package RepositoryPattern;

import Entities.Genre;
import Repository.Create;
import Repository.FindByID;
import Repository.FindByName;

import javax.persistence.EntityManager;
import java.util.List;

public class GenreRepoImpl implements GenreRepo {
    @Override
    public Genre getByName(EntityManager em, String name) {
        FindByName nameFinder = new FindByName();
        nameFinder.setEntityManager(em);
        Genre toBeFound = null;
        List<Object> found = nameFinder.findByName(name);
        for (Object x : found) {
            if (x instanceof Genre) {
                toBeFound = (Genre) x;
                break;
            }
        }
        return toBeFound;
    }

    @Override
    public Genre getByID(EntityManager em, int id) {
        FindByID idFinder = new FindByID();
        idFinder.setEntityManager(em);
        Genre toBeFound = null;
        Object found = idFinder.findByID(id);
        if (found instanceof Genre)
            toBeFound = (Genre) found;
        return toBeFound;
    }

    @Override
    public void createInDatabase(EntityManager em, Genre x) {
        Create creator = new Create();
        creator.setEntityManager(em);
        creator.persist(x);
    }
}
