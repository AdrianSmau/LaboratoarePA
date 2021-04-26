package RepositoryPattern;

import Entities.Actor;

import javax.persistence.EntityManager;

public interface ActorRepo {
    Actor getByName(EntityManager em, String name);

    Actor getByID(EntityManager em, int id);

    void createInDatabase(EntityManager em, Actor x);
}
