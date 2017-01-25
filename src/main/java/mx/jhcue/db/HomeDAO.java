package mx.jhcue.db;

import io.dropwizard.hibernate.AbstractDAO;
import mx.jhcue.core.HomeEntity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * Created by horacio on 25/01/17.
 */
public class HomeDAO extends AbstractDAO<HomeEntity> {

    public HomeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public HomeEntity findById(@NotNull Long id) {
        return get(id);
    }

    public List<HomeEntity> list() {
        Criteria criteria = criteria();
        return super.list(criteria);
    }

    public HomeEntity save(HomeEntity homeEntity) {
        return persist(homeEntity);
    }

    public void flush() {
        currentSession().flush();;
    }
}
