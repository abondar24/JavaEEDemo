package org.abondar.experimental.thorntaildemo;

import org.abondar.experimental.thorntaildemo.model.Book;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.arquillian.DefaultDeployment;
import org.wildfly.swarm.undertow.WARArchive;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
@DefaultDeployment(type = DefaultDeployment.Type.WAR)
public class BookServiceTest {


    @Deployment
    public static Archive createDeployment() {
        return ShrinkWrap.create(WARArchive.class, "BookServiceTest.war")
                .addPackages(true, "org.abondar.experimental.thorntaildemo")
                .addAsResource("META-INF/persistence.xml");

    }


    @Test
    public void getTitleTest() {

        var client = ClientBuilder.newClient();
        var target = client.target("http://localhost:8034/ws/book_service/get_title");
        var invocation = target.request(MediaType.TEXT_PLAIN).buildGet();
        var response = invocation.invoke();
        var entity = response.readEntity(String.class);

        assertEquals("Book Title", entity);
        assertEquals(Response.Status.OK.toString(), response.getStatusInfo().toString());


    }

    @Test
    public void createAndDeleteBookTest() {
        var book = new Book("Cars", 12.5F, "Science fiction comedy book",
                "1-24023-742-2", 400, false);

        var client = ClientBuilder.newClient();
        var target = client.target("http://localhost:8034/ws/book_service/create_book");
        var invocation = target.request(MediaType.APPLICATION_JSON).buildPost(Entity.entity(book, "application/json"));
        var response = invocation.invoke();

        assertEquals(201, response.getStatus());

        response = client.target(response.getLocation()).request(MediaType.APPLICATION_JSON).get();

        assertEquals(200, response.getStatus());
        assertTrue(response.hasEntity());


        book = response.readEntity(Book.class);

        assertNotNull(book.getId());
        assertEquals("Cars", book.getTitle());


        var bookId = book.getId().toString();
        response = client.target("http://localhost:8034/ws/book_service/delete_book/").path(bookId).request().delete();
        assertEquals(Response.Status.NO_CONTENT, response.getStatusInfo());

    }


}
