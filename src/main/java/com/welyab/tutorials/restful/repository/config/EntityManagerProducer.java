package com.welyab.tutorials.restful.repository.config;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
public class EntityManagerProducer {

    @Autowired
    private EntityManagerFactoryHolder entityManagerFactoryHolder;

    @Bean
    @RequestScope
    public EntityManager createEntitymanager() {
	return entityManagerFactoryHolder.crateEntityManager();
    }
}
