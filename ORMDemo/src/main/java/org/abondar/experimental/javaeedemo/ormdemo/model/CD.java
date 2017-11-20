package org.abondar.experimental.javaeedemo.ormdemo.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="cd")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "cd_id")),
        @AttributeOverride(name = "title", column = @Column(name = "cd_title")),
        @AttributeOverride(name = "description", column = @Column(name = "cd_description"))
})
public class CD  extends Item {


    private String musicCompany;
    private Integer numberOfCDs;
    private Float totalDuration;
    private String genre;


    @Lob
    private byte[] cover;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="cd_track")
    @MapKeyColumn(name="position")
    @Column(name="title")
    private Map<Integer,String> tracks = new HashMap<>();

    @ManyToMany(mappedBy = "appearsOnCDs")
    private List<Artist> createdByArtists;

    public CD(){}

    public CD(String title, Float price, String description) {
        super(title,price,description);
    }


    public CD(String title, Float price, String description, Map<Integer, String> tracks) {
        super(title,price,description);
        this.tracks = tracks;
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

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public Map<Integer, String> getTracks() {
        return tracks;
    }

    public void setTracks(Map<Integer, String> tracks) {
        this.tracks = tracks;
    }

    public void setCreatedByArtists(List<Artist> createdByArtists) {
        this.createdByArtists = createdByArtists;
    }

    public List<Artist> getCreatedByArtists() {
        return createdByArtists;
    }

    public void createdBy(Artist artist) {
        if (createdByArtists == null)
            createdByArtists = new ArrayList<>();
        createdByArtists.add(artist);
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
                ", cover=" + Arrays.toString(cover) +
                ", tracks=" + tracks +
                ", createdByArtists=" + createdByArtists +
                '}';
    }
}
