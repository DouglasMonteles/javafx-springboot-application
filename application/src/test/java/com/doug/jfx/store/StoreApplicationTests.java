package com.doug.jfx.store;

import javafx.application.Platform;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StoreApplicationTests {

	@BeforeAll
	static void setup() {
		Platform.startup(() -> {});
	}

	@Test
	void contextLoads() {

	}

}
