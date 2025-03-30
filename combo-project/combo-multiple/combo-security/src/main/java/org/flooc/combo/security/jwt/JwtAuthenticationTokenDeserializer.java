package org.flooc.combo.security.jwt;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import java.io.IOException;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author sujie
 * @since 1.0.0
 */
public class JwtAuthenticationTokenDeserializer extends JsonDeserializer<JwtAuthenticationToken> {

	private static final TypeReference<List<GrantedAuthority>> GRANTED_AUTHORITY_LIST = new TypeReference<>() {
	};

	private static final TypeReference<Object> OBJECT = new TypeReference<>() {
	};

	@Override
	public JwtAuthenticationToken deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectMapper mapper = (ObjectMapper) jp.getCodec();
		JsonNode jsonNode = mapper.readTree(jp);
		boolean authenticated = readJsonNode(jsonNode, "authenticated").asBoolean();
		JsonNode principalNode = readJsonNode(jsonNode, "principal");
		Object principal = getPrincipal(mapper, principalNode);
		JsonNode credentialsNode = readJsonNode(jsonNode, "credentials");
		Object credentials = getCredentials(credentialsNode);
		List<GrantedAuthority> authorities = mapper.readValue(readJsonNode(jsonNode, "authorities").traverse(mapper),
				GRANTED_AUTHORITY_LIST);
		JwtAuthenticationToken token = new JwtAuthenticationToken(principal, credentials, authorities);
		token.setAuthenticated(authenticated);
		JsonNode detailsNode = readJsonNode(jsonNode, "details");
		if (detailsNode.isNull() || detailsNode.isMissingNode()) {
			token.setDetails(null);
		}
		else {
			Object details = mapper.readValue(detailsNode.toString(), OBJECT);
			token.setDetails(details);
		}
		return token;
	}

	private Object getCredentials(JsonNode credentialsNode) {
		if (credentialsNode.isNull() || credentialsNode.isMissingNode()) {
			return null;
		}
		return credentialsNode.asText();
	}

	private Object getPrincipal(ObjectMapper mapper, JsonNode principalNode)
			throws IOException, JsonParseException, JsonMappingException {
		if (principalNode.isObject()) {
			return mapper.readValue(principalNode.traverse(mapper), Object.class);
		}
		return principalNode.asText();
	}

	private JsonNode readJsonNode(JsonNode jsonNode, String field) {
		return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
	}

}
