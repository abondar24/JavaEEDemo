package org.abondar.experimental.javaeedemo.restdemo.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.abondar.experimental.javaeedemo.restdemo.config.ApplicationConfig;
import org.abondar.experimental.javaeedemo.restdemo.ejb.CustomerEJB;
import org.abondar.experimental.javaeedemo.restdemo.model.Customer;
import org.abondar.experimental.javaeedemo.restdemo.model.Customers;
import org.abondar.experimental.javaeedemo.restdemo.service.CustomerRestService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class CustomerServiceTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(Customer.class.getPackage())
                .addPackage(CustomerRestService.class.getPackage())
                .addPackage(ApplicationConfig.class.getPackage())
                .addPackage(CustomerEJB.class.getPackage())
                .addAsResource("META-INF/persistence.xml");

    }


    @ArquillianResource
    private URL base;


    @Inject
    private CustomerEJB customerEJB;


    private Client client = ClientBuilder.newClient();

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createAndDeleteCustomerTest() {
        customerEJB.clearCustomers();
        Customer customer = new Customer("Alex", "Bondar", "alex@mail.com", String.valueOf(12121211),
                new Date(), new Date(), 25, "95134", "San Jose");
        client = ClientBuilder.newClient();
        WebTarget target = client.target(base + "rest/customer_service/create_customer");
        Invocation invocation = target.request(MediaType.APPLICATION_JSON).buildPost(Entity.entity(customer, "application/json"));
        Response response = invocation.invoke();

        assertEquals(201, response.getStatus());

        response = client.target(response.getLocation()).request(MediaType.APPLICATION_JSON).get();

        assertEquals(200, response.getStatus());
        assertTrue(response.hasEntity());


        customer = response.readEntity(Customer.class);

        assertNotNull(customer.getId());
        assertEquals("Alex", customer.getFirstName());

        String customerId = customer.getId().toString();
        response = client.target(base + "rest/customer_service/delete_customer/").path(customerId).request().delete();

        assertEquals(Response.Status.NO_CONTENT, response.getStatusInfo());

    }


    @Test
    public void getCustomersTest() {
        customerEJB.clearCustomers();

        Response response = client.target(base + "rest/customer_service/get_customers").request(MediaType.APPLICATION_JSON).get();
        assertEquals(base.getPath(), 200, response.getStatus());
        Customers customers = response.readEntity(Customers.class);
        System.out.println(customers.getCustomers());
        assertTrue(customers.isEmpty());
    }

    @Test
    public void getCustomerByLoginTest() {
        customerEJB.clearCustomers();
        Customer c = new Customer("Alex", "Bondar", "alex@mail.com", String.valueOf(12121211),
                new Date(), new Date(), 25, "95134", "San Jose");

        customerEJB.createCustomer(c);
        Response response = client.target(base + "rest/customer_service/get_customer_by_login/").path("ALLALAaaaa")
                .request(MediaType.APPLICATION_JSON).get();
        assertEquals(200, response.getStatus());

        Customer customer = response.readEntity(Customer.class);
        assertEquals(c.getId(), customer.getId());
        customerEJB.deleteCustomer(customer.getId());
    }


    @Test
    public void getCustomerByZipCodeCityTest() {
        customerEJB.clearCustomers();
        Customer c = new Customer("Alex", "Bondar", "alex@mail.com", String.valueOf(12121211),
                new Date(), new Date(), 25, "95134", "San Jose");

        customerEJB.createCustomer(c);

        Response response = client.target(base+"rest/customer_service/get_customer_by_zip?zip="+c.getZipcode()+
                "&city="+c.getCity()).request().get();
        assertEquals(200, response.getStatus());

        Customer customer = response.readEntity(Customer.class);
        assertEquals(c.getId(), customer.getId());
        customerEJB.deleteCustomer(customer.getId());
    }

//    @Test
//    public void shouldCheckGetCustomerByZipCodeURI() {
//        Response response = client.target("http://localhost:8282/07/customer?zip=75001").request().get();
//        assertEquals(200, response.getStatus());
//    }
//
//    @Test
//    public void shouldCheckGetCustomerByZipCodeWithParamURI() {
//        Response response = client.target("http://localhost:8282/07/customer").queryParam("zip", 75011L).request().get();
//        assertEquals(200, response.getStatus());
//    }
//
//    @Test
//    public void shouldCheckGetCustomerByFirstnameNameURI() {
//        Response response = client.target("http://localhost:8282/07/customer/search;firstname=Antonio;surname=Goncalves").request().get();
//        assertEquals(200, response.getStatus());
//    }
//
//    @Test
//    public void shouldCheckGetCustomerByFirstnameURI() {
//        Response response = client.target("http://localhost:8282/07/customer/search;firstname=AntonioNull").request().get();
//        assertEquals(200, response.getStatus());
//    }
//
//    @Test
//    public void shouldCheckGetCustomerByFirstnameNameWithParamURI() {
//        Response response = client.target("http://localhost:8282/07/customer/search").matrixParam("firstname", "Antonio2").matrixParam("surname", "Goncalves2").request().get();
//        assertEquals(200, response.getStatus());
//    }
//
    @Test
    public void getCustomerWithCookieParamTest() throws Exception{
        Cookie myCookie = new Cookie("session_id", "This is my cookie");
        String response = client.target(base+"rest/customer_service/get_session_id").request().cookie(myCookie).get(String.class);
        assertEquals(objectMapper.writeValueAsString("This is my cookie from the server"), response);
    }


    @Test
    public void echoUserAgentWithReponseTest() {

        Response response = client.target(base+"rest/customer_service/extract_user_agent").request().get();
        assertEquals(200, response.getStatus());
        assertTrue(response.readEntity(String.class).startsWith("Apache"));
    }

}

