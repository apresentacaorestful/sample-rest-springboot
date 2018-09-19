package com.welyab.tutorials.restful.repository.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Component;

@Component
public class EntityManagerFactoryHolder {

    @PostConstruct
    private void init() {
	startJpaEntityManagerFactory();
    }

    @PreDestroy
    private void shutdown() {
	stopJpaEntityManagerFactory();
    }

    public EntityManager crateEntityManager() {
	if (EntityManagerFactoryHolder_.emf == null) {
	    throw new IllegalStateException("The underlying entity manager factory is not started");
	}
	return EntityManagerFactoryHolder_.emf.createEntityManager();
    }

    private synchronized void startJpaEntityManagerFactory() {
	if (EntityManagerFactoryHolder_.emf != null) {
	    return;
	}

	EntityManagerFactoryHolder_.emf = Persistence.createEntityManagerFactory("RestSamplePu");
    }

    private synchronized void stopJpaEntityManagerFactory() {
	if (EntityManagerFactoryHolder_.emf == null) {
	    return;
	}

	EntityManagerFactoryHolder_.emf.close();
	EntityManagerFactoryHolder_.emf = null;
    }

    private static final class EntityManagerFactoryHolder_ {
	private static EntityManagerFactory emf;
    }
}
