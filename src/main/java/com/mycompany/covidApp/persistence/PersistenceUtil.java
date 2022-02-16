/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.covidApp.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

final public class PersistenceUtil {
    private static final String DEFAULT_PERSISTENCE_UNIT_NAME = "covidPU";
    private static final EntityManagerFactory DEFAULT_ENTITY_MANAGER_FACTORY;

    static {
        DEFAULT_ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(DEFAULT_PERSISTENCE_UNIT_NAME);
    }

    private PersistenceUtil() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static EntityManager newDefaultEntityManager() {
        return DEFAULT_ENTITY_MANAGER_FACTORY.createEntityManager();
    }
}
