package com.example.multitenant.config.multitenant.database;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DATASOURCECONFIG_MODULE")
public class DataSourceConfigModule implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idDataSourceConfigModule;

    public Long getIdDataSourceConfigModule() {
        return idDataSourceConfigModule;
    }

    public void setIdDataSourceConfigModule(Long idDataSourceConfigModule) {
        this.idDataSourceConfigModule = idDataSourceConfigModule;
    }

    public DataSourceConfig getDataSourceConfig() {
        return dataSourceConfig;
    }

    public void setDataSourceConfig(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @ManyToOne
    @JoinColumn(name = "datasource_config_id", referencedColumnName = "id")
    private DataSourceConfig dataSourceConfig;

    @ManyToOne
    @JoinColumn(name = "module_id", referencedColumnName = "id")
    private Module module;

    public DataSourceConfig getDatasourceConfig() {
        return dataSourceConfig;
    }

    public void setDatasourceConfig(DataSourceConfig datasourceConfig) {
        this.dataSourceConfig = datasourceConfig;
    }

    public Module getModulos() {
        return module;
    }

    public void setModulos(Module module) {
        this.module = module;
    }
}