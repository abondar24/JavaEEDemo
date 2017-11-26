package org.abondar.experimental.javaeedemo.jsfdemo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="cd")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "cd_id")),
        @AttributeOverride(name = "title", column = @Column(name = "cd_title")),
        @AttributeOverride(name = "description", column = @Column(name = "cd_description"))
})
@NamedQueries({
        @NamedQuery(name = "findAllCDs", query = "SELECT cd FROM CD cd")
})
public class CD  extends Item  implements Serializable{



    private String musicCompany;
    private Integer numberOfCDs;
    private Float totalDuration;
    private String genre;


    public CD(){}

    public CD(String title, Float price, String description) {
        super(title,price,description);
    }

    public CD(String title, Float price,String description, String musicCompany,
              Integer numberOfCDs, Float totalDuration, String genre) {
        super(title, price, description);
        this.musicCompany = musicCompany;
        this.numberOfCDs = numberOfCDs;
        this.totalDuration = totalDuration;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMusicCompany() {
        return musicCompany;
    }

    public void setMusicCompany(String musicCompany) {
        this.musicCompany = musicCompany;
    }

    public Integer getNumberOfCDs() {
        return numberOfCDs;
    }

    public void setNumberOfCDs(Integer numberOfCDs) {
        this.numberOfCDs = numberOfCDs;
    }

    public Float getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Float totalDuration) {
        this.totalDuration = totalDuration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "CD{" +
                "musicCompany='" + musicCompany + '\'' +
                ", numberOfCDs=" + numberOfCDs +
                ", totalDuration=" + totalDuration +
                ", genre='" + genre + '\'' +
                '}';
    }
}
