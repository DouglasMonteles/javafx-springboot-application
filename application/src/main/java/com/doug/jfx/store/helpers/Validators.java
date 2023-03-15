package com.doug.jfx.store.helpers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.scene.control.TextInputControl;

import java.util.List;

public class Validators {

    public static void validateFormSubmitButton(MFXTextField field, MFXButton submitButton) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            submitButton.setDisable(isFieldInvalid(newValue));
        });
    }

    public static void validateFormSubmitButton(List<MFXTextField> fields, MFXButton submitButton) {
        fields.forEach(field -> {
            field.textProperty().addListener((observable, oldValue, newValue) -> {
                boolean isAnyFieldInvalid = fields.stream()
                        .map(TextInputControl::getText)
                        .anyMatch(Validators::isFieldInvalid);

                submitButton.setDisable(isAnyFieldInvalid);
            });
        });
    }

    private static boolean isFieldInvalid(String fieldValue) {
        return (fieldValue == null || fieldValue.isEmpty());
    }

}
