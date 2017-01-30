package mx.jhcue.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.junit.ResourceTestRule;
import mx.jhcue.api.Home;
import mx.jhcue.core.HomeEntity;
import mx.jhcue.db.HomeDAO;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.After;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.RuntimeDelegate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 *
 * Created by horacio on 25/01/17.
 */
public class HomeResourceTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    private static final HomeDAO homeDAO = mock(HomeDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new HomeResource(homeDAO))
            .build();

    @After
    public void tearDown() {
        reset(homeDAO);
    }

    @Test
    public void getAHome() {
        // Prepare
        HomeEntity entity = new HomeEntity();
        entity.setId(123L);
        entity.setStreetAddressLine1("123 Main St.");
        entity.setStreetAddressLine2("");
        entity.setCity("Greenfield");
        entity.setState("CA");
        entity.setZipCode("12345");
        entity.setCountry("USA");

        when(homeDAO.findById(123L)).thenReturn(entity);

        // Exercise
        Home actual = resources.client().target("/api/home/123").request().get(Home.class);

        // Verify
        assertThat(actual).isEqualToComparingFieldByField(entity);
        verify(homeDAO).findById(123L);
    }

    @Test
    public void getNonExistingHome() {
        when(homeDAO.findById(456L)).thenReturn(null);

        // Exercise
        Home actual = resources.client().target("/api/home/456").request().get(Home.class);

        // Verify
        assertThat(actual).isNull();
        verify(homeDAO).findById(456L);
    }

    @Test
    public void post() {

        Home home = new Home()
                .withStreetAddress1("Address Line 1")
                .withStreetAddress2("Address Line 2")
                .withCity("City")
                .withState("ST")
                .withZipCode("12345")
                .withCountry("USA");

        HomeEntity persisted = new HomeEntity();
        persisted.setId(1L);
        persisted.setStreetAddressLine1(home.getStreetAddressLine1());
        persisted.setStreetAddressLine2(home.getStreetAddressLine2());
        persisted.setCity(home.getCity());
        persisted.setState(home.getState());
        persisted.setZipCode(home.getZipCode());
        persisted.setCountry(home.getCountry());

        when(homeDAO.save(any(HomeEntity.class))).thenReturn(persisted);

        Entity<Home> entity = Entity.json(home);

        Response response = resources.client().target("/api/home").request(MediaType.APPLICATION_JSON).post(entity);

        assertThat(response)
                .isNotNull()
                .hasFieldOrPropertyWithValue("status", HttpStatus.ACCEPTED_202);

        assertThat(response.getHeaderString(HttpHeaders.LOCATION))
                .endsWith("/api/home/1");

        ArgumentCaptor<HomeEntity> captor = ArgumentCaptor.forClass(HomeEntity.class);

        verify(homeDAO).save(captor.capture());

        HomeEntity toPersist = captor.getValue();

        assertThat(toPersist)
                .isNotNull();

        toPersist.setId(persisted.getId());

        assertThat(toPersist)
                .isEqualToComparingFieldByField(persisted);
    }
}
