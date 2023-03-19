package com.doug.jfx.store;


import com.doug.jfx.store.enums.Routes;
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
    public void init() {
        SpringApplication.run(StoreApplication.class)
                .getAutowireCapableBeanFactory()
                .autowireBean(this);
    }

    @Override
    public void start(Stage stage) {
        Routes.redirectTo(Routes.LOGIN);
    }

    @Override
    public void stop() {
        context.close();
        Platform.exit();
    }
}
