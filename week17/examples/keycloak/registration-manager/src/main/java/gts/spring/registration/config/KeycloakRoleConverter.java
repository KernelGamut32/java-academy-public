package gts.spring.registration.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.*;
import java.util.stream.Collectors;

public class KeycloakRoleConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        // Default converter for scopes/authorities
        JwtGrantedAuthoritiesConverter defaultAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        Collection<GrantedAuthority> defaultAuthorities = defaultAuthoritiesConverter.convert(jwt);
        // Extract realm_access roles and map them to GrantedAuthority objects
        Collection<GrantedAuthority> realmAccessRoles = extractRealmAccessRoles(jwt);
        // Combine authorities
        Set<GrantedAuthority> combinedAuthorities = new HashSet<>();
        combinedAuthorities.addAll(defaultAuthorities);
        combinedAuthorities.addAll(realmAccessRoles);
        return new AbstractAuthenticationToken(combinedAuthorities) {
            @Override
            public Object getCredentials() {
                return null;
            }
            @Override
            public Object getPrincipal() {
                return jwt.getSubject();
            }
        };
    }

    public static List<GrantedAuthority> extractRealmAccessRoles(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
        if (realmAccess == null) {
            return Collections.emptyList();
        }
        if (realmAccess.get("roles") instanceof List<?> rawList) {
            List<String> roles = rawList.stream()
                    .filter(String.class::isInstance)
                    .map(String.class::cast)
                    .toList();
            return roles.stream()
                    .map(roleName -> new SimpleGrantedAuthority(roleName.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
