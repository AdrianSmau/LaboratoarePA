package ThreadPooling;

import DAO.DAOImplementation;
import Tables.Actor;
import Tables.Director;
import Tables.Genre;
import Tables.Movie;

import java.sql.Connection;
import java.sql.SQLException;

public class WorkerThread implements Runnable {
    private final Connection conn;
    private final Movie movie;
    private final Actor actor;
    private final Director director;
    private final Genre genre;
    private final DAOImplementation dao;

    public WorkerThread(Connection conn, Movie movie, Actor actor, Director director, Genre genre) {
        this.conn = conn;
        this.movie = movie;
        this.actor = actor;
        this.director = director;
        this.genre = genre;
        this.dao = new DAOImplementation(conn);
    }

    private void processCommand() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        if (movie != null) {
            try {
                dao.addMovie(movie);
            } catch (SQLException ex) {
                System.err.println("Error when trying to import Movie: " + this.movie);
            }
        }
        if (actor != null) {
            try {
                dao.addActor(actor);
            } catch (SQLException ex) {
                System.err.println("Error when trying to import Actor: " + this.actor);
            }
        }
        if (genre != null) {
            try {
                dao.addGenre(genre);
            } catch (SQLException ex) {
                System.err.println("Error when trying to import Genre: " + this.genre);
            }
        }
        if (director != null) {
            try {
                dao.addDirector(director);
            } catch (SQLException ex) {
                System.err.println("Error when trying to import Movie: " + this.genre);
            }
        }
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " - [Start]");
        processCommand();
        System.out.println(Thread.currentThread().getName() + " - [END]");
    }

    @Override
    public String toString() {
        return this.conn.toString();
    }
}
