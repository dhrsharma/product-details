package com.dsworks.retail.module;

import com.dsworks.retail.api.RetailStore;
import com.dsworks.retail.config.AppConfiguration;
import com.dsworks.retail.service.RetailService;
import com.dsworks.retail.store.RetailStoreImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.dropwizard.setup.Environment;


public class AppModule extends AbstractModule {

    private ManagedCassandraConnector managedCassandraConnector;

    @Override
    protected void configure()
    {
        bind(RetailService.class);
        bind(RetailStore.class).to(RetailStoreImpl.class);
    }

    /**
     * @param config
     * @param environment
     * @return ManagedCassandraConnector
     */
    @Provides
    public ManagedCassandraConnector getCassandraConnector(final AppConfiguration config, final Environment environment)
    {
        if (managedCassandraConnector == null)
        {
            managedCassandraConnector = new ManagedCassandraConnector(environment, config);
        }
        return managedCassandraConnector;
    }
}
