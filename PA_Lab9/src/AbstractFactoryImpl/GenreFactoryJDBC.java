package AbstractFactoryImpl;

import DAO.DAOImplementation;
import Entities.Genre;

import java.sql.Connection;
import java.sql.SQLException;

public class GenreFactoryJDBC implements AbstractFactoryJDBC<Genre> {
    @Override
    public void create(Connection conn, Genre x) {
        DAOImplementation dao = new DAOImplementation(conn);
        try {
            dao.addGenre(x);
        } catch (SQLException ex) {
            System.err.println("SQLException caught inside JDBC operation!...");
        }
    }

    @Override
    public Genre getByName(Connection conn, String name) {
        DAOImplementation dao = new DAOImplementation(conn);
        try {
            return dao.findGenreByName(name);
        } catch (SQLException ex) {
            System.err.println("SQLException caught inside JDBC operation!...");
        }
        return null;
    }

    @Override
    public Genre getByID(Connection conn, int id) {
        DAOImplementation dao = new DAOImplementation(conn);
        try {
            return dao.findGenreByID(id);
        } catch (SQLException ex) {
            System.err.println("SQLException caught inside JDBC operation!...");
        }
        return null;
    }
}