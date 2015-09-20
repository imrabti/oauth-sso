package org.nuvola.myapp.client.application;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ApplicationView extends ViewWithUiHandlers<ApplicationUiHandlers> implements ApplicationPresenter.MyView {
    interface Binder extends UiBinder<HTMLPanel, ApplicationView> {
    }

    @UiField
    InlineLabel firstName;
    @UiField
    InlineLabel lastName;

    @Inject
    ApplicationView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void welcomeUser(String firstName, String lastName) {
        this.firstName.setText(firstName);
        this.lastName.setText(lastName);
    }
}
