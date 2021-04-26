package RepositoryPattern;

import Entities.Movie;

import javax.persistence.EntityManager;

public interface MovieRepo {
    Movie getByName(EntityManager em, String name);

    Movie getByID(EntityManager em, int id);

    void createInDatabase(EntityManager em, Movie x);
}
