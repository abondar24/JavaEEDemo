package org.abondar.experimental.javaeedemo.ormdemo.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")
@NamedQueries({
        @NamedQuery(name = "findAllBooks", query = "SELECT b FROM Book b"),
        @NamedQuery(name = "findBookCars", query = "SELECT b FROM Book b WHERE b.title ='Cars'")
})
@NamedStoredProcedureQuery(name = "archiveOldBooks", procedureName = "archive_books",
        parameters = {
             @StoredProcedureParameter(name="archiveDate",mode=ParameterMode.IN,type=Date.class),
             @StoredProcedureParameter(name="warehouse",mode=ParameterMode.IN,type=String.class),

        },
        resultClasses = Book.class)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "book_id")),
        @AttributeOverride(name = "title", column = @Column(name = "book_title")),
        @AttributeOverride(name = "description", column = @Column(name = "book_description"))
})
@Cacheable(false)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region="Book")
public class Book extends Item {

    @NotNull
    private String number;

    @Column(name = "num_pages", nullable = false)
    private Integer numberOfPages;

    private String publisher;

    private Boolean illustrations;

    @Version
    private Integer version;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "tag")
    @Column(name = "value")
    private List<String> tags = new ArrayList<>();

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

    public Book(String title, Float price, String description, String number, Integer numberOfPages, Boolean illustrations, List<String> tags) {
        super(title, price, description);
        this.number = number;
        this.numberOfPages = numberOfPages;
        this.illustrations = illustrations;
        this.tags = tags;
    }

    public Book(String title, Float price, String description, String number, Integer numberOfPages, String publisher, Boolean illustrations) {
        super(title, price, description);
        this.number = number;
        this.numberOfPages = numberOfPages;
        this.publisher = publisher;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
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

    @Override
    public String toString() {
        return "Book{" +
                "number='" + number + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", publisher='" + publisher + '\'' +
                ", illustrations=" + illustrations +
                ", tags=" + tags +
                '}';
    }
}
