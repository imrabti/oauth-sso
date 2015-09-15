package org.nuvola.myapp.client.util;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

public abstract class AbstractAsyncCallback<T> implements AsyncCallback<T>, HasHandlers {
    @Inject
    private static EventBus eventBus;

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEvent(event);
    }

    @Override
    public void onSuccess(T response) {
        onReceive(response);
    }

    @Override
    public void onFailure(Throwable exception) {
    }

    public abstract void onReceive(T response);
}
