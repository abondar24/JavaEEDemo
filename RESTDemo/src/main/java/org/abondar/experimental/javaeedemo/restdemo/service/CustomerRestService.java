package org.abondar.experimental.javaeedemo.restdemo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.abondar.experimental.javaeedemo.restdemo.ejb.CustomerEJB;
import org.abondar.experimental.javaeedemo.restdemo.model.Customer;
import org.abondar.experimental.javaeedemo.restdemo.model.Customers;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/customer_service")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerRestService {


    @Context
    private UriInfo uriInfo;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Inject
    private CustomerEJB customerEJB;


    @POST
    @Path("/create_customer")
    public Response createCustomer(Customer customer) {
        String customerId = customerEJB.createCustomer(customer).toString();
        URI uri = uriInfo.getBaseUriBuilder().path("customer_service/get_customer_by_id/" + customerId).build();
        return Response.created(uri).build();
    }

    @GET
    @Path("/get_customers")
    public Customers getCustomers() {
        return customerEJB.getCustomers();
    }

    @GET
    @Path("/get_customer_by_login/{login: [^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$]}")
    public Customer getCustomerByLogin(@PathParam("login") String login) {
        return customerEJB.getCustomerByLogin(login);
    }


    @GET
    @Path("/get_customer_by_id/{customer_id: \\d+}")
    public Customer getCustomerById(@PathParam("customer_id") Long id) {
        return customerEJB.getCustomerById(id);
    }

    @GET
    @Path("/get_customer_by_zip")
    public Customers getCustomersByZipcode(@QueryParam("zip") String zip,
                                           @QueryParam("city") String city) {


        return city == null? customerEJB.getCustomersByZipcode(zip): customerEJB.getCustomersByZipcode(zip, city);
    }


    @GET
    @Path("/get_customer_by_name")
    public Customer getCustomersByName(@MatrixParam("first_name") String firstName,
                                       @MatrixParam("last_name") String lastName) {

        return customerEJB.getCustomersByName(firstName, lastName);
    }

    @GET
    @Path("/get_session_id")
    public String extractSessionId(@CookieParam("session_id") String sessionId) throws Exception {
        System.out.println("extractSessionID : " + sessionId);
        return objectMapper.writeValueAsString(sessionId + " from the server");
    }

    @GET
    @Path("/extract_user_agent")
    public Response extractUserAgent(@HeaderParam("User-Agent") String userAgent) {
        System.out.println("ALLAAAA: "+userAgent);
        return Response.ok(userAgent + " from the server").build();
    }


    @DELETE
    @Path("/delete_customer/{id}")
    public Response deleteCustomer(@PathParam("id") Long customerId) {
        customerEJB.deleteCustomer(customerId);
        return Response.noContent().build();
    }
}
