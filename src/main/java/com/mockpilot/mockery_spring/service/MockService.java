package com.mockpilot.mockery_spring.service;

import com.mockpilot.mockery_spring.model.MockEndpoint;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockService {

    private final List<MockEndpoint> mockEndpoints = new ArrayList<>();

    public void addMock(MockEndpoint mock) {
        mockEndpoints.add(mock);
    }

    public List<MockEndpoint> getMocks() {
        return mockEndpoints;
    }

    public MockEndpoint findMatching(String method, String path) {
        return mockEndpoints.stream()
                .filter(mock -> mock.getMethod().equalsIgnoreCase(method) && matchPath(mock.getPath(), path))
                .findFirst()
                .orElse(null);
    }

    private boolean matchPath(String defined, String actual) {
        String[] a = defined.split("/");
        String[] b = actual.split("/");
        if (a.length != b.length) return false;

        for (int i = 0; i < a.length; i++) {
            if (!a[i].startsWith(":") && !a[i].equals(b[i])) return false;
        }
        return true;
    }
}
