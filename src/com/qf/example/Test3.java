package com.qf.example;

import com.qf.DBUtil.DBUtil1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test3 {
    public static void main(String[] args) throws SQLException {
        //连接数据库
        Connection connection = DBUtil1.getConnection();
        /**
         * 预编译SQL语句
         */
        String sql = "SELECT * FROM sys_user";
        //创建pr对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        //执行结果
        int sun = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()){
            StringBuilder str = new StringBuilder();
            for (int i = 1; i <= sun ; i++) {
                String string = resultSet.getString(i);
                str.append(string).append(",");
            }
            System.out.println(str);
        }
        //释放资源
        DBUtil1.close(connection,preparedStatement,resultSet);
    }
}
