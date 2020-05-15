package com.common.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class UserDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

}
