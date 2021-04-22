package Repository;

import Entities.Actor;
import Entities.Director;
import Entities.Genre;
import Entities.Movie;

import javax.persistence.EntityManager;

public class Create {
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void persist(Object x) {
        if (x != null && entityManager != null) {
            if (x instanceof Movie) {
                entityManager.persist(x);
                return;
            }
            if (x instanceof Actor) {
                entityManager.persist(x);
                return;
            }
            if (x instanceof Genre) {
                entityManager.persist(x);
                return;
            }
            if (x instanceof Director) {
                entityManager.persist(x);
                return;
            }
            System.out.println("\n ----- [Error] Object Type unrecognized! ----- \n");
        } else {
            System.out.println("\n ----- [ERROR] Entity or EntityManager invalid! ----- \n");
        }
    }
}
