package org.abondar.experimental.javaeedemo.ormdemo.model;


import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Table(name="customer")
@Access(AccessType.FIELD)
@Cacheable
//we can use either cacheable from javax.persistence or cache by hibernate
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="Customer")
public class Customer {

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Transient
    private Integer age;

    @OneToOne(fetch=FetchType.LAZY,orphanRemoval = true,cascade = {CascadeType.PERSIST})
    @JoinColumn(name="addr_fk",nullable = false)
    private Address address;

    public Customer(){}

    public Customer(String firstName, String lastName, String email, String phoneNumber,Date dateOfBirth, Date creationDate) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
                ", address=" + address +
                '}';
    }
}
