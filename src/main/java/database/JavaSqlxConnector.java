package database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

//common features :get connection with each db vendor using connection pool
@Component
@Getter
public class JavaSqlxConnector {
    @Autowired
    DataSource dataSource;
    //ConnectionFactory connectionFactory =
}
