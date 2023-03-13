package com.doug.jfx.store.enums;

import com.doug.jfx.store.builders.ScreenBuilder;
import com.doug.jfx.store.builders.impl.ScreenBuilderImpl;
import com.doug.jfx.store.controllers.RoutesController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

/*
* This enum actuate as a Director class: The director class defines the order
* in which to execute the building steps, while the builder provides the
* implementation for those steps <https://refactoring.guru/design-patterns/builder>.
* */

public enum Routes {

    LOGIN () {
        private final ScreenBuilder screen = new ScreenBuilderImpl();

        @Override
        protected FXMLLoader loadFxmlScreen() {
            var fxmlLoader = getResource("login_screen");
            fxmlLoader.setControllerFactory(controller -> RoutesController.loginController);

            return fxmlLoader;
        }

        @Override
        public void apply() throws IOException {
            screen.setTitle("Login")
                    .setResizable(false)
                    .setFullScreen(false)
                    .setWidth(600)
                    .setHeight(440)
                    .setAlwaysOnTop(true)
                    .setScene(handleFxmlScene(LOGIN.loadFxmlScreen()))
                    .build();
        }

        @Override
        public void close() {
            screen.destroy();
        }
    },

    ADMIN () {
        private final ScreenBuilder screen = new ScreenBuilderImpl();

        @Override
        protected FXMLLoader loadFxmlScreen() {
            var fxmlLoader = getResource("admin_screen");
            fxmlLoader.setControllerFactory(controller -> RoutesController.adminController);

            return fxmlLoader;
        }

        @Override
        public void apply() throws IOException {
            screen.setTitle("Admin")
                    .setMaximized(true)
                    .setMinWidth(600)
                    .setMinHeight(450)
                    .setScene(handleFxmlScene(ADMIN.loadFxmlScreen()))
                    .build();
        }

        @Override
        public void close() {
            screen.destroy();
        }
    },

    INSERT_USER () {
        private final ScreenBuilder screen = new ScreenBuilderImpl();

        @Override
        protected FXMLLoader loadFxmlScreen() {
            var fxmlLoader = getResource("insert_user_screen");
            fxmlLoader.setControllerFactory(controller -> RoutesController.userController);

            return fxmlLoader;
        }

        @Override
        public void apply() throws IOException {
            screen.setTitle("Cadastro de Usuário")
                    .setWidth(800)
                    .setHeight(680)
                    .setMinWidth(500)
                    .setMinHeight(500)
                    .setAlwaysOnTop(true)
                    .setResizable(true)
                    .setScene(Routes.handleFxmlScene(Routes.INSERT_USER.loadFxmlScreen()))
                    .build();
        }

        @Override
        public void close() {
            screen.destroy();
        }
    };

    protected abstract FXMLLoader loadFxmlScreen();

    public abstract void apply() throws IOException;

    public abstract void close();

    public static void redirectTo(Routes route) throws IOException {
        route.apply();
    }

    private static Scene handleFxmlScene(FXMLLoader fxmlLoader) throws IOException {
        return new Scene(fxmlLoader.load());
    }

    protected FXMLLoader getResource(String screenName) {
        return new FXMLLoader(Routes.class.getResource("/screens/" + screenName + ".fxml"));
    }

}
