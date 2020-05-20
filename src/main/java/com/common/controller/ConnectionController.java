package com.common.controller;

import database.connect.basic.mssql.MssqlJavaSqlConnector;
import database.connect.basic.mysql.MysqlJavaSqlConnector;
import database.connect.basic.oracle.OracleJavaSqlConnector;
import database.sql.SqlConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@PropertySource("classpath:db.properties")
public class ConnectionController {
    @Autowired
    DataSource dataSource;

    @Value("${mssqlServerConnectionUrl}")
    String mssqlServerConnectionUrl;
    @Value("${mssqlServerConnectionUsername}")
    String mssqlServerConnectionUsername;
    @Value("${mssqlServerConnectionPassword}")
    String mssqlServerConnectionPassword;

    @Value("${mysqlServerConnectionUrl}")
    String mysqlServerConnectionUrl;
    @Value("${mysqlServerConnectionUsername}")
    String mysqlServerConnectionUsername;
    @Value("${mysqlServerConnectionPassword}")
    String mysqlServerConnectionPassword;

    @Value("${oracleServerConnectionUrl}")
    String oracleServerConnectionUrl;
    @Value("${oracleServerConnectionUsername}")
    String oracleServerConnectionUsername;
    @Value("${oracleServerConnectionPassword}")
    String oracleServerConnectionPassword;

    @GetMapping("/home")
    public String showHome() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection connection = DriverManager.getConnection(mssqlServerConnectionUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "home.jsp";
    }

    @GetMapping("/home2")
    public String mssqlconnector() {
        String res = null;
        try {
            MssqlJavaSqlConnector connector = new MssqlJavaSqlConnector(mssqlServerConnectionUrl, mssqlServerConnectionUsername, mssqlServerConnectionPassword);
            res = connector.getConnection().toString();
            SqlConnection sqlConnection = new SqlConnection(connector.getConnection());
            //sqlConnection.prepareStatement();
            sqlConnection.closeAll();

            connector.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    @GetMapping("/home3")
    public String mysqlconnector() {
        String res = null;

        try {
            dataSource.getConnection();
            MysqlJavaSqlConnector connector = new MysqlJavaSqlConnector(mysqlServerConnectionUrl, mysqlServerConnectionUsername, mysqlServerConnectionPassword);
            res = connector.getConnection().toString();
            connector.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    @GetMapping("/home4")
    public String oracleconnector() {
        try {
            OracleJavaSqlConnector connector = new OracleJavaSqlConnector(oracleServerConnectionUrl, oracleServerConnectionUsername, oracleServerConnectionPassword);
            System.out.println(connector.getConnection());
            connector.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return "home.jsp";
    }

    @GetMapping("/datasource")
    public String javaxMysqlConnector() throws SQLException {
        Connection conn = dataSource.getConnection();

        return conn.toString();
    }
}
