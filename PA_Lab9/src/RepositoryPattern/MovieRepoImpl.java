package RepositoryPattern;

import Entities.Movie;
import Repository.Create;
import Repository.FindByID;
import Repository.FindByName;

import javax.persistence.EntityManager;
import java.util.List;

public class MovieRepoImpl implements MovieRepo {
    @Override
    public Movie getByName(EntityManager em, String name) {
        FindByName nameFinder = new FindByName();
        nameFinder.setEntityManager(em);
        Movie toBeFound = null;
        List<Object> found = nameFinder.findByName(name);
        for (Object x : found) {
            if (x instanceof Movie) {
                toBeFound = (Movie) x;
                break;
            }
        }
        return toBeFound;
    }

    @Override
    public Movie getByID(EntityManager em, int id) {
        FindByID idFinder = new FindByID();
        idFinder.setEntityManager(em);
        Movie toBeFound = null;
        Object found = idFinder.findByID(id);
        if (found instanceof Movie)
            toBeFound = (Movie) found;
        return toBeFound;
    }

    @Override
    public void createInDatabase(EntityManager em, Movie x) {
        Create creator = new Create();
        creator.setEntityManager(em);
        creator.persist(x);
    }
}
