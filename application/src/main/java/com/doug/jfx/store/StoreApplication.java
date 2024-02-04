package com.doug.jfx.store;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		Application.launch(JavaFxApplication.class, args);
	}

}
