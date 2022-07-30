package com.qf.example;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Test {
    /**
     * 1.加载驱动
     */
    public static final String JDBC_PROPERTIES="jdbc.properties";

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        /**
         *
         * 2.输入流连接数据库
         */
        InputStream resourceAsStream = Test.class.getClassLoader().getResourceAsStream(JDBC_PROPERTIES);
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        String driverClass = properties.getProperty("driverClass");
        String jdbcUrl = properties.getProperty("jdbcUrl");
        String jdbcUsername = properties.getProperty("jdbcUsername");
        String jdbcPassword = properties.getProperty("jdbcPassword");
        Connection connection = DriverManager.getConnection(jdbcUrl,jdbcUsername,jdbcPassword);

        Class.forName(driverClass);
        /**
         * 3.预编译SQL语句
         * 4.创建PreparedStatement对象
         */
        String sql = "SELECT * FROM sys_user WHERE uname=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"知识点");
        ResultSet resultSet = preparedStatement.executeQuery();
        /**
         * 5.执行SQL语句进行验证
         */
        SysUser sysUser = null;
        while (resultSet.next()){
            int uid = resultSet.getInt("uid");
            String uname = resultSet.getString(SysUser.COL_UNAME);
            String password = resultSet.getString(SysUser.COL_PASSWORD);
            String phone = resultSet.getString(SysUser.COL_PHONE);
            int status = resultSet.getInt("status");
            sysUser = new SysUser();
            sysUser.setUid(uid);
            sysUser.setUname(uname);
            sysUser.setPassword(password);
            sysUser.setPhone(phone);
            sysUser.setStatus(status);
        }
        /**
         * 6.处理结果
         */
        if (sysUser !=null){
            if (sysUser.getStatus()!=0){
                if (sysUser.getPassword().equals("15646")){
                    System.out.println("登入成功");
                    System.out.println(sysUser.toString());
                }else {
                    System.out.println("密码错误");
                }
            }else {
                System.out.println("账号被禁用");
            }
        }else {
            System.out.println("用户名不存在");
        }
        /**
         * 7.释放资源
         */
        resultSet.close();
        preparedStatement.close();
        connection.close();

    }
}
