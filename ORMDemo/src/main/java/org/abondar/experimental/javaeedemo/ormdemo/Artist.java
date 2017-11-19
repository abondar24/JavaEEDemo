package org.abondar.experimental.javaeedemo.ormdemo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="artist")
public class Artist {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "artist_cd",
            joinColumns = @JoinColumn(name="artist_fk"),
            inverseJoinColumns = @JoinColumn(name="cd_fk")
    )
    private List<CD> appearsOnCDs;

    public Artist(){}

    public Artist(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public List<CD> getAppearsOnCDs() {
        return appearsOnCDs;
    }

    public void setAppearsOnCDs(List<CD> appearsOnCDs) {
        this.appearsOnCDs = appearsOnCDs;
    }

    public void appearsOn(CD cd) {
        if (appearsOnCDs == null)
            appearsOnCDs = new ArrayList<>();
        appearsOnCDs.add(cd);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", appearsOnCDs=" + appearsOnCDs +
                '}';
    }
}
