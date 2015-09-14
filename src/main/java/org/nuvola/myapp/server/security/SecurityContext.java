package org.nuvola.myapp.server.security;

import java.security.Principal;

import org.nuvola.myapp.shared.vo.CurrentUser;

public interface SecurityContext {
    CurrentUser getCurrentUser(Principal user);
}
