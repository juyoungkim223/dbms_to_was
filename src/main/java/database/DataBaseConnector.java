package database;

import java.sql.SQLException;

public interface DataBaseConnector {
    boolean connect(String connectionUrl) throws SQLException;
    boolean connect(String connectionUrl, String username, String password) throws SQLException;
}
