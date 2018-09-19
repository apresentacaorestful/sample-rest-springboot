package com.welyab.tutorials.restful.repository.config;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
public class EntityManagerProducer {

    @Inject
    private EntityManagerFactoryHolder entityManagerFactoryHolder;

    @Bean
    @RequestScope
    public EntityManager createEntitymanager() {
	return entityManagerFactoryHolder.crateEntityManager();
    }
}
