package org.abondar.experimental.thorntaildemo.service;

import org.abondar.experimental.thorntaildemo.ejb.BookEJB;
import org.abondar.experimental.thorntaildemo.model.Book;
import org.abondar.experimental.thorntaildemo.model.Books;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/book_service")
public class BookRestService {

    @Inject
    BookEJB bookEJB;
    @Context
    private UriInfo uriInfo;

    @GET
    @Path("/get_title")
    @Produces("text/plain")
    public String getBookTitle() {
        return "Book Title";
    }

    @GET
    @Path("/get_book/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBook(@PathParam("id") Long bookId) {

        return bookEJB.getBook(bookId);
    }

    @GET
    @Path("/get_books")
    @Produces(MediaType.APPLICATION_JSON)
    public Books getBooks() {
        return bookEJB.getBooks();
    }


    @POST
    @Path("/create_book")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(Book book) {
        var bookId = bookEJB.createBook(book);
        var uri = uriInfo.getBaseUriBuilder().path("book_service/get_book/" + bookId).build();
        return Response.created(uri).build();
    }

    @DELETE
    @Path("/delete_book/{id}")
    public Response deleteBook(@PathParam("id") Long bookId) {
        bookEJB.deleteBook(bookId);
        return Response.noContent().build();
    }
}
