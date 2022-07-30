package com.qf.DBUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class DBUtil1 {
    public static final String JDBC_PROPERTIES="jdbc.properties";
    private static String driverName;
    private static String url;
    private static String username;
    private static String password;
    static {
        //注册驱动
        InputStream inputStream = DBUtil1.class.getClassLoader().getResourceAsStream(JDBC_PROPERTIES);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driverName = properties.getProperty("driverClass");
        url =properties.getProperty("jdbcUrl");
        username = properties.getProperty("jdbcUsername");
        password = properties.getProperty("jdbcPassword");
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(){
        //连接数据库
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    /**
     * 释放资源
     * @param connection
     * @throws SQLException
     */
    public static  void close(Connection connection, PreparedStatement preparedStatement) throws SQLException {
        connection.close();
        preparedStatement.close();
    }
    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        resultSet.close();
        close(connection,preparedStatement);
    }
    public static void close(Connection connection) throws SQLException {
        connection.close();
    }
    public static void close(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
    }
}
