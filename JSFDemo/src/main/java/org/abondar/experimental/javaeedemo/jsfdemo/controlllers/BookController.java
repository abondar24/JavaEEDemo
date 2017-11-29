package org.abondar.experimental.javaeedemo.jsfdemo.controlllers;

import org.abondar.experimental.javaeedemo.jsfdemo.ejb.BookEJB;
import org.abondar.experimental.javaeedemo.jsfdemo.model.Book;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.Date;

@ManagedBean
public class BookController {

    @Inject
    private BookEJB bookEJB;

    private Book book = new Book();
    private Date currentDate = new Date();


    public String doCreateBook() {
        book = bookEJB.createBook(book);
        System.out.println(book);
        return "listBooks.xhtml";
    }

    public BookEJB getBookEJB() {
        return bookEJB;
    }

    public void setBookEJB(BookEJB bookEJB) {
        this.bookEJB = bookEJB;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}
