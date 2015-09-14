package org.nuvola.myapp.client.application;

import javax.inject.Inject;

import org.nuvola.myapp.client.NameTokens;
import org.nuvola.myapp.client.application.ApplicationPresenter.MyProxy;
import org.nuvola.myapp.client.application.ApplicationPresenter.MyView;
import org.nuvola.myapp.client.services.SessionService;
import org.nuvola.myapp.shared.vo.CurrentUser;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class ApplicationPresenter extends Presenter<MyView, MyProxy> implements ApplicationUiHandlers {
    @ProxyStandard
    @NameToken(NameTokens.HOME)
    interface MyProxy extends ProxyPlace<ApplicationPresenter> {
    }

    interface MyView extends View, HasUiHandlers<ApplicationUiHandlers> {
    }

    private final RestDispatch dispatch;
    private final SessionService sessionService;

    @Inject
    ApplicationPresenter(EventBus eventBus,
                         MyView view,
                         MyProxy proxy,
                         RestDispatch dispatch,
                         SessionService sessionService) {
        super(eventBus, view, proxy, RevealType.Root);

        this.dispatch = dispatch;
        this.sessionService = sessionService;

        getView().setUiHandlers(this);
    }

    @Override
    protected void onReveal() {
        loadCurrentUser();
    }

    private void loadCurrentUser() {
        dispatch.execute(sessionService.currentUser(), new AsyncCallback<CurrentUser>() {
            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(CurrentUser currentUser) {
                Window.alert("You are authenticated : " + currentUser.getUsername());
            }
        });
    }
}
