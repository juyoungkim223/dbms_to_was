package database.connect;

import database.JavaSqlxConnector;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceConnectionTest {
    @Autowired
    JavaSqlxConnector javaSqlxConnector;
    JdbcTemplate jdbcTemplate;
    Connection connection;

    @Test
    public void insert() {
        System.out.println(javaSqlxConnector.getDataSource().toString());
        try {
            connection = javaSqlxConnector.getDataSource().getConnection();
            for (int i = 1; i < 10; i++) {
                jdbcTemplate.update(
                        "INSERT INTO user (user, password) VALUES (?, ?) ",
                        "user" + i, "1234"
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
