package org.abondar.experimental.javaeedemo.jsfdemo.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "book")
@NamedQueries({
        @NamedQuery(name = "findAllBooks", query = "SELECT b FROM Book b")
})
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "book_id")),
        @AttributeOverride(name = "title", column = @Column(name = "book_title")),
        @AttributeOverride(name = "description", column = @Column(name = "book_description"))
})
public class Book extends Item implements Serializable {

    @NotNull
    private String number;

    @Column(name = "num_pages", nullable = false)
    private Integer numberOfPages;

    private String publisher;

    private Boolean illustrations;


    private Date publishedDate;

    @Version
    private Integer version;


    public Book() {
    }

    public Book(String title, Float price, String description) {

        super(title, price, description);

    }

    public Book(String title, Float price, String description, String number, Integer numberOfPages, Boolean illustrations) {
        super(title, price, description);
        this.number = number;
        this.numberOfPages = numberOfPages;
        this.illustrations = illustrations;
    }

    public Book(String title, Float price, String description, String number, Integer numberOfPages, String publisher, Boolean illustrations, Date publishedDate, Integer version) {
        super(title, price, description);
        this.number = number;
        this.numberOfPages = numberOfPages;
        this.publisher = publisher;
        this.illustrations = illustrations;
        this.publishedDate = publishedDate;
        this.version = version;
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


    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public String toString() {
        return super.toString()+"Book{" +
                "number='" + number + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", publisher='" + publisher + '\'' +
                ", illustrations=" + illustrations +
                ", version=" + version +
                '}';
    }
}
