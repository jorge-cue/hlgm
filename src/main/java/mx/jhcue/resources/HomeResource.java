package mx.jhcue.resources;

import mx.jhcue.db.HomeDAO;

import javax.ws.rs.Path;

/**
 *
 * Created by horacio on 25/01/17.
 */
@Path("/api/home")
public class HomeResource {

    private final HomeDAO homeDAO;

    public HomeResource(HomeDAO homeDAO) {
        this.homeDAO = homeDAO;
    }
}
