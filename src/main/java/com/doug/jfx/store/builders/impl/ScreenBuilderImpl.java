package com.doug.jfx.store.builders.impl;

import com.doug.jfx.store.builders.ScreenBuilder;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
* This implementation uses the builder design pattern with a fluent interface design.
* Builder <https://refactoring.guru/design-patterns/builder>
* Fluent Interface: <https://en.wikipedia.org/wiki/Fluent_interface>
* */

public class ScreenBuilderImpl implements ScreenBuilder {

    private final Stage stage;

    public ScreenBuilderImpl() {
        this.stage = new Stage();
    }

    public ScreenBuilderImpl(Stage stage) {
        this.stage = stage;
    }

    @Override
    public ScreenBuilder setTitle(String title) {
        stage.setTitle(title);
        return this;
    }

    @Override
    public ScreenBuilder setResizable(boolean isResizable) {
        this.stage.setResizable(isResizable);
        return this;
    }

    @Override
    public ScreenBuilder setFullScreen(boolean isFullScreen) {
        this.stage.setFullScreen(isFullScreen);
        return this;
    }

    @Override
    public ScreenBuilder setWidth(double width) {
        this.stage.setWidth(width);
        return this;
    }

    @Override
    public ScreenBuilder setHeight(double height) {
        this.stage.setHeight(height);
        return this;
    }

    @Override
    public ScreenBuilder setAlwaysOnTop(boolean isAlwaysOnTop) {
        this.stage.setAlwaysOnTop(isAlwaysOnTop);
        return this;
    }

    @Override
    public ScreenBuilder setScene(Scene scene) {
        this.stage.setScene(scene);
        return this;
    }

    @Override
    public void build() {
        this.stage.show();
    }


}
