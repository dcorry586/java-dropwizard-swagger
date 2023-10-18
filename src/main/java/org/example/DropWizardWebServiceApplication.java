package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.example.resources.OrderController;
import org.example.resources.ProductController;

public class DropWizardWebServiceApplication extends Application<DropWizardWebServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropWizardWebServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "true";
    }

    @Override
    public void initialize(final Bootstrap<DropWizardWebServiceConfiguration> bootstrap) {
   bootstrap.addBundle(new SwaggerBundle<DropWizardWebServiceConfiguration>() {
      @Override
       protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(DropWizardWebServiceConfiguration configuration) {
          return configuration.getSwagger();
      }
   });
    }

    @Override
    public void run(final DropWizardWebServiceConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.jersey().register(new OrderController());
        environment.jersey().register(new ProductController());
    }

}
