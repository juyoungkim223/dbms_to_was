package database.connect.oracle;

import database.JavaSqlConnector;
import java.sql.SQLException;

public class OracleJavaSqlConnector extends JavaSqlConnector {
    private static Class<?> jdbcDriver = null;

    static {
        try {
            jdbcDriver = Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public OracleJavaSqlConnector(String connectionUrl) throws SQLException {
        connect(connectionUrl);
    }

    public OracleJavaSqlConnector(String connectionUrl, String username, String password) throws SQLException {
        connect(connectionUrl, username, password);
    }
}
