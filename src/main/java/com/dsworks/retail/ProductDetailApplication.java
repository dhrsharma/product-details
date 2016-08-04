package com.dsworks.retail;

import com.dsworks.retail.config.AppConfiguration;
import com.dsworks.retail.module.AppModule;
import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


public class ProductDetailApplication extends Application<AppConfiguration> {

    public static void main(String[] args) throws Exception {
        new ProductDetailApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        GuiceBundle<AppConfiguration> guiceBundle = GuiceBundle.<AppConfiguration>newBuilder()
                .addModule(new AppModule())
                .setConfigClass(AppConfiguration.class)
                .enableAutoConfig(getClass().getPackage().getName())
                .build(Stage.DEVELOPMENT);
        bootstrap.addBundle(guiceBundle);

    }

    @Override
    public void run(AppConfiguration appConfiguration, Environment environment) throws Exception {

    }
}
