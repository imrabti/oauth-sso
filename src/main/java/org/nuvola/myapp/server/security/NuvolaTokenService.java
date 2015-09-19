package org.nuvola.myapp.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

@Component
public class NuvolaTokenService extends DefaultTokenServices {
    @Autowired
    NuvolaTokenService(TokenStore tokenStore) {
        setTokenStore(tokenStore);
    }

    @Override
    public OAuth2Authentication loadAuthentication(String accessTokenValue) throws AuthenticationException,
            InvalidTokenException {
        return super.loadAuthentication(accessTokenValue);
    }
}
