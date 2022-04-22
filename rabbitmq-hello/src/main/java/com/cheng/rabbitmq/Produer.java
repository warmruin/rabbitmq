package com.cheng.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者 ：发消息
 *
 * @author Cris
 * @date 2022/04/21
 */
public class Produer {
    //队列名称
    public static final String QUEUE_NAME="hello";

    //发消息
    public static void main(String[] args) throws IOException, TimeoutException {
    //创建一个连接工厂
        ConnectionFactory factory=new ConnectionFactory();
        //工厂的IP 连接RabbitMQ的队列
        factory.setHost("192.168.152.129");
        factory.setUsername("admin");
        factory.setPassword("123456");

        //创建连接
        Connection connection = factory.newConnection();


        //获取信道
        Channel channel= connection.createChannel();

        //产生一个队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //发消息
        String message="hello world1!";


        //发送一个消息
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("消息发送完毕");
    }
}
