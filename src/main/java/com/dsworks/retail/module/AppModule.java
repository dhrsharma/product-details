package com.dsworks.retail.module;

import com.datastax.driver.core.Session;
import com.dsworks.retail.api.RetailStore;
import com.dsworks.retail.config.AppConfiguration;
import com.dsworks.retail.model.Product;
import com.dsworks.retail.model.RetailProductAttributes;
import com.dsworks.retail.service.RetailService;
import com.dsworks.retail.store.RetailStoreImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;

import javax.ws.rs.client.Client;


public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(RetailService.class);
        bind(new TypeLiteral<RetailStore<Product>>() {
        }).to(RetailStoreImpl.class);
    }

    /**
     * @param config
     * @param environment
     * @return Session
     */
    @Singleton
    @Provides
    public Session getCassandraSession(final AppConfiguration config, final Environment
            environment) {
        return config.getCassandraFactory().build(environment).connect(RetailProductAttributes
                                                                               .REATIL_KEYSPACE.getValue());
    }

    /**
     * @param config
     * @param environment
     * @return
     */
    @Singleton
    @Provides
    public Client getClient(final AppConfiguration config, final Environment
            environment) {
        return new JerseyClientBuilder(environment).using(config.getJerseyClient()).build("Retail-Client");
    }


}
