package com.doug.jfx.store.controllers.components;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SideOptionsComponent extends VBox {

    @FXML
    private MFXButton deleteButton;

    @FXML
    private MFXButton editButton;

    @FXML
    private MFXButton infoButton;

    @Setter
    private DefaultAction infoAction;

    @Setter
    private DefaultAction editAction;

    @Setter
    private DefaultAction deleteAction;

    public SideOptionsComponent() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/screens/components/side_options_component.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public SideOptionsComponent(String infoTextBtn, String editTextBtn, String deleteTextBtn) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/screens/components/side_options_component.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

            infoButton.setText(infoTextBtn);
            editButton.setText(editTextBtn);
            deleteButton.setText(deleteTextBtn);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void doDelete(ActionEvent event) {
        if (deleteAction != null) {
            deleteAction.handleDefaultAction();
        }
    }

    @FXML
    public void doEdit(ActionEvent event) {
        if (editAction != null) {
            editAction.handleDefaultAction();
        }
    }

    @FXML
    public void doInfo(ActionEvent event) {
        if (infoAction != null) {
            infoAction.handleDefaultAction();
        }
    }

}
