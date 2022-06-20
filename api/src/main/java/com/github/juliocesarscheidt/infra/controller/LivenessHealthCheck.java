package com.github.juliocesarscheidt.infra.controller;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import javax.enterprise.context.ApplicationScoped;

@Liveness
@ApplicationScoped
public class LivenessHealthCheck implements HealthCheck {

    // @Liveness - the liveness check accessible at http://localhost:8080/q/health/live

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("liveness-probe")
              .up()
              .withData("status", "alive")
              .build();
    }
}
