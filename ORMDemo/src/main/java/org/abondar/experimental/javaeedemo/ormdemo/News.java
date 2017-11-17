package org.abondar.experimental.javaeedemo.ormdemo;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

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

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
