package com.welyab.tutorials.restful.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.welyab.tutorials.restful.Customer;
import com.welyab.tutorials.restful.service.CustomerService;

@RestController("customers")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(
	    path = "{customerCode}",
	    method = RequestMethod.GET,
	    produces = {
		    "application/json",
		    "application/xml"
	    }
    )
    public ResponseEntity<Customer> get(
	    @PathVariable("customerCode") String customerCode
    ) {
	Customer customer = null;
	try {
	    customer = customerService.get(customerCode);
	} catch (IllegalArgumentException e) {
	    return ResponseEntity.badRequest().build();
	}

	if (customer == null) {
	    return ResponseEntity.notFound().build();
	}

	customer.addLink(getCustomerLink(customerCode));
	customer.addLink(deleteCustomerLink(customerCode));
	customer.addLink(allCostumersLink());

	return ResponseEntity.ok(customer);
    }

    @RequestMapping(
	    method = RequestMethod.GET,
	    produces = {
		    "application/json",
		    "application/xml"
	    }
    )
    public List<Customer> listCustomers() {
	return customerService.getCustomers()
		.stream()
		.peek(c -> {
		    c.addLink(getCustomerLink(c.getCode()));
		    c.addLink(deleteCustomerLink(c.getCode()));
		    c.addLink(allCostumersLink());
		})
		.collect(Collectors.toList());
    }

    @RequestMapping(
	    method = RequestMethod.POST,
	    consumes = {
		    "application/json",
		    "application/xml"
	    },
	    produces = {
		    "application/json",
		    "application/xml"
	    }
    )
    public ResponseEntity<Customer> add(@RequestBody Customer customer) throws URISyntaxException {
	customerService.addCostumer(customer);
	return ResponseEntity.created(
		new URI("http://asdf.com")
	).build();
    }

    @RequestMapping(
	    method = RequestMethod.PUT,
	    consumes = {
		    "application/json",
		    "application/xml"
	    }
    )
    public ResponseEntity<Customer> update(Customer customer) {
	customerService.update(customer);
	return ResponseEntity.noContent().build();
    }

    @RequestMapping(
	    path = "{customerCode}",
	    method = RequestMethod.DELETE,
	    consumes = {
		    "application/json",
		    "application/xml"
	    }
    )
    public ResponseEntity<Customer> delete(@PathVariable("customerCode") String customerCode) {
	customerService.removeCustomer(customerCode);
	return ResponseEntity.noContent().build();
    }

    private static Link getCustomerLink(String customerCode) {
	return linkTo(methodOn(CustomerResource.class).get(customerCode))
		.withRel("self")
		.withType(RequestMethod.GET.name());
    }

    private static Link deleteCustomerLink(String customerCode) {
	return linkTo(methodOn(CustomerResource.class).get(customerCode))
		.withRel("self")
		.withType(RequestMethod.DELETE.name());
    }

    private static Link allCostumersLink() {
	if (true) {
	    return deleteCustomerLink("lkfasdjlfkajsdlfkajds");
	}
	return linkTo(methodOn(CustomerResource.class).listCustomers())
		.withRel("allCustomers")
		.withType(HttpMethod.GET.name());
    }
}
