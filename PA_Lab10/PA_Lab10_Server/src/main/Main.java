package main;

import DatabaseConnection.DatabaseConnection;
import SocketManager.SocketManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Connecting to DataBase!...");
            DatabaseConnection DBCon = DatabaseConnection.getInstance();
            conn = DBCon.getConnection();
            System.out.println("Connected successfully!...\n");
            Statement stmt = conn.createStatement();
            System.out.println("Wiping the database!...");
            String sql = "BEGIN\n" +
                    "   FOR cur_rec IN (SELECT object_name, object_type\n" +
                    "                     FROM user_objects\n" +
                    "                    WHERE object_type IN\n" +
                    "                             ('TABLE',\n" +
                    "                              'VIEW',\n" +
                    "                              'PACKAGE',\n" +
                    "                              'PROCEDURE',\n" +
                    "                              'FUNCTION',\n" +
                    "                              'SEQUENCE',\n" +
                    "                              'SYNONYM',\n" +
                    "                              'PACKAGE BODY'\n" +
                    "                             ))\n" +
                    "   LOOP\n" +
                    "      BEGIN\n" +
                    "         IF cur_rec.object_type = 'TABLE'\n" +
                    "         THEN\n" +
                    "            EXECUTE IMMEDIATE    'DROP '\n" +
                    "                              || cur_rec.object_type\n" +
                    "                              || ' \"'\n" +
                    "                              || cur_rec.object_name\n" +
                    "                              || '\" CASCADE CONSTRAINTS';\n" +
                    "         ELSE\n" +
                    "            EXECUTE IMMEDIATE    'DROP '\n" +
                    "                              || cur_rec.object_type\n" +
                    "                              || ' \"'\n" +
                    "                              || cur_rec.object_name\n" +
                    "                              || '\"';\n" +
                    "         END IF;\n" +
                    "      EXCEPTION\n" +
                    "         WHEN OTHERS\n" +
                    "         THEN\n" +
                    "            DBMS_OUTPUT.put_line (   'FAILED: DROP '\n" +
                    "                                  || cur_rec.object_type\n" +
                    "                                  || ' \"'\n" +
                    "                                  || cur_rec.object_name\n" +
                    "                                  || '\"'\n" +
                    "                                 );\n" +
                    "      END;\n" +
                    "   END LOOP;\n" +
                    "END;";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLSyntaxErrorException ex) {
                ex.printStackTrace();
            } finally {
                System.out.println("Database wiped clean!...");
            }
            System.out.println("Attempting to create table 'users'!...");
            sql = "CREATE TABLE users " +
                    "(name VARCHAR2(50) NOT NULL PRIMARY KEY)";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLSyntaxErrorException ex) {
                System.out.println("Table 'movies' already exists!...");
            } finally {
                System.out.println("Table 'movies' created!...");
            }
        } catch (SQLException ex) {
            System.err.println("Error on DataBase: " + ex);
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found!");
        }
        SocketManager socketManager = new SocketManager(conn);
        socketManager.run();
    }
}
