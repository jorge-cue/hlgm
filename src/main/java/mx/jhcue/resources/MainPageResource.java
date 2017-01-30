package mx.jhcue.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import mx.jhcue.api.Home;
import mx.jhcue.core.HomeEntity;
import mx.jhcue.db.HomeDAO;
import mx.jhcue.views.HomeView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Created by horacio on 28/01/17.
 */
@Path("/")
@Produces(MediaType.TEXT_HTML)
public class MainPageResource {

    private final HomeDAO homeDAO;

    public MainPageResource(HomeDAO homeDAO) {
        this.homeDAO = homeDAO;
    }

    @GET
    @UnitOfWork(readOnly = true)
    @Timed
    public HomeView index() {
        List<HomeEntity> homeEntities = homeDAO.list();
        List<Home> homes = homeEntities.stream()
                .map(h -> new Home(h.getId(), h.getStreetAddressLine1(), h.getStreetAddressLine1(), h.getCity(), h.getState(), h.getZipCode(), h.getCountry()))
                .collect(Collectors.toList());
        return new HomeView(homes);
    }
}
