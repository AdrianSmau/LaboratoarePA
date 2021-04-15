package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance = null;
    private Connection connection;
    private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String username = "STUDENT";
    private final String password = "STUDENT";

    private DatabaseConnection() throws SQLException {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex){
            System.err.println("Database Connection Failed!...");
        }
    }
    public Connection getConnection() {
        return connection;
    }
    public static DatabaseConnection getInstance() throws SQLException{
        if(instance == null){
            instance= new DatabaseConnection();
        } else
            if(instance.getConnection().isClosed()){
                instance = new DatabaseConnection();
            }
            return instance;
    }
}
