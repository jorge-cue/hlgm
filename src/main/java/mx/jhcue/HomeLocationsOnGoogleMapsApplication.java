package mx.jhcue;

import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import mx.jhcue.core.HomeEntity;
import mx.jhcue.db.HomeDAO;
import mx.jhcue.resources.HomeResource;

public class HomeLocationsOnGoogleMapsApplication extends Application<HomeLocationsOnGoogleMapsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HomeLocationsOnGoogleMapsApplication().run(args);
    }

    @Override
    public String getName() {
        return "HomeLocationsOnGoogleMaps";
    }

    private final HibernateBundle<HomeLocationsOnGoogleMapsConfiguration> hibernate = new HibernateBundle<HomeLocationsOnGoogleMapsConfiguration>(HomeEntity.class) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(HomeLocationsOnGoogleMapsConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(final Bootstrap<HomeLocationsOnGoogleMapsConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final HomeLocationsOnGoogleMapsConfiguration configuration,
                    final Environment environment) {

        final HomeDAO homeDAO = new HomeDAO(hibernate.getSessionFactory());

        environment.jersey().register(new HomeResource(homeDAO));
    }
}
