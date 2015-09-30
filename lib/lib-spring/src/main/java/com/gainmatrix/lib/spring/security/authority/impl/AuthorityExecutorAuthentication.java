package com.gainmatrix.lib.spring.security.authority.impl;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthorityExecutorAuthentication extends AbstractAuthenticationToken {

    private static final String AUTHORITY_CONTEXT_PRINCIPAL = "AuthorityContext";

    private static final String AUTHORITY_CONTEXT_CREDENTIALS = "-";

    public AuthorityExecutorAuthentication(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return AUTHORITY_CONTEXT_CREDENTIALS;
    }

    @Override
    public Object getPrincipal() {
        return AUTHORITY_CONTEXT_PRINCIPAL;
    }

}
