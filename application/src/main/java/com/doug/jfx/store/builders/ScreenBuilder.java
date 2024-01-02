package com.doug.jfx.store.builders;

import javafx.scene.Scene;

public interface ScreenBuilder {

    ScreenBuilder setTitle(String title);
    ScreenBuilder setResizable(boolean isResizable);
    ScreenBuilder setFullScreen(boolean isFullScreen);
    ScreenBuilder setWidth(double width);
    ScreenBuilder setMaxWidth(double maxWidth);
    ScreenBuilder setHeight(double height);
    ScreenBuilder setMaxHeight(double maxHeight);
    ScreenBuilder setAlwaysOnTop(boolean isAlwaysOnTop);
    ScreenBuilder setScene(Scene scene);
    ScreenBuilder setMinWidth(double minWidth);
    ScreenBuilder setMinHeight(double minHeight);
    ScreenBuilder setMaximized(boolean isMaximized);
    void build();
    void destroy();

}
