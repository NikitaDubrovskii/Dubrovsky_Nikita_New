package dev.dubrovsky.dao.connection;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectionDataBase {

    private static EntityManagerFactory entityManagerFactory;

    private ConnectionDataBase() {}

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            try {
                entityManagerFactory = Persistence.createEntityManagerFactory("default-persistence");
            } catch (Exception e) {
                System.out.println("Error in getEntityManager: " + e);
            }
        }

        return entityManagerFactory;
    }

}