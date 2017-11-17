package org.abondar.experimental.javaeedemo.ormdemo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/*
* Version of News class with embedded key
* */
@Entity
@Table(name="news_e")
public class NewsEmbed {

    @EmbeddedId
    private NewsIdEmbed id;

    private String content;

    public NewsEmbed(){}

    public NewsEmbed(NewsIdEmbed id, String content) {
        this.id = id;
        this.content = content;
    }

    public NewsIdEmbed getId() {
        return id;
    }

    public void setId(NewsIdEmbed id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NewsEmbed{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
