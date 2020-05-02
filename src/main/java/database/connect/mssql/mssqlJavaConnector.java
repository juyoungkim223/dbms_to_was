package database.connect.mssql;

import database.connect.JavaConnector;
import java.sql.SQLException;

public class mssqlJavaConnector extends JavaConnector {
    private static Class<?> jdbcDriver = null;

    static {
        try {
            jdbcDriver = Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public mssqlJavaConnector(String connectionUrl) throws SQLException {
        connect(connectionUrl);
    }
}
