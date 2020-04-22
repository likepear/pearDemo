package com.likepear.demo;

import com.likepear.utils.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 简单队列消费者demo
 */
public class SimpleQueueConsumer {

    private static String queueName = "mq_demo";

    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = ConnectionUtils.getConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(queueName,false,false,false,null);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String msg = new String(body,"utf-8");
                System.out.println(msg);

            }
        };

        //监听队列 进行消费
        channel.basicConsume(queueName,true,defaultConsumer);

    }
}
