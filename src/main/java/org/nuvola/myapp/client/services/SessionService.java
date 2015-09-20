package org.nuvola.myapp.client.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.nuvola.oauth.shared.UserProfile;

import com.gwtplatform.dispatch.rest.shared.RestAction;
import static org.nuvola.myapp.shared.ApiPaths.SESSION;

@Path(SESSION)
public interface SessionService {
    @GET
    RestAction<UserProfile> currentUser();
}
