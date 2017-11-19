package org.abondar.experimental.javaeedemo.ormdemo;

import javax.persistence.*;


@Embeddable
@Access(AccessType.PROPERTY)
public class AddressEmbed {

    private String street;

    private String state;

    private  String city;

    public AddressEmbed( String street, String state, String city,  String zipcode, String country) {
        this.street = street;
        this.state = state;
        this.city = city;
        this.zipcode = zipcode;
        this.country = country;
    }

    public AddressEmbed(){}

    private String zipcode;

    private String country;


    @Column(nullable = false)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(length = 3)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(nullable = false, length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "zip_code", length = 10)
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
        return "AddressEmbed{" +
                "street='" + street + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
