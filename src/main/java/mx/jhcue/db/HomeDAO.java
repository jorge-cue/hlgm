package mx.jhcue.db;

import io.dropwizard.hibernate.AbstractDAO;
import mx.jhcue.core.Home;
import org.hibernate.SessionFactory;

/**
 *
 * Created by horacio on 25/01/17.
 */
public class HomeDAO extends AbstractDAO<Home> {

    public HomeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


}
