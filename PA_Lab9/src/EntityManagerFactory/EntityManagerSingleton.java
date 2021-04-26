package EntityManagerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerSingleton {
    private static final EntityManagerSingleton instance = new EntityManagerSingleton();
    private final String persistence_unit_name = "Lab9App";
    private EntityManagerFactory entityManagerFactory;

    private EntityManagerSingleton() {
    }

    public static EntityManagerSingleton getInstance() {
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null)
            createEntityManagerFactory();
        return entityManagerFactory;
    }

    public void closeEntityManagerFactory() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
            entityManagerFactory = null;
            System.out.println("\n ----- [NOTIFICATION] Persistence finished at: " + new java.util.Date() + " ----- \n");
        }
    }

    private void createEntityManagerFactory() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name);
        System.out.println("\n ----- [NOTIFICATION] Persistence started at: " + new java.util.Date() + " ----- \n");
    }
}
