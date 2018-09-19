package com.welyab.tutorials.restful.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.welyab.tutorials.restful.Customer;

@Component
public class CustomerDao extends Dao<String, Customer> {

    @Autowired
    private EntityManager entityManager;

    protected CustomerDao() {
	super(Customer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
	return entityManager;
    }

    public List<Customer> findAll() {
	return getEntityManager()
		.createQuery(
			"from Customer",
			Customer.class
		).getResultList();
    }
}
