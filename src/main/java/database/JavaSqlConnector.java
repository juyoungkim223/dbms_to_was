package database;

import lombok.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//common features :get connection with each db vendor without connection pool
@Getter
@NoArgsConstructor
@ToString
public abstract class JavaSqlConnector implements DataBaseConnector {
    private Connection connection;

    @Override
    public boolean connect(String connectionUrl) throws SQLException {
        connection = DriverManager.getConnection(connectionUrl);
        return connection != null;
    }
    @Override
    public boolean connect(String connectionUrl, String username, String password) throws SQLException {
        connection = DriverManager.getConnection(connectionUrl, username, password);
        return connection != null;
    }

    public void close() throws SQLException {
        connection.close();
    }
}
