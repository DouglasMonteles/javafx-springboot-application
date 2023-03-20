package com.doug.jfx.store.helpers;

import com.doug.jfx.store.builders.DialogBuilder;
import com.doug.jfx.store.builders.impl.DialogBuilderImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Dialog {

    public static void infoDialog(String title, String headerText, String contentText) {
        DialogBuilder alert = baseDialog(Alert.AlertType.INFORMATION, title, headerText, contentText);
        alert.build();
    }

    public static void waringDialog(String title, String headerText, String contentText) {
        DialogBuilder alert = baseDialog(Alert.AlertType.WARNING, title, headerText, contentText);
        alert.build();
    }

    public static void errorDialog(String title, String headerText, String contentText) {
        DialogBuilder alert = baseDialog(Alert.AlertType.ERROR, title, headerText, contentText);
        alert.build();
    }

    public static Optional<ButtonType> confirmationDialog(String title, String headerText, String contentText) {
        DialogBuilder alert = baseDialog(Alert.AlertType.CONFIRMATION, title, headerText, contentText);
        return alert.build();
    }

    private static DialogBuilder baseDialog(Alert.AlertType type, String title, String headerText, String contentText) {
        DialogBuilder alert = new DialogBuilderImpl(type);

        alert.setTitle(title)
            .setHeaderText(headerText)
            .setContentText(contentText);

        return alert;
    }

}
