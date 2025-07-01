package com.mockpilot.mockery_spring.controller;

import com.mockpilot.mockery_spring.model.MockEndpoint;
import com.mockpilot.mockery_spring.service.MockService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MockController {

    private final MockService mockService;

    @PostMapping("/__mock")
    public ResponseEntity<String> addMock(@RequestBody MockEndpoint mock) {
        mockService.addMock(mock);
        return ResponseEntity.ok("Mock added.");
    }

    @RequestMapping("/**")
    public ResponseEntity<?> handleAny(HttpServletRequest request) {
        String path = request.getRequestURI();
        String method = request.getMethod();

        MockEndpoint matched = mockService.findMatching(method, path);
        if (matched == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .status(matched.getResponse().getStatus())
                .body(matched.getResponse().getBody());
    }
}
