package org.abondar.experimental.javaeedemo.ormdemo.model;

import java.io.Serializable;

public class NewsId implements Serializable {
    private String title;
    private String language;

    public NewsId(String title, String language) {
        this.title = title;
        this.language = language;
    }

    public NewsId(){}

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

        NewsId newsId = (NewsId) o;

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
