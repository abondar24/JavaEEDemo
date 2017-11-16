package org.abondar.experimental.javaeedemo.jpademo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
        @NamedQuery(name="findAllBooks", query = "SELECT b FROM Book b"),
        @NamedQuery(name = "findBookCars",query = "SELECT b FROM Book b WHERE b.title ='Cars'")
})
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String title;

    private Float price;

    @Size(min = 10,max=20000)
    private String description;

    private String number;

    private Integer numberOfPages;

    private Boolean illustrations;

    public Book(){}

    public Book(String title, Float price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public Book(String title, Float price,String description, String number, Integer numberOfPages, Boolean illustrations) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.number = number;
        this.numberOfPages = numberOfPages;
        this.illustrations = illustrations;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Boolean getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(Boolean illustrations) {
        this.illustrations = illustrations;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", number='" + number + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", illustrations=" + illustrations +
                '}';
    }
}
