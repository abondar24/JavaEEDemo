package org.abondar.experimental.javaeedemo.ormdemo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
@SecondaryTables({
        @SecondaryTable(name="city"),
        @SecondaryTable(name="country")
})
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String street;

    private String state;

    @NotNull
    private  String city;

    public Address( String street, String state, String city,  String zipcode, String country) {
        this.street = street;
        this.state = state;
        this.city = city;
        this.zipcode = zipcode;
        this.country = country;
    }

    @NotNull
    private String zipcode;

    private String country;

    public Address(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
