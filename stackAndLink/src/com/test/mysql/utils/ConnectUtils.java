package com.test.mysql.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * jdbc工具类
 * @author likepear
 * @date 2019年12月12日
 */
public class ConnectUtils {

    private static String className;
    private static String url;
    private static String username;
    private static String password;

    static {

        try {

            Properties prop = new Properties();
            InputStream resourceAsStream = ConnectUtils.class.getResourceAsStream("/db.properties");
            prop.load(resourceAsStream);
            className = prop.getProperty("driverClass");
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            Class.forName(className);

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取连接对象
     * @return
     */
    public static Connection getConnection() {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;

    }

    /**
     * 释放资源
     * @param result
     * @param statement
     * @param coon
     */
    public static void release(ResultSet result, Statement statement, Connection coon) {
        try {
            if (result != null) {
                result.close();
            }
            if(statement!=null){
                statement.close();
            }
            if(coon!=null){
                coon.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean execute(Statement statement,String sql){

        boolean res = false;
        try {
            res = statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;

    }

    public static ResultSet executeQuery(Statement statement,String sql) throws SQLException {

        ResultSet resultSet = statement.executeQuery(sql);

        return resultSet;

    }


}
