package org.abondar.experimental.javaeedemo.ormdemo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Table(name="customer_emb")
@Access(AccessType.FIELD)
public class CustomerWithEmbedAddr {

    @Id
    @GeneratedValue
    private Long id;


    @NotNull
    @Column(name="first_name",nullable = false,length=50)
    private String firstName;

    @Column(name="last_name",nullable = false,length=50)
    private String lastName;

    private String email;

    @Column(name="phone_number",length = 15)
    private String phoneNumber;

    @Past
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Transient
    private Integer age;


    @Embedded
    private AddressEmbed address;

    public CustomerWithEmbedAddr(){}

    public CustomerWithEmbedAddr(String firstName, String lastName, String email, String phoneNumber, Date dateOfBirth, Date creationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.creationDate = creationDate;
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

    public AddressEmbed getAddress() {
        return address;
    }

    public void setAddress(AddressEmbed address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CustomerWithEmbedAddr{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", creationDate=" + creationDate +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
