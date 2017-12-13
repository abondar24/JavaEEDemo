package org.abondar.experimental.javaeedemo.restdemo.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/book_service")
public class BookRestService {

    @GET
    @Path("/get_title")
    @Produces("text/plain")
    public String getBookTitle(){
        return "Book Title";
    }
}
