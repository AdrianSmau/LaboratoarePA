package ConnectionPooling;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class C3PODataSource {

    private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();

    static {
        try {
            dataSource.setDriverClass("oracle.jdbc.driver.OracleDriver");
            dataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
            dataSource.setUser("STUDENT");
            dataSource.setPassword("STUDENT");
            dataSource.setMinPoolSize(2);
            dataSource.setMaxPoolSize(100);
            dataSource.setMaxStatements(1000);
        } catch (PropertyVetoException ex) {
            System.err.println("PropertyVetoException caught when creating the dataSource!...");
        }
    }

    public static ComboPooledDataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConn() throws SQLException {
        return dataSource.getConnection();
    }
}
