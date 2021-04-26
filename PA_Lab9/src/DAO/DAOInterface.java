package DAO;

import Entities.Actor;
import Entities.Director;
import Entities.Genre;
import Entities.Movie;

import java.sql.SQLException;

public interface DAOInterface {
    int addDirector(Director director) throws SQLException;

    int addActor(Actor actor) throws SQLException;

    int addMovie(Movie movie) throws SQLException;

    int addGenre(Genre genre) throws SQLException;

    Director findDirectorByID(int id) throws SQLException;

    Actor findActorByID(int id) throws SQLException;

    Movie findMovieByID(int id) throws SQLException;

    Genre findGenreByID(int id) throws SQLException;

    Director findDirectorByName(String name) throws SQLException;

    Actor findActorByName(String name) throws SQLException;

    Movie findMovieByName(String name) throws SQLException;

    Genre findGenreByName(String name) throws SQLException;
}
