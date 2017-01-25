package mx.jhcue;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HomeLocationsOnGoogleMapsApplication extends Application<HomeLocationsOnGoogleMapsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HomeLocationsOnGoogleMapsApplication().run(args);
    }

    @Override
    public String getName() {
        return "HomeLocationsOnGoogleMaps";
    }

    @Override
    public void initialize(final Bootstrap<HomeLocationsOnGoogleMapsConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final HomeLocationsOnGoogleMapsConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
