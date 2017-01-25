package mx.jhcue.resources;

import io.dropwizard.testing.junit.ResourceTestRule;
import mx.jhcue.core.HomeEntity;
import mx.jhcue.db.HomeDAO;
import org.junit.After;
import org.junit.ClassRule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 *
 * Created by horacio on 25/01/17.
 */
public class HomeResourceTest {

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
        HomeEntity expected = new HomeEntity();
        expected.setId(123L);
        expected.setStreetAddressLine1("123 Main St.");
        expected.setStreetAddressLine2("");
        expected.setCity("Greenfield");
        expected.setState("CA");
        expected.setZipCode("12345");
        expected.setCountry("USA");

        when(homeDAO.findById(123L)).thenReturn(expected);

        // Exercise
        HomeEntity actual = resources.client().target("/api/home/123").request().get(HomeEntity.class);

        // Verify
        assertThat(actual).isEqualToComparingFieldByField(expected);
        verify(homeDAO).findById(123L);
    }

    @Test
    public void getNonExistingHome() {
        when(homeDAO.findById(456L)).thenReturn(null);

        // Exercise
        HomeEntity actual = resources.client().target("/api/home/456").request().get(HomeEntity.class);

        // Verify
        assertThat(actual).isNull();
        verify(homeDAO).findById(456L);
    }
}
