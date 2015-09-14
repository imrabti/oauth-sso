package org.nuvola.myapp.client.util;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import org.nuvola.miniachat.common.client.event.RequestEvent;
import org.nuvola.miniachat.common.shared.GlobalParameter;

import java.util.Date;

public abstract class AbstractAsyncCallback<T> implements AsyncCallback<T>, HasHandlers {
    @Inject
    private static EventBus eventBus;

    private Long created;

    public AbstractAsyncCallback() {
        RequestEvent.fire(this, RequestEvent.State.SENT, this);
        created = (new Date()).getTime();
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEvent(event);
    }

    @Override
    public void onSuccess(T response) {
        RequestEvent.fire(this, RequestEvent.State.RECEIVED, this);
        onReceive(response);
    }

    @Override
    public void onFailure(Throwable exception) {
        Window.Location.replace("/404");
    }

    public Boolean isExpired() {
        Date current = new Date();
        return (current.getTime() - created) > 10 * GlobalParameter.SECOND_IN_MILIS;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public abstract void onReceive(T response);
}
