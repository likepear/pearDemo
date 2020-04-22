package com.likepear.demo;

import com.likepear.utils.ConnectionUtils;
import com.rabbitmq.client.Connection;

/**
 * 死信队列demo
 */
public class DlxQueueDemo {

    public static void main(String[] args) {

        Connection connection = ConnectionUtils.getConnection();

    }

}
