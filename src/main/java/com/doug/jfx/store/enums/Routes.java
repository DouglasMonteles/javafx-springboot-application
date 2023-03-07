package com.doug.jfx.store.enums;

import com.doug.jfx.store.builders.ScreenBuilder;
import javafx.scene.Parent;
import javafx.scene.Scene;

/*
* This enum actuate as a Director class: The director class defines the order
* in which to execute the building steps, while the builder provides the
* implementation for those steps <https://refactoring.guru/design-patterns/builder>.
* */

public enum Routes {

    LOGIN;

    public static void LOGIN(ScreenBuilder screen) {
        screen.setTitle("Login")
                .setResizable(false)
                .setFullScreen(false)
                .setWidth(300)
                .setHeight(300)
                .setAlwaysOnTop(true)
                .setScene(new Scene(new Parent() {}))
                .build();
    }
}