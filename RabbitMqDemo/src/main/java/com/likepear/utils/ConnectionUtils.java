package com.likepear.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * RabbitMq连接工具类
 */
public class ConnectionUtils {

    private static ConnectionFactory factory;
    private static String username = "likepear";
    private static String password = "5200";
    private static String host = "localhost";
    private static String virtualHost = "/";
    private static int port = 5672;

    static{

        factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setVirtualHost(virtualHost);
        factory.setUsername(username);
        factory.setPassword(password);

    }

    /**
     * 获取连接对象
     * @author likepear
     * @return
     */
    public static Connection getConnection(){

        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;

    }

    /**
     * 释放资源
     * @author likepear
     */
    public static void release(Channel channel,Connection coon){

        try{

            if(channel!=null){
                channel.close();
            }

            if(coon!=null){
                coon.close();
            }

        }catch (Exception e){

        }finally {
            channel = null;
            coon = null;
        }


    }

}
