package mx.jhcue.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import mx.jhcue.core.Home;
import mx.jhcue.db.HomeDAO;
import org.eclipse.jetty.http.HttpStatus;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

/**
 *
 * Created by horacio on 25/01/17.
 */
@Path("/api/home")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HomeResource {

    private final HomeDAO homeDAO;

    public HomeResource(HomeDAO homeDAO) {
        this.homeDAO = homeDAO;
    }

    @GET
    @Path("/{id}")
    @Timed
    @UnitOfWork
    public Home getHome(@PathParam("id") @NotNull Long id) {
        return homeDAO.findById(id);
    }

    @GET
    @Timed
    @UnitOfWork
    public List<Home> getAll() {
        return homeDAO.list();
    }

    @POST
    @Timed
    @UnitOfWork
    public Response post(@NotNull @Valid Home home) {
        Home newHome = homeDAO.save(home);
        return Response.created(UriBuilder.fromResource(HomeResource.class).build(newHome.getId())).build();
    }

    @PUT
    @Path("/{id}")
    @Timed
    @UnitOfWork
    public Response put(@PathParam("id") @NotNull Long id, @NotNull @Valid Home home) {
        if (! id.equals(home.getId())) {
            return Response.created(UriBuilder.fromResource(HomeResource.class).build(id, home.getId())).status(HttpStatus.CONFLICT_409).build();
        }
        Home newHome = homeDAO.save(home);
        return Response.created(UriBuilder.fromResource(HomeResource.class).build(newHome.getId())).status(HttpStatus.ACCEPTED_202).build();
    }
}
