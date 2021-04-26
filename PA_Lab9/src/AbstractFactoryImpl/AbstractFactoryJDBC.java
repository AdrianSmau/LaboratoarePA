package AbstractFactoryImpl;

import java.sql.Connection;

public interface AbstractFactoryJDBC<T> {
    void create(Connection conn, T x);

    T getByName(Connection conn, String name);

    T getByID(Connection conn, int id);
}
