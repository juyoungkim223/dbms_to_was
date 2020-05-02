package database.connect;

import database.connect.DataBaseConnector;
import lombok.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//common features : get connection without connection pool
@Getter
@NoArgsConstructor
@ToString
public abstract class JavaConnector implements DataBaseConnector {
    private Connection connection;

    @Override
    public boolean connect(String connectionUrl) throws SQLException {
        connection = DriverManager.getConnection(connectionUrl);
        return connection != null;
    }

    public void close() throws SQLException {
        connection.close();
    }
}
