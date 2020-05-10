package database.connect.mssql;

import database.JavaSqlConnector;
import java.sql.SQLException;

public class MssqlJavaSqlConnector extends JavaSqlConnector {
    private static Class<?> jdbcDriver = null;

    static {
        try {
            jdbcDriver = Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MssqlJavaSqlConnector(String connectionUrl) throws SQLException {
        connect(connectionUrl);
    }
    public MssqlJavaSqlConnector(String connectionUrl, String username, String password) throws SQLException {
        connect(connectionUrl, username, password);
    }
}
