package com.github.juliocesarscheidt.infra.controller;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import javax.enterprise.context.ApplicationScoped;

@Readiness
@ApplicationScoped
public class ReadinessHealthCheck implements HealthCheck {

    // @Readiness - the readiness check accessible at http://localhost:8080/q/health/ready

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("readiness-probe")
              .up()
              .withData("status", "ready")
              .build();
    }
}
