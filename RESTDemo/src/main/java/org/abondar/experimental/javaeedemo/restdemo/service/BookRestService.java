package org.abondar.experimental.javaeedemo.restdemo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.abondar.experimental.javaeedemo.restdemo.model.Book;
import org.abondar.experimental.javaeedemo.restdemo.model.Books;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/book_service")
@Stateless
public class BookRestService {

    @Context
    private UriInfo uriInfo;

    @PersistenceContext(unitName = "demo_unit")
    private EntityManager em;

    private ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Path("/get_title")
    @Produces("text/plain")
    public String getBookTitle(){
        return "Book Title";
    }

    @GET
    @Path("/get_book/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBook(@PathParam("id") Long bookId) {
        return em.find(Book.class, bookId);
    }

    @GET
    @Path("/get_books")
    @Produces(MediaType.APPLICATION_JSON)
    public Books getBooks(){
        TypedQuery<Book> query = em.createNamedQuery("findAllBooks",Book.class);
        return new Books(query.getResultList());
    }


    @POST
    @Path("/create_book")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(Book book){
        em.persist(book);
        URI uri = uriInfo.getBaseUriBuilder().path("book_service/get_book/"+book.getId().toString()).build();
        System.out.println(uri);
        return Response.created(uri).build();
    }

    @DELETE
    @Path("/delete_book/{id}")
    public Response deleteBook(@PathParam("id") Long bookId) {
        em.remove(em.find(Book.class,bookId));
        return Response.noContent().build();
    }
}
