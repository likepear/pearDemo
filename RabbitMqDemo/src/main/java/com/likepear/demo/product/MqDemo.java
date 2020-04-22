package com.likepear.demo.product;

import com.likepear.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class MqDemo {

    private static String queueName = "mq_demo";

    public static void main(String[] args) throws IOException, InterruptedException {

        Connection coon = ConnectionUtils.getConnection();
        //创建通道
        Channel channel = coon.createChannel();
/*        //创建队列
        channel.queueDeclare(queueName,false,false,false,null);*/


        String msg = "hello rabbitMq";
        //发布消息
        channel.basicPublish("", queueName, null,msg.getBytes());

        //释放资源
        ConnectionUtils.release(channel,coon);




    }
    
}
