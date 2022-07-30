package com.qf.example;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Test2 {
    /**
     * 加载驱动
     */
    public static final String JDBC_PROPERTIES="jdbc.properties";

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        /**
         * 连接数据库
         */
        InputStream in =Test2.class.getClassLoader().getResourceAsStream(JDBC_PROPERTIES);
        Properties properties = new Properties();
        properties.load(in);
        String driverClass = properties.getProperty("driverClass");
        String jdbcUrl = properties.getProperty("jdbcUrl");
        String jdbcUsername = properties.getProperty("jdbcUsername");
        String jdbcPassword = properties.getProperty("jdbcPassword");
        Connection connection = DriverManager.getConnection(jdbcUrl,jdbcUsername,jdbcPassword);
        Class.forName(driverClass);

        /**
         * 预编译SQL语句
         */
        String sql = "INSERT INTO sys_user(uname,password,phone) VALUES(?,?,?)";
        /**
         * 创建PreparedStatement对象
         */
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"李四");
        preparedStatement.setString(2,"123456");
        preparedStatement.setString(3,"11242");
        /**
         * 执行SQL语句
         */
        int con  = preparedStatement.executeUpdate();
        /**
         * 处理结果
         */
        if (con >0){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
        /**
         * 释放资源
         */
        preparedStatement.close();
        connection.close();
    }
}
