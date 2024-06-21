package com.example.multitenant.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class LiquibaseRunner {

    private final LiquibaseMultitenantUpdater liquibaseMultitenantUpdater;

    public LiquibaseRunner(LiquibaseMultitenantUpdater liquibaseMultitenantUpdater) {
        this.liquibaseMultitenantUpdater = liquibaseMultitenantUpdater;
    }

    @PostConstruct
    public void runLiquibase() {
        try {
            liquibaseMultitenantUpdater.updateAllTenants();
        } catch (SQLException e) {
            // Handle exception
            e.printStackTrace();
        }
    }
}
