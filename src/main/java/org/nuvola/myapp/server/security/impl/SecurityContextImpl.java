package org.nuvola.myapp.server.security.impl;

import java.security.Principal;

import org.nuvola.myapp.server.security.SecurityContext;
import org.nuvola.myapp.shared.vo.CurrentUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextImpl implements SecurityContext {
    @Override
    public CurrentUser getCurrentUser(Principal user) {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) user;
        Authentication authentication = oAuth2Authentication.getUserAuthentication();

        if (authentication.isAuthenticated()) {
            CurrentUser currentUser = new CurrentUser();
            currentUser.setUsername((String) authentication.getPrincipal());

            return currentUser;
        }

        return null;
    }
}
