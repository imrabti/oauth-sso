package org.nuvola.myapp.client;

import javax.inject.Inject;

import org.nuvola.myapp.client.services.SessionService;
import org.nuvola.myapp.client.util.AbstractAsyncCallback;
import org.nuvola.myapp.client.util.ClientId;
import org.nuvola.myapp.client.util.CurrentUser;
import org.nuvola.oauth.shared.UserProfile;

import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

public class BootstrapperImpl implements Bootstrapper {
    private final PlaceManager placeManager;
    private final RestDispatch dispatch;
    private final SessionService sessionService;
    private final CurrentUser currentUser;
    private final String clientId;

    @Inject
    BootstrapperImpl(PlaceManager placeManager,
                     RestDispatch dispatch,
                     SessionService sessionService,
                     CurrentUser currentUser,
                     @ClientId String clientId) {
        this.placeManager = placeManager;
        this.dispatch = dispatch;
        this.sessionService = sessionService;
        this.currentUser = currentUser;
        this.clientId = clientId;
    }

    @Override
    public void onBootstrap() {
        dispatch.execute(sessionService.currentUser(), new AbstractAsyncCallback<UserProfile>() {
            @Override
            public void onReceive(UserProfile response) {
                if (response == null) {
                    placeManager.revealUnauthorizedPlace(NameTokens.getUnauthorized());
                } else {
                    currentUser.initCurrentUser(response, clientId);
                    checkIfUserAllowed();
                }
            }
        });
    }

    private void checkIfUserAllowed() {
        if (currentUser.isAllowed()) {
            placeManager.revealDefaultPlace();
        } else {
            placeManager.revealUnauthorizedPlace(NameTokens.getUnauthorized());
        }
    }
}
