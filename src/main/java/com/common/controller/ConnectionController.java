package com.common.controller;

import database.connect.mssql.MssqlJavaSqlConnector;
import database.connect.mysql.MysqlJavaSqlConnector;
import database.connect.oracle.OracleJavaSqlConnector;
import database.sql.SqlConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RestController
@PropertySource("classpath:dbConnection.properties")
public class ConnectionController {
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
            
            connector.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    //service mysql start
    @GetMapping("/home3")
    public String mysqlconnector() {
        String res = null;
        try {
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
}