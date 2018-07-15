package org.abondar.experimental.thorntaildemo.service;

import org.abondar.experimental.thorntaildemo.ejb.BookEJB;
import org.abondar.experimental.thorntaildemo.model.Book;
import org.abondar.experimental.thorntaildemo.model.Books;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/book_service")
public class BookRestService {

    @Context
    private UriInfo uriInfo;

    @Inject
    BookEJB bookEJB;


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
        String bookId = bookEJB.createBook(book);
        URI uri = uriInfo.getBaseUriBuilder().path("book_service/get_book/" + bookId).build();
        return Response.created(uri).build();
    }

    @DELETE
    @Path("/delete_book/{id}")
    public Response deleteBook(@PathParam("id") Long bookId) {
        bookEJB.deleteBook(bookId);
        return Response.noContent().build();
    }
}
