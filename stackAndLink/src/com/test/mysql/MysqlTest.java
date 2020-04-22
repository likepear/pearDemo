package com.test.mysql;

import com.test.mysql.utils.ConnectUtils;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class MysqlTest {

    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.jdbc.Driver");

        Connection coon = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "5200");
        Statement statement = coon.createStatement();

        String sql = " select username,password from tb_user ";
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
        }

        resultSet.close();
        statement.close();
        coon.close();

    }

    @Test
    public void test(){

        Connection coon = ConnectUtils.getConnection();

        String sql = " select username,password from tb_user ";
        try {
            PreparedStatement prep = coon.prepareStatement(sql);
            ResultSet resultSet = prep.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
            }
            ConnectUtils.release(resultSet, prep, coon);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
