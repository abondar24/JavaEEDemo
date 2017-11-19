package org.abondar.experimental.javaeedemo.ormdemo;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "comment_posted_date")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String nickname;

    private String content;

    private Integer note;

    @Column(name="posted_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postedDate;

    public Comment(){}


    public Comment(String nickname, String content, Integer note, String postedDate) {
        this.nickname = nickname;
        this.content = content;
        this.note = note;

        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            this.postedDate = df.parse(postedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                ", note=" + note +
                ", postedDate=" + postedDate +
                '}';
    }
}
