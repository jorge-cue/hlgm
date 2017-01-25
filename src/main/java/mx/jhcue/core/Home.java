package mx.jhcue.core;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * Created by horacio on 25/01/17.
 */
@Entity
@Table(name = "homes")
public class Home {

    @Id
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



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetAddressLine1() {
        return streetAddressLine1;
    }

    public void setStreetAddressLine1(String streetAddressLine1) {
        this.streetAddressLine1 = streetAddressLine1;
    }

    public String getStreetAddressLine2() {
        return streetAddressLine2;
    }

    public void setStreetAddressLine2(String streetAddressLine2) {
        this.streetAddressLine2 = streetAddressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
