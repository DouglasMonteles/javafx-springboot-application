package com.doug.jfx.store.enums;

import com.doug.jfx.store.builders.ScreenBuilder;
import com.doug.jfx.store.builders.impl.ScreenBuilderImpl;
import com.doug.jfx.store.controllers.RoutesController;
import com.doug.jfx.store.controllers.UserController;
import com.doug.jfx.store.models.dtos.UserDTO;
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
        public void apply() {
            screen.setTitle("Login")
                    .setResizable(false)
                    .setFullScreen(false)
                    .setWidth(600)
                    .setHeight(440)
                    .setAlwaysOnTop(true)
                    .setScene(handleFxmlScene(loadFxmlScreen()))
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
        public void apply() {
            screen.setTitle("Admin")
                    .setMaximized(true)
                    .setMinWidth(600)
                    .setMinHeight(450)
                    .setScene(handleFxmlScene(loadFxmlScreen()))
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
        public void apply() {
            screen.setTitle("Cadastro de Usuário")
                    .setWidth(800)
                    .setHeight(680)
                    .setMinWidth(500)
                    .setMinHeight(500)
                    .setResizable(true)
                    .setScene(handleFxmlScene(loadFxmlScreen()))
                    .build();
        }

        @Override
        public void close() {
            screen.destroy();
        }
    },

    UPDATE_USER () {
        private final ScreenBuilder screen = new ScreenBuilderImpl();

        @Override
        protected FXMLLoader loadFxmlScreen() {
            var fxmlLoader = getResource("update_user_screen");
            fxmlLoader.setControllerFactory(controller -> RoutesController.userController);

            return fxmlLoader;
        }

        @Override
        public void apply() {
            screen.setTitle("Atualização de Usuário")
                    .setWidth(800)
                    .setHeight(680)
                    .setMinWidth(500)
                    .setMinHeight(500)
                    .setResizable(true)
                    .setScene(handleFxmlScene(loadFxmlScreen()))
                    .build();
        }

        @Override
        public void close() {
            screen.destroy();
        }
    },

    INFO_USER () {
        private final ScreenBuilder screen = new ScreenBuilderImpl();

        @Override
        protected FXMLLoader loadFxmlScreen() {
            var fxmlLoader = getResource("info_user_screen");
            fxmlLoader.setControllerFactory(controller -> RoutesController.userController);

            return fxmlLoader;
        }

        @Override
        public void apply() {
            screen.setTitle("Informações de Usuário")
                    .setWidth(800)
                    .setHeight(680)
                    .setMinWidth(500)
                    .setMinHeight(500)
                    .setResizable(true)
                    .setScene(handleFxmlScene(loadFxmlScreen()))
                    .build();
        }

        @Override
        public void close() {
            screen.destroy();
        }
    },

    INFO_CATEGORY () {
        private final ScreenBuilder screen = new ScreenBuilderImpl();

        @Override
        protected FXMLLoader loadFxmlScreen() {
            var fxmlLoader = getResource("info_category_screen");
            fxmlLoader.setControllerFactory(controller -> RoutesController.categoryController);

            return fxmlLoader;
        }

        @Override
        public void apply() {
            screen.setTitle("Informações da Categoria")
                    .setWidth(800)
                    .setHeight(680)
                    .setMinWidth(500)
                    .setMinHeight(500)
                    .setResizable(true)
                    .setScene(handleFxmlScene(loadFxmlScreen()))
                    .build();
        }

        @Override
        public void close() {
            screen.destroy();
        }
    },

    INSERT_CATEGORY () {
        private final ScreenBuilder screen = new ScreenBuilderImpl();

        @Override
        protected FXMLLoader loadFxmlScreen() {
            var fxmlLoader = getResource("insert_category_screen");
            fxmlLoader.setControllerFactory(controller -> RoutesController.categoryController);

            return fxmlLoader;
        }

        @Override
        public void apply() {
            screen.setTitle("Cadastro de Categoria")
                    .setWidth(600)
                    .setHeight(450)
                    .setMinWidth(200)
                    .setMinHeight(200)
                    .setResizable(true)
                    .setScene(handleFxmlScene(loadFxmlScreen()))
                    .build();
        }

        @Override
        public void close() {
            screen.destroy();
        }
    },

    UPDATE_CATEGORY () {
        private final ScreenBuilder screen = new ScreenBuilderImpl();

        @Override
        protected FXMLLoader loadFxmlScreen() {
            var fxmlLoader = getResource("update_category_screen");
            fxmlLoader.setControllerFactory(controller -> RoutesController.categoryController);

            return fxmlLoader;
        }

        @Override
        public void apply() {
            screen.setTitle("Atualização da Categoria")
                    .setWidth(600)
                    .setHeight(450)
                    .setMinWidth(200)
                    .setMinHeight(200)
                    .setResizable(true)
                    .setScene(handleFxmlScene(loadFxmlScreen()))
                    .build();
        }

        @Override
        public void close() {
            screen.destroy();
        }
    },

    INSERT_PRODUCT () {
        private final ScreenBuilder screen = new ScreenBuilderImpl();

        @Override
        protected FXMLLoader loadFxmlScreen() {
            var fxmlLoader = getResource("insert_product_screen");
            fxmlLoader.setControllerFactory(controller -> RoutesController.productController);

            return fxmlLoader;
        }

        @Override
        public void apply() {
            screen.setTitle("Cadastro de Produto")
                    .setWidth(600)
                    .setHeight(450)
                    .setMinWidth(200)
                    .setMinHeight(200)
                    .setResizable(true)
                    .setScene(handleFxmlScene(loadFxmlScreen()))
                    .build();
        }

        @Override
        public void close() {
            screen.destroy();
        }
    };

    protected abstract FXMLLoader loadFxmlScreen();

    public abstract void apply();

    public abstract void close();

    public static void redirectTo(Routes route) {
        route.apply();
    }

    private static Scene handleFxmlScene(FXMLLoader fxmlLoader) {
        try {
            return new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected FXMLLoader getResource(String screenName) {
        return new FXMLLoader(Routes.class.getResource("/screens/" + screenName + ".fxml"));
    }

}
