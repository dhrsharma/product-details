package com.dsworks.retail.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import systems.composable.dropwizard.cassandra.CassandraFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public class AppConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("cassandra")
    private CassandraFactory cassandra = new CassandraFactory();

    @Valid
    @NotNull
    @JsonProperty("jerseyClient")
    private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();

    @JsonProperty("nameServiceURL")
    private String nameServiceURL;

    @JsonProperty("cassandra")
    public CassandraFactory getCassandraFactory() {
        return cassandra;
    }

    @JsonProperty("cassandra")
    public void setCassandraFactory(CassandraFactory cassandra) {
        this.cassandra = cassandra;
    }

    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration getJerseyClient() {
        return jerseyClient;
    }

    @JsonProperty("jerseyClient")
    public void setJerseyClient(JerseyClientConfiguration jerseyClient) {
        this.jerseyClient = jerseyClient;
    }

    @JsonProperty("nameServiceURL")
    public String getNameServiceURL() {
        return nameServiceURL;
    }

    @JsonProperty("nameServiceURL")
    public void setNameServiceURL(String nameServiceURL) {
        this.nameServiceURL = nameServiceURL;
    }
}
