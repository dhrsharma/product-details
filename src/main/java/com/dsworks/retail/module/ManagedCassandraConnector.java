package com.dsworks.retail.module;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.dsworks.retail.config.AppConfiguration;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Environment;

public class ManagedCassandraConnector implements Managed {
    public static final String RETAIL_KEYSPACE = "retail";
    private final Environment environment;
    private final AppConfiguration appConfiguration;
    private Cluster cluster;
    private Session session;

    public ManagedCassandraConnector(final Environment environment, final AppConfiguration appConfiguration)
    {
        this.appConfiguration = appConfiguration;
        this.environment = environment;
    }

    public Session getSession()
    {
        return this.session;
    }

    @Override
    public void start() throws Exception
    {
        this.cluster = appConfiguration.getCassandraFactory().build(environment);
        this.session = cluster.connect(RETAIL_KEYSPACE);
    }

    @Override
    public void stop() throws Exception
    {
        if (session != null)
        {
            session.close();
        }
        if (cluster != null)
        {
            cluster.close();
        }
    }
}
