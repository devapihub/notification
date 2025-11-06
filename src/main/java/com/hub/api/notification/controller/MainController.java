package com.hub.api.notification.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
@Slf4j
public class MainController {

    @GetMapping("")
    public Object hello() {
        return Map.of("status", "OK", "message", "Hello notification service");
    }
}
