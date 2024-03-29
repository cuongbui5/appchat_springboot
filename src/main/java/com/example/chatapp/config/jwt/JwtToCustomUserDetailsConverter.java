package com.example.chatapp.config.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;

import com.example.chatapp.config.CustomUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToCustomUserDetailsConverter {
    public CustomUserDetails converter(DecodedJWT jwt){
        return CustomUserDetails.builder()
                .userId(Long.valueOf(jwt.getSubject()))
                .email(jwt.getClaim("email").asString())
                .authorities(extractAuthoritiesFromClaim(jwt))
                .build();
    }
    private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT jwt){
        var claim=jwt.getClaim("authorities");
        if(claim.isNull()||claim.isMissing()){
            return List.of();
        }
        return claim.asList(SimpleGrantedAuthority.class);
    }
}
