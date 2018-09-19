package com.welyab.tutorials.restful.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.welyab.tutorials.restful.Customer;
import com.welyab.tutorials.restful.service.CustomerService;

@Component
@Path("customers")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @GET
    @Path("{customerCode}")
    @Produces({
	    MediaType.APPLICATION_JSON,
	    MediaType.APPLICATION_XML
    })
    public Response get(@PathParam("customerCode") String customerCode, @Context UriInfo uriInfo) {
	Customer customer = null;
	try {
	    customer = customerService.get(customerCode);
	} catch (IllegalArgumentException e) {
	    return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
	}

	if (customer == null) {
	    return Response.status(Status.NOT_FOUND).build();
	}

	customer.setSelf(getSelfLink(customer, uriInfo));
	customer.setDelete(getDeleteLink(customer, uriInfo));

	return Response.status(Status.OK).entity(customer).build();
    }

    @GET
    @Produces({
	    MediaType.APPLICATION_JSON,
	    MediaType.APPLICATION_XML
    })
    public List<Customer> listCustomers(@Context UriInfo uriInfo) {
	return customerService.getCustomers()
		.stream()
		.peek(c -> {
		    c.setSelf(getSelfLink(c, uriInfo));
		    c.setDelete(getDeleteLink(c, uriInfo));
		})
		.collect(Collectors.toList());
    }

    @POST
    @Consumes({
	    MediaType.APPLICATION_JSON,
	    MediaType.APPLICATION_XML
    })
    public Response add(Customer customer, @Context UriInfo uriInfo) throws URISyntaxException {
	customerService.addCostumer(customer);
	return Response.created(
		new URI(getSelfLink(customer, uriInfo).getLink())
	).build();
    }

    @PUT
    @Consumes({
	    MediaType.APPLICATION_JSON,
	    MediaType.APPLICATION_XML
    })
    public Response update(Customer customer) {
	customerService.update(customer);
	return Response.noContent().build();
    }

    @DELETE
    @Path("{customerCode}")
    public Response delete(@PathParam("customerCode") String customerCode) {
	customerService.removeCustomer(customerCode);
	return Response.noContent().build();
    }

    private com.welyab.tutorials.restful.api.Link getDeleteLink(Customer customer, UriInfo uriInfo) {
	URI selfUri = uriInfo
		.getAbsolutePathBuilder()
		.path(customer.getCode())
		.build();
	return new com.welyab.tutorials.restful.api.Link(selfUri.toString(), "getBook", "DELETE");
    }

    private com.welyab.tutorials.restful.api.Link getSelfLink(Customer customer, UriInfo uriInfo) {
	URI selfUri = uriInfo
		.getAbsolutePathBuilder()
		.path(customer.getCode())
		.build();
	return new com.welyab.tutorials.restful.api.Link(selfUri.toString(), "getBook", "GET");
    }
}
