package com.jy.controller;

import database.connect.JavaConnector;
import database.connect.mssql.mssqlJavaConnector;
import database.connect.mysql.mysqlJavaConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Controller
@PropertySource("classpath:dbConnection.properties")
public class HomeController {
    @Value("${mssqlServerConnectionUrl}")
    String mssqlServerConnectionUrl;

    @Value("${mysqlServerConnectionUrl}")
    String mysqlServerConnectionUrl;

    @GetMapping("/home")
    public String showHome() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(mssqlServerConnectionUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "home.jsp";
    }

    @GetMapping("/home2")
    public String mssqlconnector() {
        try {
            mssqlJavaConnector connector = new mssqlJavaConnector(mssqlServerConnectionUrl);
            System.out.println(connector.getConnection());
            connector.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return "home.jsp";
    }
    @GetMapping("/home3")
    public String mysqlconnector() {
        try {
            mysqlJavaConnector connector = new mysqlJavaConnector(mysqlServerConnectionUrl);
            System.out.println(connector.getConnection());
            connector.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return "home.jsp";
    }
}
