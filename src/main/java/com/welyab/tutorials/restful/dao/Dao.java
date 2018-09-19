package com.welyab.tutorials.restful.dao;

import javax.persistence.EntityManager;

public abstract class Dao<PkType, EntityType> {

    private final Class<EntityType> entityType;

    protected Dao(Class<EntityType> entityType) {
	this.entityType = entityType;
    }

    public EntityType find(PkType pk) {
	return getEntityManager().find(entityType, pk);
    }

    public void save(EntityType entity) {
	getEntityManager().getTransaction().begin();
	getEntityManager().persist(entity);
	getEntityManager().getTransaction().commit();
    }

    public void update(EntityType entity) {
	getEntityManager().getTransaction().begin();
	getEntityManager().merge(entity);
	getEntityManager().getTransaction().commit();
    }

    public void remove(EntityType entity) {
	getEntityManager().getTransaction().begin();
	getEntityManager().remove(entityType);
	getEntityManager().getTransaction().commit();
    }

    protected abstract EntityManager getEntityManager();
}
