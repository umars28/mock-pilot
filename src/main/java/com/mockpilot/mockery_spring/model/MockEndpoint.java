package com.mockpilot.mockery_spring.model;

import lombok.Data;
import java.util.Map;

@Data
public class MockEndpoint {
    private String method;
    private String path;
    private MockResponse response;
}

