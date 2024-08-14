package dev.dubrovsky.dao.connection;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectionDataBase {

    private EntityManagerFactory entityManagerFactory;

    public ConnectionDataBase() {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("default-persistence");
        } catch (Exception e) {
            System.out.println("Error in getEntityManager: " + e);
        }
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

}
