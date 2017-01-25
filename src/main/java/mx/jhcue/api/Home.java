package mx.jhcue.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 *
 * Created by horacio on 25/01/17.
 */
public class Home {

    private Long id;
    @NotNull
    @Length(max = 60)
    private String streetAddressLine1;
    @NotNull
    @Length(max = 60)
    private String streetAddressLine2;
    @NotNull
    @Length(max = 60)
    private String city;
    @NotNull
    @Length(max = 5)
    private String state;
    @NotNull
    @Length(max = 5)
    private String zipCode;
    @NotNull
    @Length(max = 30)
    private String country;

    public Home() {
        this(null, null, null, null, null, null, null);
    }

    public Home(Long id, String streetAddressLine1, String streetAddressLine2, String city, String state, String zipCode, String country) {
        this.id = id;
        this.streetAddressLine1 = streetAddressLine1;
        this.streetAddressLine2 = streetAddressLine2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Home(String streetAddressLine1, String streetAddressLine2, String city, String state, String zipCode, String country) {
        this(null, streetAddressLine1, streetAddressLine2, city, state, zipCode, country);
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonProperty
    public String getStreetAddressLine1() {
        return streetAddressLine1;
    }

    @JsonProperty
    public String getStreetAddressLine2() {
        return streetAddressLine2;
    }

    @JsonProperty
    public String getCity() {
        return city;
    }

    @JsonProperty
    public String getState() {
        return state;
    }

    @JsonProperty
    public String getZipCode() {
        return zipCode;
    }

    @JsonProperty
    public String getCountry() {
        return country;
    }

    @JsonProperty
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty
    public void setStreetAddressLine1(String streetAddressLine1) {
        this.streetAddressLine1 = streetAddressLine1;
    }

    @JsonProperty
    public void setStreetAddressLine2(String streetAddressLine2) {
        this.streetAddressLine2 = streetAddressLine2;
    }

    @JsonProperty
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @JsonProperty
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
