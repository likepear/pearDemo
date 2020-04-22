package com.likepear.demo.consumer;

import com.likepear.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Send1 {

    private static String queueName = "mq_demo";

    public static void main(String[] args) throws IOException, InterruptedException {

        Connection coon = ConnectionUtils.getConnection();
        //创建通道
        Channel channel = coon.createChannel();
/*        //创建队列
        channel.queueDeclare(queueName,false,false,false,null);*/

        int counter = 0;
        while (counter<30) {

            String msg = "<h1 style='color:red;font-size:16px'>你好啊！user2</h1>";
            //发布消息
            channel.basicPublish("", queueName, null,msg.getBytes());
            counter++;
            Thread.sleep(4000);
        }

        //释放资源
        ConnectionUtils.release(channel,coon);

    }

}
