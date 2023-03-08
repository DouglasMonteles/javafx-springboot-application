package com.doug.jfx.store.enums;

import com.doug.jfx.store.builders.ScreenBuilder;
import com.doug.jfx.store.controllers.RoutesController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/*
* This enum actuate as a Director class: The director class defines the order
* in which to execute the building steps, while the builder provides the
* implementation for those steps <https://refactoring.guru/design-patterns/builder>.
* */

public enum Routes {

    LOGIN () {
        public FXMLLoader loadFxmlScreen() {
            var fxmlLoader = new FXMLLoader(Routes.class.getResource("/screens/login_screen.fxml"));
            fxmlLoader.setControllerFactory(controller -> RoutesController.loginController);

            return fxmlLoader;
        }
    },

    ADMIN () {
        public FXMLLoader loadFxmlScreen() {
            var fxmlLoader = new FXMLLoader(Routes.class.getResource("/screens/login_screen.fxml"));
            fxmlLoader.setControllerFactory(controller -> RoutesController.loginController);

            return fxmlLoader;
        }
    };

    public static void LOGIN(@NotNull ScreenBuilder screen) throws IOException {
        screen.setTitle("Login")
                .setResizable(false)
                .setFullScreen(false)
                .setWidth(600)
                .setHeight(440)
                .setAlwaysOnTop(true)
                .setScene(handleFxmlScene(LOGIN.loadFxmlScreen()))
                .build();
    }

    public static void ADMIN(@NotNull ScreenBuilder screen) throws IOException {
        screen.setTitle("Admin")
                .setResizable(false)
                .setFullScreen(false)
                .setWidth(-1)
                .setHeight(-1)
                .setAlwaysOnTop(true)
                .setScene(handleFxmlScene(LOGIN.loadFxmlScreen()))
                .build();
    }

    private static Scene handleFxmlScene(FXMLLoader fxmlLoader) throws IOException {
        return new Scene(fxmlLoader.load());
    }

    public abstract FXMLLoader loadFxmlScreen();

}
