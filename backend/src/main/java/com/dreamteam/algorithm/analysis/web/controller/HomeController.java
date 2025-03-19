package com.dreamteam.algorithm.analysis.web.controller;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {
    @GetMapping
    public ResponseEntity<Map<String, String>> home() {
        Map<String, String> map = new HashMap<>();
        map.put("response", "Hello World");
        return ResponseEntity.ok().body(map);
    }
}
