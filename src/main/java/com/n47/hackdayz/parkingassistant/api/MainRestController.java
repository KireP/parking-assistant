package com.n47.hackdayz.parkingassistant.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MainRestController {

    @GetMapping
    public Map<String, String> test() {
        return Collections.singletonMap("message", "OK");
    }
}
