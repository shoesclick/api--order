package com.shoesclick.api.order.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.HashSet;
import java.util.Set;

public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final ObjectMapper objectMapper;

    public JwtConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Set<SimpleGrantedAuthority> grants = getRealmRoles(jwt);
        return new JwtAuthenticationToken(jwt, grants);
    }

    private Set<SimpleGrantedAuthority> getRealmRoles(Jwt jwt) {
        Set<SimpleGrantedAuthority> rolesPrefix = new HashSet<>();
        JsonNode json = objectMapper.convertValue(jwt.getClaim("realm_access"), JsonNode.class);
        json.elements().forEachRemaining( role -> role.elements().forEachRemaining( item -> rolesPrefix.add(new SimpleGrantedAuthority("ROLE_" + item.asText()))));
        return rolesPrefix;
    }

}
