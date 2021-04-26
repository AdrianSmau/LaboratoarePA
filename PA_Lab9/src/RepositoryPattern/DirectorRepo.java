package RepositoryPattern;

import Entities.Director;

import javax.persistence.EntityManager;

public interface DirectorRepo {
    Director getByName(EntityManager em, String name);

    Director getByID(EntityManager em, int id);

    void createInDatabase(EntityManager em, Director x);
}
