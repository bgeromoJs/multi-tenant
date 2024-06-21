package com.example.multitenant.config;

import com.example.multitenant.config.multitenant.TenantSchemaResolver;
import com.example.multitenant.config.multitenant.database.DataSourceBasedMultiTenantConnectionProviderImpl;
import com.example.multitenant.config.multitenant.database.TenantDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LiquibaseConfig {

    @Bean
    public LiquibaseMultitenantUpdater liquibaseMultitenantUpdater(
            DataSourceBasedMultiTenantConnectionProviderImpl connectionProvider,
            TenantDataSource tenantDataSource) {
        return new LiquibaseMultitenantUpdater(connectionProvider, tenantDataSource);
    }
}