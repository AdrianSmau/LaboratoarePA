package AbstractFactoryImpl;

import javax.persistence.EntityManager;

public interface AbstractFactoryJPA<T> {
    void create(EntityManager em, T x);

    T getByName(EntityManager em, String name);

    T getByID(EntityManager em, int id);
}
