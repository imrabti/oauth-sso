package org.nuvola.myapp.client.application;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * Created by imrabti on 9/9/15.
 */
public class ApplicationView extends Composite {
    interface ApplicationViewUiBinder extends UiBinder<HTMLPanel, ApplicationView> {
    }

    private static ApplicationViewUiBinder ourUiBinder = GWT.create(ApplicationViewUiBinder.class);

    public ApplicationView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}