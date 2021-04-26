package BonusAlgorithm;

import AbstractFactoryImpl.DirectorFactoryJPA;
import AbstractFactoryImpl.FactoryProviderJPA;
import AbstractFactoryImpl.MovieFactoryJPA;
import Entities.Director;
import Entities.Movie;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class InputGenerator {
    private final int directorNum;
    private final int moviePerDirectorMinBound;
    private final int getMoviePerDirectorMaxBound;
    private final EntityManager em;

    public InputGenerator(int directorNum, int moviePerDirectorMinBound, int getMoviePerDirectorMaxBound, EntityManager em) {
        this.directorNum = directorNum;
        this.moviePerDirectorMinBound = moviePerDirectorMinBound;
        this.getMoviePerDirectorMaxBound = getMoviePerDirectorMaxBound;
        this.em = em;
    }

    public int getDirectorNum() {
        return directorNum;
    }

    public int getMoviePerDirectorMinBound() {
        return moviePerDirectorMinBound;
    }

    public int getGetMoviePerDirectorMaxBound() {
        return getMoviePerDirectorMaxBound;
    }

    public void generateInput() {
        System.out.println("Generating " + this.directorNum + " directors, which direct, at random, between " + this.moviePerDirectorMinBound + " and " + this.getMoviePerDirectorMaxBound + " movies!...");
        DirectorFactoryJPA directorFactoryJPA = (DirectorFactoryJPA) FactoryProviderJPA.getFactory("Director");
        MovieFactoryJPA movieFactoryJPA = (MovieFactoryJPA) FactoryProviderJPA.getFactory("Movie");
        int movieIndex = 0;
        for (int i = 0; i < this.directorNum; i++) {
            int randNum = ThreadLocalRandom.current().nextInt(this.moviePerDirectorMinBound, this.getMoviePerDirectorMaxBound + 1);
            for (int j = 0; j < randNum; j++) {
                Director x = new Director();
                x.setName("Bonus Sample Director " + i + "(" + j + ")");
                Movie y = new Movie();
                y.setTitle("Bonus Sample Movie " + movieIndex);
                y.setRelease_date(Date.valueOf(LocalDate.now().toString()));
                y.setDuration(100);
                y.setScore(8.8f);
                y.setDirector(x);
                x.setMovie(y);
                directorFactoryJPA.create(em, x);
                movieFactoryJPA.create(em, y);
                movieIndex++;
            }
        }
    }
}
