package com.unibanco.itau.config;

import com.unibanco.itau.entity.Users;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ApiKeyAuthenticationToken extends AbstractAuthenticationToken {
    private String credentials;
    private Users principal;

    public ApiKeyAuthenticationToken(Users user, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = user;
        this.credentials = null;
        this.setAuthenticated(true);
    }
    public ApiKeyAuthenticationToken(String apikey){
        super(null);
        this.credentials = apikey;
        this.setAuthenticated(false);
    }

    public ApiKeyAuthenticationToken(){
        super(null);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
