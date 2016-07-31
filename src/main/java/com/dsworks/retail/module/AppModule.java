package com.dsworks.retail.module;

import com.dsworks.retail.store.RetailStore;
import com.dsworks.retail.store.RetailStoreImpl;
import com.google.inject.AbstractModule;


public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(RetailStore.class).to(RetailStoreImpl.class);
    }

    /*@Provides
    public Cluster getCluster(final AppConfiguration config, final Environment environment) {
        return config.getCassandraFactory().build(environment);
    }*/
}
