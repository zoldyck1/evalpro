package com.eval.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

    private static final String URL  = getEnvOrDefault("DB_URL",
        "jdbc:mysql://localhost:3306/evaluation_db?useSSL=false&serverTimezone=UTC");
    private static final String USER = getEnvOrDefault("DB_USER", "root");
    private static final String PASS = getEnvOrDefault("DB_PASS", "");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC driver not found", e);
        }
    }

    private DBConnection() {}

    private static String getEnvOrDefault(String key, String fallback) {
        String value = System.getenv(key);
        return (value != null && !value.isEmpty()) ? value : fallback;
    }

    public static Connection get() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
