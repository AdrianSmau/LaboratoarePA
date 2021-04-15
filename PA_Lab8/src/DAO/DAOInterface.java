package DAO;

import Tables.Genre;
import Tables.Movie;

import java.sql.SQLException;

public interface DAOInterface {
    int addMovie(Movie movie) throws SQLException;
    int addGenre(Genre genre) throws SQLException;
    Movie findMovieByID(int id) throws SQLException;
    Genre findGenreByID(int id) throws SQLException;
    Movie findMovieByName(String name) throws SQLException;
    Genre findGenreByName(String name) throws SQLException;
}
