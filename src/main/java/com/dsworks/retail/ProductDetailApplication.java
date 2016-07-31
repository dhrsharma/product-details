package com.dsworks.retail;

import com.dsworks.retail.config.AppConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by dhruvsharma on 7/30/16.
 */
public class ProductDetailApplication extends Application<AppConfiguration> {

    @Override
    public void run(String... arguments) throws Exception {
        new ProductDetailApplication().run(arguments);
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {

    }

    @Override
    public void run(AppConfiguration appConfiguration, Environment environment) throws Exception {

    }
}
