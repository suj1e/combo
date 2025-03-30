package org.flooc.combo.security.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSHeader.Builder;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.util.DateUtils;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.security.exception.SecurityErrorCode;
import org.flooc.combo.security.common.JwtTokenVerifyResult;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public final class JwtUtils {

	private static final int SIZE = 2048;

	private static final String RSA = "RSA";

	private JwtUtils() {
	}

	public static final ConcurrentHashMap<String, String> PUBLIC_RSA_KEYS = new ConcurrentHashMap<>();

	public static final ConcurrentHashMap<String, RSAKey> RSA_KEYS = new ConcurrentHashMap<>();

	public static JwtToken generate(String keyId, long expiresIn, String subject, Map<String, Object> claimMap)
			throws JOSEException {
		RSAKey rsaJWK = getRsaKey(keyId);
		// header
		JWSHeader header = new Builder(JWSAlgorithm.RS256).keyID(rsaJWK.getKeyID()).build();
		// payload
		Instant now = Instant.now();
		Date issueTime = Date.from(now);
		Date nbfTime = Date.from(now);
		JwtToken jwtToken = new JwtToken();
		jwtToken.setIat(issueTime);
		jwtToken.setEt(Date.from(issueTime.toInstant().plusSeconds(expiresIn)));
		jwtToken.setNbf(nbfTime);
		jwtToken.setEi(expiresIn);
		JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder().subject(subject)
			.issueTime(jwtToken.getIat())
			.expirationTime(jwtToken.getEt())
			.notBeforeTime(jwtToken.getNbf());
		if (!CollectionUtils.isEmpty(claimMap)) {
			claimMap.forEach(builder::claim);
		}
		JWTClaimsSet payload = builder.build();
		SignedJWT jwsObject = new SignedJWT(header, payload);
		// singer
		JWSSigner signer = new RSASSASigner(rsaJWK);
		jwsObject.sign(signer);
		// 存储公钥
		savePublicKey(keyId, rsaJWK);
		String token = jwsObject.serialize();
		jwtToken.setToken(token);
		return jwtToken;
	}

	private static void savePublicKey(String keyId, RSAKey rsaJWK) throws JOSEException {
		RSAKey publicJWK = rsaJWK.toPublicJWK();
		String encodePublicKey = Base64.getEncoder().encodeToString(publicJWK.toRSAPublicKey().getEncoded());
		PUBLIC_RSA_KEYS.put(keyId, encodePublicKey);
	}

	private static RSAKey getRsaKey(String keyId) throws JOSEException {
		RSAKey rsaKey = RSA_KEYS.get(keyId);
		if (Objects.isNull(rsaKey)) {
			rsaKey = new RSAKeyGenerator(SIZE).keyID(keyId).generate();
			RSA_KEYS.put(keyId, rsaKey);
		}
		return rsaKey;
	}

	/**
	 * @param keyId rsaKeyId
	 * @param token 令牌
	 * @return 验证结果，true: 通过; false: 不通过
	 */
	public static JwtTokenVerifyResult verifyToken(String keyId, String token) {
		try {
			RSAPublicKey publicKey = getRsaPublicKey(keyId);
			if (publicKey == null) {
				log.error("There is no such token public key, keyId: {}", keyId);
				return JwtTokenVerifyResult.invalid(SecurityErrorCode.TokenParseError);
			}
			JWSVerifier verifier = new RSASSAVerifier(publicKey);
			SignedJWT parse = parseToken(token);
			// 验证签名
			if (!parse.verify(verifier)) {
				return JwtTokenVerifyResult.invalid(SecurityErrorCode.TokenParseError);
			}
			// 获取过期时间
			JWTClaimsSet jwtClaimsSet = parse.getJWTClaimsSet();
			Date expirationTime = jwtClaimsSet.getExpirationTime();
			boolean expired = DateUtils.isAfter(new Date(), expirationTime, 0);
			if (expired) {
				return JwtTokenVerifyResult.invalid(SecurityErrorCode.TokenExpired);
			}
			return JwtTokenVerifyResult.valid();
		}
		catch (Exception e) {
			log.error("The jwt token is invalid");
			return JwtTokenVerifyResult.invalid(SecurityErrorCode.TokenParseError);
		}
	}

	private static RSAPublicKey getRsaPublicKey(String keyId) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyFactory rsa = KeyFactory.getInstance(RSA);
		String encodePublicKey = PUBLIC_RSA_KEYS.get(keyId);
		if (!StringUtils.hasText(encodePublicKey)) {
			return null;
		}
		byte[] decode = Base64.getDecoder().decode(encodePublicKey);
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decode);
		return (RSAPublicKey) rsa.generatePublic(x509EncodedKeySpec);
	}

	public static SignedJWT parseToken(String token) throws ParseException {
		return SignedJWT.parse(token);
	}

	public static String toAuthenticationClaim(String claimKey, String token) {
		try {
			SignedJWT signedJWT = JwtUtils.parseToken(token);
			JWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();
			Map<String, Object> claims = jwtClaimsSet.getClaims();
			return (String) claims.get(claimKey);
		}
		catch (Exception e) {
			log.error("Failed to find claim", e);
			return null;
		}
	}

	@Getter
	@Setter
	public static class JwtToken {

		private Date iat;

		private Date nbf;

		private Date et;

		private String token;

		private long ei;

	}

}
