package org.flooc.combo.security.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class JwtAuthenticationTokenMapper {

	private JwtAuthenticationTokenMapper() {
	}

	public static JwtAuthenticationToken toToken(String claim) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addDeserializer(JwtAuthenticationToken.class, new JwtAuthenticationTokenDeserializer());
		simpleModule.addDeserializer(GrantedAuthority.class, new JwtAuthenticationTokenGrantAuthorityDeserializer());
		om.registerModule(simpleModule);
		return om.readValue(claim, JwtAuthenticationToken.class);
	}

}
