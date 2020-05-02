package database.connect.mysql;

import database.connect.JavaConnector;

import java.sql.SQLException;

public class mysqlJavaConnector extends JavaConnector {
    private static Class<?> jdbcDriver = null;

    static {
        try {
            jdbcDriver = Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public mysqlJavaConnector(String connectionUrl) throws SQLException {
        connect(connectionUrl);
    }
}
