package database.connect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.sql.DataSource;
import java.sql.SQLException;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/applicationContext.xml"})
public class DataSourceConnectionTest {
    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Test
    public void insert() {
        System.out.println("datasource: " + dataSource.toString());
        try {
            System.out.println("datasource: " + dataSource.getConnection().toString());
            jdbcTemplate.update("INSERT INTO Member (username, password) VALUES (?, ?) ",
                    "user", "1234");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
