package AbstractFactoryImpl;

import DAO.DAOImplementation;
import Entities.Director;

import java.sql.Connection;
import java.sql.SQLException;

public class DirectorFactoryJDBC implements AbstractFactoryJDBC<Director> {
    @Override
    public void create(Connection conn, Director x) {
        DAOImplementation dao = new DAOImplementation(conn);
        try {
            dao.addDirector(x);
        } catch (SQLException ex) {
            System.err.println("SQLException caught inside JDBC operation!...");
        }
    }

    @Override
    public Director getByName(Connection conn, String name) {
        DAOImplementation dao = new DAOImplementation(conn);
        try {
            return dao.findDirectorByName(name);
        } catch (SQLException ex) {
            System.err.println("SQLException caught inside JDBC operation!...");
        }
        return null;
    }

    @Override
    public Director getByID(Connection conn, int id) {
        DAOImplementation dao = new DAOImplementation(conn);
        try {
            return dao.findDirectorByID(id);
        } catch (SQLException ex) {
            System.err.println("SQLException caught inside JDBC operation!...");
        }
        return null;
    }
}