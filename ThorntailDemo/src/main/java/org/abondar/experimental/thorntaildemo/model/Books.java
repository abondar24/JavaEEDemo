package org.abondar.experimental.thorntaildemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonRootName("books")
public class Books extends ArrayList<Book> {
    public Books() {
        super();
    }

    public Books(Collection<? extends Book> c) {
        super(c);
    }

    @JsonProperty("bookList")
    private List<Book> getBooks() {
        return this;
    }

    public void setBooks(List<Book> books) {
        this.addAll(books);
    }
}
