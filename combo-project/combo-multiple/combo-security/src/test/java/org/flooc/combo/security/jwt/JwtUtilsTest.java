package org.flooc.combo.security.jwt;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSHeader.Builder;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

/**
 * @author sujie
 * @since 1.0.0
 */
class JwtUtilsTest {
  @Test
  void testRsa() throws JOSEException, ParseException {
    // RSA signatures require a public and private RSA key pair,
    // the public key must be made known to the JWS recipient to
    // allow the signatures to be verified
    RSAKey rsaJWK = new RSAKeyGenerator(2048).keyID("123").generate();
    RSAKey rsaPublicJWK = rsaJWK.toPublicJWK();

    // Create RSA-signer with the private key
    JWSSigner signer = new RSASSASigner(rsaJWK);

    // Prepare JWS object with simple string as payload
    JWSHeader header = new Builder(JWSAlgorithm.RS256).keyID(rsaJWK.getKeyID()).build();
    Payload payload = new Payload("In RSA we trust!");
    JWSObject jwsObject = new JWSObject(header, payload);

    // Compute the RSA signature
    jwsObject.sign(signer);

    // To serialize to compact form, produces something like
    // eyJhbGciOiJSUzI1NiJ9.SW4gUlNBIHdlIHRydXN0IQ.IRMQENi4nJyp4er2L
    // mZq3ivwoAjqa1uUkSBKFIX7ATndFF5ivnt-m8uApHO4kfIFOrW7w2Ezmlg3Qd
    // maXlS9DhN0nUk_hGI3amEjkKd0BWYCB8vfUbUv0XGjQip78AI4z1PrFRNidm7
    // -jPDm5Iq0SZnjKjCNS5Q15fokXZc8u0A
    String s = jwsObject.serialize();

    // To parse the JWS and verify it, e.g. on client-side
    jwsObject = JWSObject.parse(s);

    JWSVerifier verifier = new RSASSAVerifier(rsaPublicJWK);

    assertTrue(jwsObject.verify(verifier));

    assertEquals("In RSA we trust!", jwsObject.getPayload().toString());
  }

  @Test
  void testJwt() throws JOSEException, ParseException, InterruptedException, JsonProcessingException {
    Map<String, Object> claimMap = new HashMap<>();
    claimMap.put("data", "hello");
    String token = JwtUtils.generate("256", 3, "123", claimMap).getToken();
    assertTrue(JwtUtils.verifyToken("256", token).isValid());
    assertFalse(JwtUtils.verifyToken("255", token).isValid());
    TimeUnit.SECONDS.sleep(5);
    assertFalse(JwtUtils.verifyToken("256", token).isValid());
  }
}