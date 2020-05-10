package database.connect;

import database.connect.mssql.MssqlJavaSqlConnector;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class connectionTest {
    Map<String, String> testProperties = new HashMap<>();

    @Before
    public void setUp() throws IOException {
        Properties prp=new Properties();
        String propFilePath = "testdbConnection.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        prp.load(new ByteArrayInputStream(loader.getResourceAsStream(propFilePath).readAllBytes()));
        for(Object key : prp.keySet()) {
            testProperties.put((String)key, prp.getProperty((String)key));
        }
    }

    @Test
    public void mssqlconnector() {
        try {
            MssqlJavaSqlConnector connector = new MssqlJavaSqlConnector(testProperties.get("mssqlServerConnectionUrl"),
                    testProperties.get("mssqlServerConnectionUsername"),
                    testProperties.get("mssqlServerConnectionPassword"));
            System.out.println(connector.getConnection());
            connector.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void mysqlconnector() {
        try {
            MssqlJavaSqlConnector connector = new MssqlJavaSqlConnector(testProperties.get("mysqlServerConnectionUrl"),
                    testProperties.get("mysqlServerConnectionUsername"),
                    testProperties.get("mysqlServerConnectionPassword"));
            System.out.println(connector.getConnection());
            connector.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void oracleconnector() {

    }
}
