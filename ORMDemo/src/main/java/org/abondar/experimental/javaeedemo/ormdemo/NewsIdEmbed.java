package org.abondar.experimental.javaeedemo.ormdemo;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class NewsIdEmbed implements Serializable{

    private String title;
    private String language;

    public NewsIdEmbed(String title, String language) {
        this.title = title;
        this.language = language;
    }

    public NewsIdEmbed(){}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsIdEmbed newsId = (NewsIdEmbed) o;

        if (!title.equals(newsId.title)) return false;
        return language.equals(newsId.language);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + language.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "NewsIdEmbed{" +
                "title='" + title + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
