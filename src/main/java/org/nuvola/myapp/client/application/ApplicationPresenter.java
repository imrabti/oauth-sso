package org.nuvola.myapp.client.application;

import javax.inject.Inject;

import org.nuvola.myapp.client.NameTokens;
import org.nuvola.myapp.client.application.ApplicationPresenter.MyProxy;
import org.nuvola.myapp.client.application.ApplicationPresenter.MyView;
import org.nuvola.oauth.client.util.CurrentUser;

import com.google.web.bindery.event.shared.EventBus;
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
        void welcomeUser(String firstName, String lastName);
    }

    private final CurrentUser currentUser;

    @Inject
    ApplicationPresenter(EventBus eventBus,
                         MyView view,
                         MyProxy proxy,
                         CurrentUser currentUser) {
        super(eventBus, view, proxy, RevealType.Root);

        this.currentUser = currentUser;

        getView().setUiHandlers(this);
    }

    @Override
    protected void onReveal() {
        getView().welcomeUser(currentUser.getFirstName(), currentUser.getLastName());
    }
}
