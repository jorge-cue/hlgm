package mx.jhcue.resources;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import mx.jhcue.HomeLocationsOnGoogleMapsApplication;
import mx.jhcue.HomeLocationsOnGoogleMapsConfiguration;
import mx.jhcue.core.HomeEntity;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 *
 * Created by horacio on 25/01/17.
 */
public class HomeResourceIntegrationTest {


    @ClassRule
    public static final DropwizardAppRule<HomeLocationsOnGoogleMapsConfiguration> RULE =
            new DropwizardAppRule<>(
                    HomeLocationsOnGoogleMapsApplication.class,
                    ResourceHelpers.resourceFilePath("config-test.yml"));

    @Ignore
    @Test
    public void integrationTest() {

        Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test");

        HomeEntity homeEntity = new HomeEntity();
        homeEntity.setStreetAddressLine1("Pedro Enriquez Urena 444");
        homeEntity.setStreetAddressLine1("Pabellon Burdeos 1 402");
        homeEntity.setState("CDMX");
        homeEntity.setCity("Ciudad de Mexico");
        homeEntity.setCountry("Mexico");
        homeEntity.setZipCode("04430");

        Entity<HomeEntity> entity =  Entity.entity(homeEntity, MediaType.APPLICATION_JSON);

        Response response = client.target(String.format("http://localhost:%d/api/homeEntity", RULE.getLocalPort()))
                .request(MediaType.APPLICATION_JSON)
                .post(entity);

        assertThat(response)
                .hasFieldOrPropertyWithValue("status", HttpStatus.ACCEPTED_202);
    }
}
