package org.abondar.experimental.javaeedemo.ormdemo;

import javax.persistence.*;
import java.util.Arrays;

@Entity
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private float duration;

    @Basic(fetch=FetchType.LAZY)
    @Lob
    private byte[] wav;

    private String description;

    public Track(String title, float duration, String description) {
        this.title = title;
        this.duration = duration;
        this.description = description;
    }

    public Track(){}

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

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public byte[] getWav() {
        return wav;
    }

    public void setWav(byte[] wav) {
        this.wav = wav;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", wav=" + Arrays.toString(wav) +
                ", description='" + description + '\'' +
                '}';
    }
}
