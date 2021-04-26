package Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class FindByName {
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Object> findByName(String pattern) {
        List<Object> results = new ArrayList<>();
        Object movie;
        try {
            movie = entityManager.createNamedQuery("Movie.findByName")
                    .setParameter("title", pattern)
                    .getSingleResult();
        } catch (NoResultException ex) {
            movie = null;
        }
        Object actor;
        try {
            actor = entityManager.createNamedQuery("Actor.findByName")
                    .setParameter("name", pattern)
                    .getSingleResult();
        } catch (NoResultException ex) {
            actor = null;
        }
        Object genre;
        try {
            genre = entityManager.createNamedQuery("Genre.findByName")
                    .setParameter("name", pattern)
                    .getSingleResult();
        } catch (NoResultException ex) {
            genre = null;
        }
        Object director;
        try {
            director = entityManager.createNamedQuery("Director.findByName")
                    .setParameter("name", pattern)
                    .getSingleResult();
        } catch (NoResultException ex) {
            director = null;
        }
        if (movie != null)
            results.add(movie);
        if (actor != null)
            results.add(actor);
        if (genre != null)
            results.add(genre);
        if (director != null)
            results.add(director);
        return results;
    }
}
