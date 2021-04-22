package Repository;

import Entities.Actor;
import Entities.Director;
import Entities.Genre;
import Entities.Movie;

import javax.persistence.EntityManager;

public class FindByID {
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public Object findByID(int id){
        if(this.entityManager != null){
            Movie movie = entityManager.find(Movie.class,id);
            if(movie != null)
                return movie;
            Actor actor = entityManager.find(Actor.class,id);
            if(actor != null)
                return actor;
            Genre genre = entityManager.find(Genre.class,id);
            if(genre != null)
                return genre;
            Director director = entityManager.find(Director.class,id);
            if(director != null)
                return director;
            System.out.println("\n ----- [ERROR] No Entity found with the given ID!... ----- \n");
            return null;
        }
        System.out.println("\n ----- [ERROR] No EntityManager found!... ----- \n");
        return null;
    }
}
