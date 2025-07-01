package com.mockpilot.mockery_spring.model;

import lombok.Data;
import java.util.Map;

@Data
public class MockResponse {
    private int status;
    private Map<String, Object> body;
}
