package com.project1.project1;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Controller;

@Controller
public class mydbheathservice implements HealthIndicator
{
    public boolean isHealthGood(){
        return true;
    }
    @Override
    public Health health() {
        if (isHealthGood()) {
            return Health.up().withDetail("Database service", "is running up ").build();
        }
        return Health.down().withDetail("Database service","is running down").build();
    }
}