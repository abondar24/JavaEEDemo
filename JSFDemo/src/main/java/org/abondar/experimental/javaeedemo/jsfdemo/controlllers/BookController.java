package org.abondar.experimental.javaeedemo.jsfdemo.controlllers;

import org.abondar.experimental.javaeedemo.jsfdemo.ejb.BookEJB;
import org.abondar.experimental.javaeedemo.jsfdemo.model.Book;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@ManagedBean
@RequestScoped
public class BookController {

    @Inject
    private BookEJB bookEJB;

    private Book book = new Book();
    private Date currentDate = new Date();


    public String doCreateBook() {
        FacesContext ctx = FacesContext.getCurrentInstance();

        if (book.getNumber() == null || "".equals(book.getNumber())) {
            ctx.addMessage("bookForm:number", new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Wrong ISBN Number", "Enter an ISBN Number"));
        }

        if (book.getTitle() == null || "".equals(book.getTitle())) {
            ctx.addMessage("bookForm:title", new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Wrong title", "Enter a title"));
        }

        if (ctx.getMessageList().size()!=0){
            return null;
        }

        try {
            book = bookEJB.createBook(book);
            System.out.println(book);
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Book is created", book.toString()));
        } catch (Exception ex){
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Book is not created",ex.getMessage()));
        }


        return "listBooks.xhtml";
    }

    public List<Book> findAllBooks(){
        return bookEJB.findAllBooks();
    }

    public void doFindBookById(){
        book = bookEJB.findBookById(book.getId());
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
