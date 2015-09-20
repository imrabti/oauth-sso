package org.nuvola.myapp.client;

import org.nuvola.myapp.client.application.ApplicationModule;
import org.nuvola.myapp.client.services.ServiceModule;
import org.nuvola.myapp.client.unauthorized.UnauthorizedModule;
import org.nuvola.myapp.client.util.ClientId;
import org.nuvola.myapp.client.util.CurrentUser;

import com.google.gwt.inject.client.AbstractGinModule;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.client.proxy.DefaultPlaceManager;
import com.gwtplatform.mvp.shared.proxy.RouteTokenFormatter;

public class ClientModule extends AbstractGinModule {
    @Override
    protected void configure() {
        DefaultModule defaultModule = new DefaultModule.Builder()
                .placeManager(DefaultPlaceManager.class)
                .tokenFormatter(RouteTokenFormatter.class)
                .build();

        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.getHome());
        bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.getHome());
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.getUnauthorized());
        bindConstant().annotatedWith(ClientId.class).to("myapp");

        bind(CurrentUser.class).asEagerSingleton();

        install(defaultModule);
        install(new ServiceModule());
        install(new ApplicationModule());
        install(new UnauthorizedModule());
    }
}
