package mx.jhcue.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import mx.jhcue.api.Home;
import mx.jhcue.core.HomeEntity;
import mx.jhcue.db.HomeDAO;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Created by horacio on 25/01/17.
 */
@Path("/api/home")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HomeResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeResource.class);

    private final HomeDAO homeDAO;

    public HomeResource(HomeDAO homeDAO) {
        this.homeDAO = homeDAO;
    }

    @GET
    @Path("/{id}")
    @Timed
    @UnitOfWork(readOnly = true)
    public Home getHome(@PathParam("id") @NotNull Long id) {
        HomeEntity e = homeDAO.findById(id);
        if (e == null) {
            return null;
        }
        return new Home(e.getId(), e.getStreetAddressLine1(), e.getStreetAddressLine2(), e.getCity(), e.getState(), e.getZipCode(), e.getCountry());
    }

    @GET
    @Timed
    @UnitOfWork(readOnly = true)
    public List<Home> getAll() {
        List<HomeEntity> homes = homeDAO.list();
        List<Home> response = homes
                .stream()
                .map(e -> new Home(e.getId(), e.getStreetAddressLine1(), e.getStreetAddressLine2(), e.getCity(), e.getState(), e.getZipCode(), e.getCountry()))
                .collect(Collectors.toList());
        return response;
    }

    @POST
    @Timed
    @UnitOfWork
    public Response post(@NotNull @Valid Home home) {
        LOGGER.debug("post {}", home);

        HomeEntity entity = new HomeEntity();
        entity.setStreetAddressLine1(home.getStreetAddressLine1());
        entity.setStreetAddressLine2(home.getStreetAddressLine2());
        entity.setCity(home.getCity());
        entity.setState(home.getState());
        entity.setZipCode(home.getZipCode());
        entity.setCountry(home.getCountry());

        LOGGER.debug("About to persist {}", entity);
        HomeEntity newHome = homeDAO.save(entity);
        LOGGER.debug("Persisted {} from {}", newHome, entity);

        return Response.created(UriBuilder.fromPath("/api/home/{id}").build(newHome.getId())).status(HttpStatus.ACCEPTED_202).build();
    }

    @PUT
    @Path("/{id}")
    @Timed
    @UnitOfWork
    public Response put(@PathParam("id") @NotNull Long id, @NotNull @Valid Home home) {
        LOGGER.debug("put id {}, home {}", id, home);
        if (! id.equals(home.getId())) {
            return Response.created(UriBuilder.fromResource(HomeResource.class).build(id, home.getId())).status(HttpStatus.CONFLICT_409).build();
        }
        HomeEntity entity = homeDAO.findById(id);
        if (entity == null) {
            LOGGER.debug("Entity with id {} Not found", id);
            return Response.created(UriBuilder.fromResource(HomeResource.class).build(id, home.getId())).status(HttpStatus.NOT_FOUND_404).build();
        }
        entity.setStreetAddressLine1(home.getStreetAddressLine1());
        entity.setStreetAddressLine2(home.getStreetAddressLine2());
        entity.setCity(home.getCity());
        entity.setState(home.getState());
        entity.setZipCode(home.getZipCode());
        entity.setCountry(home.getCountry());
        LOGGER.debug("About to persist {}", entity);
        HomeEntity newHome = homeDAO.save(entity);
        LOGGER.debug("Persisted {} from {}", newHome, entity);
        return Response.created(UriBuilder.fromPath("/api/home/{id}").build(newHome.getId())).status(HttpStatus.ACCEPTED_202).build();
    }
}
