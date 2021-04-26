package RepositoryPattern;

import Entities.Actor;
import Repository.Create;
import Repository.FindByID;
import Repository.FindByName;

import javax.persistence.EntityManager;
import java.util.List;

public class ActorRepoImpl implements ActorRepo {
    @Override
    public Actor getByName(EntityManager em, String name) {
        FindByName nameFinder = new FindByName();
        nameFinder.setEntityManager(em);
        Actor toBeFound = null;
        List<Object> found = nameFinder.findByName(name);
        for (Object x : found) {
            if (x instanceof Actor) {
                toBeFound = (Actor) x;
                break;
            }
        }
        return toBeFound;
    }

    @Override
    public Actor getByID(EntityManager em, int id) {
        FindByID idFinder = new FindByID();
        idFinder.setEntityManager(em);
        Actor toBeFound = null;
        Object found = idFinder.findByID(id);
        if (found instanceof Actor)
            toBeFound = (Actor) found;
        return toBeFound;
    }

    @Override
    public void createInDatabase(EntityManager em, Actor x) {
        Create creator = new Create();
        creator.setEntityManager(em);
        creator.persist(x);
    }
}
