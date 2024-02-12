package com.doug.jfx.store.builders;

import javafx.scene.control.ButtonType;

import java.util.Optional;

public interface DialogBuilder {

    DialogBuilder setTitle(String title);

    DialogBuilder setHeaderText(String headerText);

    DialogBuilder setContentText(String contentText);

    DialogBuilder setWidth(double width);

    Optional<ButtonType> build();

}
