package mx.jhcue.views;

import io.dropwizard.views.View;
import mx.jhcue.api.Home;

import java.util.List;

/**
 *
 * Created by horacio on 28/01/17.
 */
public class HomeView extends View {

    private List<Home> homes;

    public HomeView(List<Home> homes) {
        super("index.ftl");
        this.homes = homes;
    }

    public List<Home> getHomes() {
        return homes;
    }
}
