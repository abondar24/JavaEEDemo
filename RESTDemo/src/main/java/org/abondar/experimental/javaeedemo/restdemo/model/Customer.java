package org.abondar.experimental.javaeedemo.restdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@Table(name="customer")
@Access(AccessType.FIELD)
@NamedQueries({
        @NamedQuery(name = "findCustomers", query = "SELECT c FROM Customer c")
})
@XmlRootElement
@JsonIgnoreProperties(value = { "dateOfBirth","creationDate" })
public class Customer {

    @Id
    @GeneratedValue
    private Long id;


    @NotNull
    @Column(name="first_name",nullable = false,length=50)
    @JsonProperty("first_name")
    private String firstName;

    @Column(name="last_name",nullable = false,length=50)
    @JsonProperty("last_name")
    private String lastName;

    private String email;

    @Column(name="phone_number",length = 15)
    @JsonProperty("phone_number")
    private String phoneNumber;

    @Past
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Transient
    private Integer age;

    private String zipcode;

    private String city;

    public Customer(){}

    public Customer(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Customer(String firstName, String lastName, String email, String phoneNumber, Date dateOfBirth, Date creationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.creationDate = creationDate;
    }

    public Customer(String firstName, String lastName, String email, String phoneNumber, Date dateOfBirth, Date creationDate, Integer age, String zipcode, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.creationDate = creationDate;
        this.age = age;
        this.zipcode = zipcode;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //length of 15 is overwritten by 50 because of entity annotation
    @Access(AccessType.PROPERTY)
    @Column(name="phone_number",length = 50)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", creationDate=" + creationDate +
                ", age=" + age +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
