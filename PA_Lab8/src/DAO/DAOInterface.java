package DAO;

import Tables.Actor;
import Tables.Director;
import Tables.Genre;
import Tables.Movie;

import java.sql.SQLException;
import java.util.List;

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

    List<Movie> importMovies(String CSV_Path, int n);
    List<Actor> importActors(String CSV_Path, int n);
    List<Director> importDirectors(String CSV_Path, int n);
    List<Genre> importGenres(String CSV_Path, int n);

    void generateReport(List<Movie> movies, List<Actor> actors, List<Director> directors, List<Genre> genres, int n);
}
