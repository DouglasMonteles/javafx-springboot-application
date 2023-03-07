package com.doug.jfx.store.builders;

import com.doug.jfx.store.enums.Routes;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface ScreenBuilder {

    ScreenBuilder setTitle(String title);
    ScreenBuilder setResizable(boolean isResizable);
    ScreenBuilder setFullScreen(boolean isFullScreen);
    ScreenBuilder setWidth(double width);
    ScreenBuilder setHeight(double height);
    ScreenBuilder setAlwaysOnTop(boolean isAlwaysOnTop);
    ScreenBuilder setScene(Scene scene);
    void build();

}