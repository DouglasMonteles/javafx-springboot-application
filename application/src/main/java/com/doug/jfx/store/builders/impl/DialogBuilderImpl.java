package com.doug.jfx.store.builders.impl;

import com.doug.jfx.store.builders.DialogBuilder;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DialogBuilderImpl implements DialogBuilder {

    private final Alert alert;

    public DialogBuilderImpl(Alert.AlertType type) {
        this.alert = new Alert(type);
    }

    @Override
    public DialogBuilder setTitle(String title) {
        this.alert.setTitle(title);
        return this;
    }

    @Override
    public DialogBuilder setHeaderText(String headerText) {
        this.alert.setHeaderText(headerText);
        return this;
    }

    @Override
    public DialogBuilder setContentText(String contentText) {
        this.alert.setContentText(contentText);
        return this;
    }

    public Optional<ButtonType> build() {
        return this.alert.showAndWait();
    }

}
