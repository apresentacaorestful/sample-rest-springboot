package com.welyab.tutorials.restful.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.google.common.base.Preconditions;

import com.welyab.tutorials.restful.Customer;
import com.welyab.tutorials.restful.repository.CustomerRepository;

@Component
@RequestScope
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getCustomers() {
	return customerRepository.listCustomers();
    }

    public Customer get(String customerCode) {
	Preconditions.checkArgument(customerCode != null, "Parameter 'customerCode' cannot be null");
	Preconditions.checkArgument(
		StringUtils.isNotBlank(customerCode),
		"Parameter 'customerCode' cannot be empty"
	);
	return customerRepository.findByCode(customerCode);
    }

    public void removeCustomer(String customerCode) {
	Preconditions.checkNotNull(customerCode, "Parameter customerCode cannot be null");

	customerRepository.delete(customerCode);
    }

    public void update(Customer customer) {
	Preconditions.checkArgument(customer != null, "Parameter customer cannot be null");
	Preconditions.checkArgument(
		StringUtils.isBlank(customer.getCode()),
		"Customer email cannot be blank"
	);
	Preconditions.checkArgument(
		StringUtils.isNotBlank(customer.getEmail()),
		"Customer name cannot be blank"
	);
	Preconditions.checkArgument(
		StringUtils.isNotBlank(customer.getEmail()),
		"Customer email cannot be null"
	);

	customerRepository.update(customer);
    }

    public void addCostumer(Customer customer) {
	Preconditions.checkArgument(customer != null, "Parameter customer cannot be null");
	Preconditions.checkArgument(
		StringUtils.isBlank(customer.getCode()),
		"In order to save a new customer, the code can't be already set"
	);
	Preconditions.checkArgument(
		StringUtils.isNotBlank(customer.getEmail()),
		"Customer name cannot be blank"
	);
	Preconditions.checkArgument(
		StringUtils.isNotBlank(customer.getEmail()),
		"Customer email cannot be null"
	);

	customer.setCode(UUID.randomUUID().toString());
	customerRepository.save(customer);
    }
}
