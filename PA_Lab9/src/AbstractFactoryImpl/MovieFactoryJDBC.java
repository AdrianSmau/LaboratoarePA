package AbstractFactoryImpl;

import DAO.DAOImplementation;
import Entities.Movie;

import java.sql.Connection;
import java.sql.SQLException;

public class MovieFactoryJDBC implements AbstractFactoryJDBC<Movie> {
    @Override
    public void create(Connection conn, Movie x) {
        DAOImplementation dao = new DAOImplementation(conn);
        try {
            dao.addMovie(x);
        } catch (SQLException ex) {
            System.err.println("SQLException caught inside JDBC operation!...");
        }
    }

    @Override
    public Movie getByName(Connection conn, String name) {
        DAOImplementation dao = new DAOImplementation(conn);
        try {
            return dao.findMovieByName(name);
        } catch (SQLException ex) {
            System.err.println("SQLException caught inside JDBC operation!...");
        }
        return null;
    }

    @Override
    public Movie getByID(Connection conn, int id) {
        DAOImplementation dao = new DAOImplementation(conn);
        try {
            return dao.findMovieByID(id);
        } catch (SQLException ex) {
            System.err.println("SQLException caught inside JDBC operation!...");
        }
        return null;
    }
}