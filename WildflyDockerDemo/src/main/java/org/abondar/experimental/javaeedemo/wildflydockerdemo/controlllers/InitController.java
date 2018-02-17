package org.abondar.experimental.javaeedemo.wildflydockerdemo.controlllers;

import org.abondar.experimental.javaeedemo.wildflydockerdemo.model.Book;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;

@ManagedBean
@ApplicationScoped
public class InitController {

    private Book defaultBook;

    @PostConstruct
    private void init(){
        defaultBook = new Book("title",0F,"default description");
    }

    public Book getDefaultBook() {
        return defaultBook;
    }

    public void setDefaultBook(Book defaultBook) {
        this.defaultBook = defaultBook;
    }
}
