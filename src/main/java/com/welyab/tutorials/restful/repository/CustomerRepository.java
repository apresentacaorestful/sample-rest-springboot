package com.welyab.tutorials.restful.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.welyab.tutorials.restful.Customer;
import com.welyab.tutorials.restful.dao.CustomerDao;

@Component
public class CustomerRepository {

    @Autowired
    private CustomerDao customerDao;

    public void save(Customer customer) {
	customerDao.save(customer);
    }

    public Customer findByCode(String customerCode) {
	return customerDao.find(customerCode);
    }

    public List<Customer> listCustomers() {
	return customerDao.findAll();
    }

    public void delete(String customerCode) {
	Customer customer = findByCode(customerCode);
	customerDao.remove(customer);
    }

    public void update(Customer customer) {
	customerDao.update(customer);
    }
}
