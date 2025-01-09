package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.test.ApplicationModuleTest;

@ApplicationModuleTest
class DemoApplicationTests {

	 @Test
    void contextLoads() {
        ApplicationModules.of(DemoApplication.class).verify();
    }

}
