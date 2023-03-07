package com.doug.jfx.store;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFxApplication extends Application {

    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {
        SpringApplication.run(StoreApplication.class)
                .getAutowireCapableBeanFactory()
                .autowireBean(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        context.close();
        Platform.exit();
    }
}
