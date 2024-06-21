package com.example.multitenant.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class LiquibaseRunnerConfig {

    @Bean
    public ApplicationRunner applicationRunner(LiquibaseMultitenantUpdater liquibaseMultitenantUpdater) {
        return args -> {
            try {
                liquibaseMultitenantUpdater.updateAllTenants();
            } catch (SQLException e) {
                // Handle exception
                e.printStackTrace();
            }
        };
    }

    @Bean
    public CommandLineRunner commandLineRunner(LiquibaseMultitenantUpdater liquibaseMultitenantUpdater) {
        return args -> {
            try {
                liquibaseMultitenantUpdater.updateAllTenants();
            } catch (SQLException e) {
                // Handle exception
                e.printStackTrace();
            }
        };
    }
}
