package database.connect.datasource.mysql;

import database.JavaSqlConnector;

import java.sql.SQLException;

public class MysqlJavaSqlConnector extends JavaSqlConnector {
    private static Class<?> jdbcDriver = null;

    static {
        try {
            jdbcDriver = Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MysqlJavaSqlConnector(String connectionUrl) throws SQLException {
        connect(connectionUrl);
    }

    public MysqlJavaSqlConnector(String connectionUrl, String username, String password) throws SQLException {
        connect(connectionUrl, username, password);
    }
}
