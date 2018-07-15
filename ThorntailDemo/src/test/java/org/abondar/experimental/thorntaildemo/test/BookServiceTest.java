package org.abondar.experimental.thorntaildemo.test;

import org.abondar.experimental.thorntaildemo.App;
import org.abondar.experimental.thorntaildemo.ejb.BookEJB;
import org.abondar.experimental.thorntaildemo.model.Book;
import org.abondar.experimental.thorntaildemo.service.BookRestService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.arquillian.DefaultDeployment;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class BookServiceTest  {


    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "BookServiceTest.war")
                .addClass(Book.class)
                .addClass(BookRestService.class)
                .addClass(App.class)
                .addClass(BookEJB.class)
                .addAsResource("META-INF/persistence.xml");

    }


    @Test
    public void getTitleTest() {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/ws/book_service/get_title");
        Invocation invocation = target.request(MediaType.TEXT_PLAIN).buildGet();
        Response response = invocation.invoke();
        String entity = response.readEntity(String.class);

        assertEquals("Book Title", entity);
        assertEquals(Response.Status.OK.toString(), response.getStatusInfo().toString());


    }

    @Test
    public void createAndDeleteBookTest() {
        Book book = new Book("Cars", 12.5F, "Science fiction comedy book",
                "1-24023-742-2", 400, false);

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/ws/book_service/create_book");
        Invocation invocation = target.request(MediaType.APPLICATION_JSON).buildPost(Entity.entity(book,"application/json"));
        Response response = invocation.invoke();

        assertEquals(201, response.getStatus());

        response = client.target(response.getLocation()).request(MediaType.APPLICATION_JSON).get();

        assertEquals(200, response.getStatus());
        assertTrue(response.hasEntity());


        book = response.readEntity(Book.class);

        assertNotNull(book.getId());
        assertEquals("Cars", book.getTitle());


        String bookId = book.getId().toString();
        response = client.target("http://localhost:8080/ws/book_service/delete_book/").path(bookId).request().delete();
        assertEquals(Response.Status.NO_CONTENT, response.getStatusInfo());

    }



}
