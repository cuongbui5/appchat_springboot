package com.example.chatapp.config.authentication;


import com.example.chatapp.config.CustomUserDetails;
import org.springframework.security.authentication.AbstractAuthenticationToken;


public class CustomAbstractAuthenticationToken extends AbstractAuthenticationToken {
    private final CustomUserDetails userDetails;

    public CustomAbstractAuthenticationToken(CustomUserDetails userDetails) {
        super(userDetails.getAuthorities());
        this.userDetails=userDetails;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return userDetails.getPassword();
    }

    @Override
    public CustomUserDetails getPrincipal() {
        return userDetails;
    }
}
