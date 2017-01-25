package mx.jhcue.db;

import io.dropwizard.hibernate.AbstractDAO;
import mx.jhcue.core.Home;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * Created by horacio on 25/01/17.
 */
public class HomeDAO extends AbstractDAO<Home> {

    public HomeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Home findById(@NotNull Long id) {
        return get(id);
    }

    public List<Home> list() {
        Criteria criteria = criteria();
        return super.list(criteria);
    }

    public Home save(Home home) {
        return persist(home);
    }
}
