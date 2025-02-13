package com.example.demo.eventaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;

import com.example.demo.eventaction.action.Action;
import com.example.demo.eventaction.action.RepublishUncompletedEvent;

@ApplicationModuleTest
class EventActionIntegrationTest {

    @Autowired
    RepublishUncompletedEvent republishUncompletedEvent;

    @Test
    void verifyModule() {

    }

    @Test
    void testUncompletedEvent() {

        republishUncompletedEvent.republish(Action.PAYMENT);
    }
}
