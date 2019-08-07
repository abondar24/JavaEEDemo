package org.abondar.experimental.thorntaildemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.abondar.experimental.thorntaildemo.App;
import org.abondar.experimental.thorntaildemo.ejb.CustomerEJB;
import org.abondar.experimental.thorntaildemo.model.Book;
import org.abondar.experimental.thorntaildemo.model.Customer;
import org.abondar.experimental.thorntaildemo.model.Customers;
import org.abondar.experimental.thorntaildemo.service.CustomerRestService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.arquillian.DefaultDeployment;
import org.wildfly.swarm.undertow.WARArchive;

import javax.inject.Inject;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
@DefaultDeployment(type = DefaultDeployment.Type.WAR)
public class CustomerServiceTest {

    @Deployment
    public static Archive createDeployment() {
        return ShrinkWrap.create(WARArchive.class, "CustomerServiceTest.war")
                .addPackages(true,"org.abondar.experimental.thorntaildemo")
                .addAsResource("META-INF/persistence.xml");

    }


    @Inject
    private CustomerEJB customerEJB;


    private Client client = ClientBuilder.newClient();

    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void pingTest(){
        var response = client.target("http://localhost:8034/ws/customer_service/ping").request(MediaType.TEXT_PLAIN).get();
        assertEquals(200, response.getStatus());
        assertEquals("ping",response.readEntity(String.class));
    }

    @Test
    public void createAndDeleteCustomerTest() {
        customerEJB.clearCustomers();
        var customer = new Customer("Alex", "Bondar", "alex@mail.com", String.valueOf(12121211),
                new Date(), new Date(), 25, "95134", "San Jose");
        var target = client.target("http://localhost:8034/ws/customer_service/create_customer");
        var invocation = target.request(MediaType.APPLICATION_JSON).buildPost(Entity.entity(customer, "application/json"));
        var response = invocation.invoke();

        assertEquals(201, response.getStatus());

        response = client.target(response.getLocation()).request(MediaType.APPLICATION_JSON).get();

        assertEquals(200, response.getStatus());
        assertTrue(response.hasEntity());


        customer = response.readEntity(Customer.class);

        assertNotNull(customer.getId());
        assertEquals("Alex", customer.getFirstName());

        var customerId = customer.getId().toString();
        response = client.target("http://localhost:8034/ws/customer_service/delete_customer/").path(customerId).request().delete();

        assertEquals(Response.Status.NO_CONTENT, response.getStatusInfo());

    }


    @Test
    public void getCustomersTest() {
        customerEJB.clearCustomers();

        var response = client.target("http://localhost:8034/ws/customer_service/get_customers").request(MediaType.APPLICATION_JSON).get();
        assertEquals(200, response.getStatus());
        var customers = response.readEntity(Customers.class);
        assertTrue(customers.isEmpty());
    }


    @Test
    public void getCustomerByZipCodeCityTest() {
        customerEJB.clearCustomers();
        var c = new Customer("Alex", "Bondar", "alex@mail.com", String.valueOf(12121211),
               null, null, 25, "95134", "San Jose");

        customerEJB.createCustomer(c);

        var response = client.target("http://localhost:8034/ws/customer_service/get_customer_by_zip?zip="+c.getZipcode()+
                "&city="+c.getCity()).request().get();
        assertEquals(200, response.getStatus());

        var customers = response.readEntity(Customers.class);
        assertEquals(1,customers.size());

        customers.forEach(cc-> customerEJB.deleteCustomer(cc.getId()));

    }

    @Test
    public void getCustomerByZipCodeTest() {
        customerEJB.clearCustomers();
        var c = new Customer("Alex", "Bondar", "alex@mail.com", String.valueOf(12121211),
                null, null, 25, "95134", "San Jose");

        customerEJB.createCustomer(c);

        var response = client.target("http://localhost:8034/ws/customer_service/get_customer_by_zip?zip="+c.getZipcode())
                .request(MediaType.APPLICATION_JSON).get();
        assertEquals(200, response.getStatus());

        var customers = response.readEntity(Customers.class);
        assertEquals(1,customers.size());

        customers.forEach(cc-> customerEJB.deleteCustomer(cc.getId()));

    }


    @Test
    public void getCustomerByNameTest() {
        customerEJB.clearCustomers();
        var c = new Customer("Alex", "Bondar", "alex@mail.com", String.valueOf(12121211),
                null, null, 25, "95134", "San Jose");

        customerEJB.createCustomer(c);

        var response = client.target("http://localhost:8034/ws/customer_service/get_customer_by_name;first_name="+c.getFirstName()+
                ";last_name="+c.getLastName()).request(MediaType.APPLICATION_JSON).get();
        assertEquals(200, response.getStatus());

        var customer = response.readEntity(Customer.class);
        assertEquals(c.getFirstName(), customer.getFirstName());
        assertEquals(c.getLastName(),customer.getLastName());
        customerEJB.deleteCustomer(customer.getId());

    }

    @Test
    public void getCustomerWithCookieParamTest() throws Exception{
        var myCookie = new Cookie("session_id", "This is my cookie");
        var response = client.target("http://localhost:8034/ws/customer_service/get_session_id")
                .request(MediaType.APPLICATION_JSON).cookie(myCookie).get(String.class);
        assertEquals(objectMapper.writeValueAsString("This is my cookie from the server"), response);
    }


    @Test
    public void echoUserAgentWithResponseTest() {

        var response = client.target("http://localhost:8034/ws/customer_service/extract_user_agent").request().get();
        assertEquals(200, response.getStatus());
        assertTrue(response.readEntity(String.class).startsWith("Apache"));
    }


    @Test
    public void getCustomerAsPlainTextTest() {
        var response = client.target("http://localhost:8034/ws/customer_service/get_customer_text").request(MediaType.TEXT_PLAIN).get();
        assertEquals(200, response.getStatus());
        assertTrue(response.readEntity(String.class).startsWith("Customer"));
    }

    @Test
    public void getCustomerAsHTML() {
        var response = client.target( "http://localhost:8034/ws/customer_service/get_customer_html").request(MediaType.TEXT_HTML).get();
        assertEquals(200, response.getStatus());
        assertTrue(response.readEntity(String.class).startsWith("<h1>Customer</h1>"));
    }


    @Test
    public void getDefaultMediaTypeTest() {
        var response = client.target("http://localhost:8034/ws/customer_service/get_default_media").request().get();
        assertEquals(200, response.getStatus());
        assertEquals("*/*", response.readEntity(String.class));
    }


}

