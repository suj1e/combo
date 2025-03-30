package org.flooc.combo.security.jwt;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * @author sujie
 * @since 1.0.0
 */
public class JwtAuthenticationTokenGrantAuthorityDeserializer extends JsonDeserializer<GrantedAuthority> {

	@Override
	public GrantedAuthority deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JacksonException {
		ObjectMapper mapper = (ObjectMapper) p.getCodec();
		JsonNode jsonNode = mapper.readTree(p);
		return new SimpleGrantedAuthority(jsonNode.elements().next().asText());
	}

}
