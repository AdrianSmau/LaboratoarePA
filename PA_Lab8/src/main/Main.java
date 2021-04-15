package main;

import DAO.DAOImplementation;
import DatabaseConnection.DatabaseConnection;
import Tables.Genre;
import Tables.Movie;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Connecting to DataBase!...");
            DatabaseConnection DBCon = DatabaseConnection.getInstance();
            Connection conn = DBCon.getConnection();
            System.out.println("Connected successfully!...\n");
            Statement stmt = conn.createStatement();

            System.out.println("Attempting to create table 'movies'!...");
            String sql = "CREATE TABLE movies " +
                    "(id_movie INT NOT NULL PRIMARY KEY," +
                    "title VARCHAR2(20) NOT NULL," +
                    "release_date DATE," +
                    "duration INT," +
                    "score NUMBER(*,1))";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLSyntaxErrorException ex) {
                System.out.println("Table 'movies' already exists!...");
            } finally {
                System.out.println("Table 'movies' created!...");
            }
            System.out.println("\nAttempting to create table 'genres'!...");
            sql = "CREATE TABLE genres " +
                    "(id_genre INT NOT NULL PRIMARY KEY," +
                    "name VARCHAR2(20) NOT NULL)";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLSyntaxErrorException ex) {
                System.out.println("Table 'genres' already exists!...");
            } finally {
                System.out.println("Table 'genres' created!...");
            }

            System.out.println("\nAttempting to create associative table 'references'!...");
            sql = "CREATE TABLE references " +
                    "(id_movie INT NOT NULL," +
                    "id_genre INT NOT NULL," +
                    "CONSTRAINT references_movie FOREIGN KEY (id_movie) REFERENCES movies(id_movie)," +
                    "CONSTRAINT references_genre FOREIGN KEY (id_genre) REFERENCES genres(id_genre))";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLSyntaxErrorException ex) {
                System.out.println("Table 'references' already exists!...");
            } finally {
                System.out.println("Table 'references' created!...");
            }

            System.out.println();

            DAOImplementation dao = new DAOImplementation(conn);
            Movie[] movies = new Movie[2];
            movies[0] = new Movie(1, "Fight Club", Date.valueOf("1999-10-15"), 139, 8.8);
            movies[1] = new Movie(2, "Interstellar", Date.valueOf("2014-11-07"), 169, 8.6);

            if (dao.findMovieByID(1) == null)
                dao.addMovie(movies[0]);
            if (dao.findMovieByID(2) == null)
                dao.addMovie(movies[1]);

            Movie tempMovie1 = dao.findMovieByID(1);
            Movie tempMovie2 = dao.findMovieByName("Interstellar");

            System.out.println(tempMovie1);
            System.out.println(tempMovie2);

            Genre[] genres = new Genre[2];
            genres[0] = new Genre(1, "Action");
            genres[1] = new Genre(2, "Drama");

            if (dao.findGenreByID(1) == null)
                dao.addGenre(genres[0]);
            if (dao.findGenreByID(2) == null)
                dao.addGenre(genres[1]);

            Genre tempGenre1 = dao.findGenreByID(2);
            Genre tempGenre2 = dao.findGenreByName("Action");

            System.out.println(tempGenre2);
            System.out.println(tempGenre1);

            conn.close();
        } catch (SQLException ex) {
            System.err.println("Error on DataBase: " + ex);
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found!");
        }
    }
}
