package RepositoryPattern;

import Entities.Director;
import Repository.Create;
import Repository.FindByID;
import Repository.FindByName;

import javax.persistence.EntityManager;
import java.util.List;

public class DirectorRepoImpl implements DirectorRepo {
    @Override
    public Director getByName(EntityManager em, String name) {
        FindByName nameFinder = new FindByName();
        nameFinder.setEntityManager(em);
        Director toBeFound = null;
        List<Object> found = nameFinder.findByName(name);
        for (Object x : found) {
            if (x instanceof Director) {
                toBeFound = (Director) x;
                break;
            }
        }
        return toBeFound;
    }

    @Override
    public Director getByID(EntityManager em, int id) {
        FindByID idFinder = new FindByID();
        idFinder.setEntityManager(em);
        Director toBeFound = null;
        Object found = idFinder.findByID(id);
        if (found instanceof Director)
            toBeFound = (Director) found;
        return toBeFound;
    }

    @Override
    public void createInDatabase(EntityManager em, Director x) {
        Create creator = new Create();
        creator.setEntityManager(em);
        creator.persist(x);
    }
}
