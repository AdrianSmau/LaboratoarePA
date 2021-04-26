package AbstractFactoryImpl;

import DAO.DAOImplementation;
import Entities.Actor;

import java.sql.Connection;
import java.sql.SQLException;

public class ActorFactoryJDBC implements AbstractFactoryJDBC<Actor> {
    @Override
    public void create(Connection conn, Actor x) {
        DAOImplementation dao = new DAOImplementation(conn);
        try {
            dao.addActor(x);
        } catch (SQLException ex) {
            System.err.println("SQLException caught inside JDBC operation!...");
        }
    }

    @Override
    public Actor getByName(Connection conn, String name) {
        DAOImplementation dao = new DAOImplementation(conn);
        try {
            return dao.findActorByName(name);
        } catch (SQLException ex) {
            System.err.println("SQLException caught inside JDBC operation!...");
        }
        return null;
    }

    @Override
    public Actor getByID(Connection conn, int id) {
        DAOImplementation dao = new DAOImplementation(conn);
        try {
            return dao.findActorByID(id);
        } catch (SQLException ex) {
            System.err.println("SQLException caught inside JDBC operation!...");
        }
        return null;
    }
}