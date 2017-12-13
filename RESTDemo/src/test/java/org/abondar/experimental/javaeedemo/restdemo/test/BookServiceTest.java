package org.abondar.experimental.javaeedemo.restdemo.test;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

import static junit.framework.TestCase.assertEquals;

public class BookServiceTest {
    private static HttpServer server;
    private static URI uri = UriBuilder.fromUri("http://localhost/").port(8282).build();


    @BeforeClass
    public static void init() throws IOException {
        server = HttpServer.create(new InetSocketAddress(uri.getPort()), 0);

        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new ApplicationConfig(), HttpHandler.class);
        server.createContext(uri.getPath(), handler);

        server.start();
    }

    @AfterClass
    public static void stop() {
        server.stop(0);
    }


    @Test
    public void getBookTest() {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8282/book_service/get_title");
        Invocation invocation = target.request(MediaType.TEXT_PLAIN).buildGet();
        Response response = invocation.invoke();
        String entity = response.readEntity(String.class);

        assertEquals("Book Title", entity);
        assertEquals(Response.Status.OK.toString(), response.getStatusInfo().toString());

    }

}
