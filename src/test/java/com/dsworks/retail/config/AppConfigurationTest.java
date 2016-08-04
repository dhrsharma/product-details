package com.dsworks.retail.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.dropwizard.jackson.Jackson;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class AppConfigurationTest extends Assert {

    private final ObjectMapper objectMapper = Jackson.newObjectMapper(new YAMLFactory());

    @Test
    public void hasACassandraFactory() throws Exception {
        final AppConfiguration appConfiguration = parseAppConfiguration();
        assertNotNull("Cassandra factory should not be null.", appConfiguration.getCassandraFactory());
    }

    @Test
    public void hasAJersetClientConfig() throws Exception {
        final AppConfiguration appConfiguration = parseAppConfiguration();
        assertNotNull("JerseyClientConfiguration should not be null.", appConfiguration.getJerseyClient());
    }

    @Test
    public void hasNameServiceURL() throws Exception {
        final AppConfiguration appConfiguration = parseAppConfiguration();
        assertNotNull("NameService is null.", appConfiguration.getNameServiceURL());
    }

    private AppConfiguration parseAppConfiguration() throws Exception {
        return objectMapper.readValue(new File("src/test/resources/test.yml"),
                                      AppConfiguration.class);
    }
}
