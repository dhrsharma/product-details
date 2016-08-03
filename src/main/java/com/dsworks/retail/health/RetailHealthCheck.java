package com.dsworks.retail.health;

import com.hubspot.dropwizard.guice.InjectableHealthCheck;


public class RetailHealthCheck extends InjectableHealthCheck {

    @Override
    protected Result check() throws Exception
    {
        return Result.healthy();
    }

    @Override
    public String getName()
    {
        return "RetailHealth";
    }
}
