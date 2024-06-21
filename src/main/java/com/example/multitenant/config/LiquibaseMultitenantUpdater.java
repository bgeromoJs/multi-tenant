package com.example.multitenant.config;

import com.example.multitenant.config.multitenant.TenantSchemaResolver;
import com.example.multitenant.config.multitenant.database.DataSourceBasedMultiTenantConnectionProviderImpl;
import com.example.multitenant.config.multitenant.database.TenantDataSource;
import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.LabelExpression;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.database.jvm.JdbcConnection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LiquibaseMultitenantUpdater {

    private final DataSourceBasedMultiTenantConnectionProviderImpl connectionProvider;
    private final TenantDataSource tenantDataSource;

    public LiquibaseMultitenantUpdater(DataSourceBasedMultiTenantConnectionProviderImpl connectionProvider,
                                       TenantDataSource tenantDataSource) {
        this.connectionProvider = connectionProvider;
        this.tenantDataSource = tenantDataSource;
    }

    public void updateDatabaseForTenant(String tenantId) throws SQLException, LiquibaseException {
        DataSource dataSource = connectionProvider.selectDataSource(tenantId);
        if (dataSource == null) {
            throw new IllegalArgumentException("No DataSource found for tenant: " + tenantId);
        }
        try (Connection connection = dataSource.getConnection()) {
            // Configure o schema do inquilino
            connection.setSchema(tenantId);
            String changelogPath = String.format("db/changelog/changes/%s/changelog-master.json", tenantId);
            // Configure e execute Liquibase
            Liquibase liquibase = new Liquibase(changelogPath, new ClassLoaderResourceAccessor(), new JdbcConnection(connection));
            liquibase.update(new Contexts(), new LabelExpression());
        }
    }

    public void updateAllTenants() throws SQLException {
        List<String> tenantIds = getAllTenantIds(); // Implementar método para obter todos os IDs dos inquilinos
        for (String tenantId : tenantIds) {
            try {
                updateDatabaseForTenant(tenantId);
            } catch (LiquibaseException e) {
                // Tratar exceção
                e.printStackTrace();
            }
        }
    }

    private List<String> getAllTenantIds() {
        // Implemente a lógica para obter todos os IDs dos inquilinos
        return tenantDataSource.getDataSourceNames();
    }
}