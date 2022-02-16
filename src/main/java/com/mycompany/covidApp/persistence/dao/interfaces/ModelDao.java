/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.covidApp.persistence.dao.interfaces;

import com.mycompany.covidApp.persistence.PersistenceUtil;
import java.util.Collection;
import java.util.function.Supplier;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

/**
 *
 * @author Mysafir
 * @param <Entity>
 * @param <PK>
 */
public interface ModelDao<Entity, PK> {

    Supplier<EntityManager> entityManagerSupplier = PersistenceUtil::newDefaultEntityManager;

    Collection<Entity> getAll() throws PersistenceException;

    default void persist(Entity entity) throws PersistenceException {
        EntityManager entityManager = entityManagerSupplier.get();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    default Entity getByPK(Class<Entity> entityClass, PK primaryKey) throws PersistenceException {
        return entityManagerSupplier.get().find(entityClass, primaryKey);
    }

    default void update(Entity entity) {
        EntityManager entityManager = entityManagerSupplier.get();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    default void delete(Entity entity) throws PersistenceException {
        EntityManager entityManager = entityManagerSupplier.get();

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    Collection<Entity> getByColumnName(String columnName, String value) throws PersistenceException;
}
