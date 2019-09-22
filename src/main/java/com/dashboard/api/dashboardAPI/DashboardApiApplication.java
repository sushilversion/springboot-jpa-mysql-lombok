package com.dashboard.api.dashboardAPI;

import com.dashboard.api.dashboardAPI.audit.AuditorAwareImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")

public class DashboardApiApplication {
	@Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }


	public static void main(String[] args) {
		SpringApplication.run(DashboardApiApplication.class, args);
	}

}
