package database.connect;

import java.sql.SQLException;

public interface DataBaseConnector {
    boolean connect(String connectionUrl) throws SQLException;
}
