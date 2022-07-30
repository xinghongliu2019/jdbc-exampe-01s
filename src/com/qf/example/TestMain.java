package com.qf.example;

import java.sql.*;


public class TestMain {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String JDBC_URL = "jdbc:mysql:// localhost:3306/db_jdbc_2204";
    public static final String JDBC_USER = "root";
    public static final String JDBC_PASSWORD = "root";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /**
         * 1.加载驱动
         */
        Class.forName(JDBC_DRIVER);
        /**
         * 2.建立数据库连接
         */
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        /**
         * 3.创建statement对象
         */
        Statement statement = connection.createStatement();
        /**
         * 4.编写SQL语句
         * 注册用户
         * 通过用户名查询信息
         */
        String username = "张三";
        //String sql = "INSERT INTO sys_user(uname,password,phone) VALUES ('张三','15646','115754') ";
        String sql = "SELECT * FROM sys_user";
        //5执行SQL语句
        //int count = statement.executeUpdate(sql);
        ResultSet rs = statement.executeQuery(sql);

        //6.处理结果
        int columnCount = rs.getMetaData().getColumnCount();
        while (rs.next()){
            StringBuilder str = new StringBuilder();
            for (int i =1;i<=columnCount;i++){
                String string = rs.getString(i);
                str.append(string).append(",");
            }
            System.out.println(str);
        }
//        if (count>0){
//            System.out.println("注册成功");
//        }else {
//            System.out.println("注册失败");
//        }
//        while (rs.next()) {
//            // 通过字段顺序获取字段值
////				int empno = rs.getInt(1);
////				String ename = rs.getString(2);
//            // 通过字段名获取字段值
//            String uname = rs.getString("uname");
//            String password = rs.getString("password");
//            String phone = rs.getString("phone");
//            System.out.println("uname：" + uname +
//                    "；password：" + password + "；phone：" + phone);
//        }
        /**
         * 7.释放资源
         */
        rs.close();
        statement.close();
        connection.close();
    }

}
