package org.abondar.experimental.thorntaildemo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.abondar.experimental.thorntaildemo.ejb.CustomerEJB;
import org.abondar.experimental.thorntaildemo.model.Customer;
import org.abondar.experimental.thorntaildemo.model.Customers;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/customer_service")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerRestService {


    @Context
    private UriInfo uriInfo;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Inject
    private CustomerEJB customerEJB;

    private Customer demoCustomer =new Customer("Alex","Bondar","1212121212");


    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "ping";
    }

    @POST
    @Path("/create_customer")
    public Response createCustomer(Customer customer) {
        var customerId = customerEJB.createCustomer(customer).toString();
        var uri = uriInfo.getBaseUriBuilder().path("customer_service/get_customer_by_id/" + customerId).build();
        return Response.created(uri).build();
    }

    @GET
    @Path("/get_customers")
    public Customers getCustomers() {
        return customerEJB.getCustomers();
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
        return Response.ok(userAgent + " from the server").build();
    }


    @DELETE
    @Path("/delete_customer/{id}")
    public Response deleteCustomer(@PathParam("id") Long customerId) {
        customerEJB.deleteCustomer(customerId);
        return Response.noContent().build();
    }


    @GET
    @Path("/get_customer_text")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getCustomerText(){
        return Response.ok(demoCustomer.toString()).build();
    }


    @GET
    @Path("/get_customer_html")
    @Produces(MediaType.TEXT_HTML)
    public Response getCustomerHtml() {
        String sb = "<h1>Customer</h1><p>" +
                demoCustomer.toString() +
                "</p><hr/>";
        return Response.ok(sb).build();
    }


    @GET
    @Path("/get_default_media")
    public String getDefaultMediaType(@Context HttpHeaders httpHeaders){
        List<MediaType> types = httpHeaders.getAcceptableMediaTypes();
        return types.get(0).toString();
    }


}
