package com.example.demo.eventaction;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.eventaction.action.Action;
import com.example.demo.eventaction.action.RepublishUncompletedEvent;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "event-action")
public class Controller {

    private final RepublishUncompletedEvent republishUncompletedEvent;

    @GetMapping
    public ResponseEntity<String> publishEvent(@RequestParam(name = "action") String action) {
        republishUncompletedEvent.republish(Action.getActionByName(action));
        return new ResponseEntity<>("Event Triggered", HttpStatus.NO_CONTENT);
    }
}
