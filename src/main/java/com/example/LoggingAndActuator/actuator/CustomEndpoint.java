package com.example.LoggingAndActuator.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "customEndpoint")
public class CustomEndpoint {

    @ReadOperation
    public Map<String, Object> customStatus() {
        Map<String, Object> response = new HashMap<>();

        // Custom Status and Application Info
        response.put("status", "Application is running smoothly!");
        response.put("version", "1.0.0");

        // Add system details
        response.put("availableProcessors", Runtime.getRuntime().availableProcessors());
        response.put("freeMemory", Runtime.getRuntime().freeMemory());
        response.put("totalMemory", Runtime.getRuntime().totalMemory());
        response.put("maxMemory", Runtime.getRuntime().maxMemory());

        // Application uptime
        long uptime = ManagementFactory.getRuntimeMXBean().getUptime();
        response.put("uptime", uptime + " ms");

        // Add custom metrics (example: active users, environment)
        response.put("activeUsers", getActiveUsers());
        response.put("environment", "production");

        return response;
    }

    private int getActiveUsers() {
        return 2; // Example: Return active users count
    }
}

