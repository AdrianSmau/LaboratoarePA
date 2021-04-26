package RepositoryPattern;

import Entities.Genre;

import javax.persistence.EntityManager;

public interface GenreRepo {
    Genre getByName(EntityManager em, String name);

    Genre getByID(EntityManager em, int id);

    void createInDatabase(EntityManager em, Genre x);
}
