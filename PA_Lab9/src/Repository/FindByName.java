package Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class FindByName {
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Object> findByName(String pattern) {
        List<Object> results = new ArrayList<>();
        List<Object> movies = entityManager.createNamedQuery("Movie.findByName")
                .setParameter("title", pattern)
                .getResultList();
        List<Object> actors = entityManager.createNamedQuery("Actor.findByName")
                .setParameter("name", pattern)
                .getResultList();
        List<Object> genres = entityManager.createNamedQuery("Genre.findByName")
                .setParameter("name", pattern)
                .getResultList();
        List<Object> directors = entityManager.createNamedQuery("Director.findByName")
                .setParameter("name", pattern)
                .getResultList();
        if (movies.size() > 0)
            results.add(movies);
        if (actors.size() > 0)
            results.add(actors);
        if (genres.size() > 0)
            results.add(genres);
        if (results.size() > 0)
            results.add(directors);
        return results;
    }
}
