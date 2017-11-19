package org.abondar.experimental.javaeedemo.ormdemo;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
* Version of News class with IdClass
* */
@Entity
@Table(name = "news")
@IdClass(NewsId.class)
public class News {

    @Id
    private String title;

    @Id
    private String language;

    private String content;

    @OneToMany(fetch = FetchType.EAGER)
    @OrderBy("postedDate DESC")
    private List<Comment> comments;

    public News(String title, String language, String content) {
        this.title = title;
        this.language = language;
        this.content = content;
    }



    public News(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        if (comments == null)
            comments = new ArrayList<>();
        comments.add(comment);
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
