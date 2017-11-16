package org.abondar.experimental.javaeedemo.beanvalidation;

import javax.validation.constraints.NotNull;

public class Address {

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
    @ZipCode
    private String zipcode;

    private String country;

    public Address(){}



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
                "street='" + street + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
