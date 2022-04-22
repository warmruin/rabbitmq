package com.cheng.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者 接受消息
 *
 * @author Cris
 * @date 2022/04/22
 */
public class Consumer {
    //队列名称
    public static final String QUEUE_NAME="hello";

    //接收消息
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("192.168.152.129");
        factory.setUsername("admin");
        factory.setPassword("123456");

        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();

        //声明
        DeliverCallback deliverCallback=(consumerTag,message)->{
            System.out.println(new String (message.getBody()));
        };


        CancelCallback cancelCallback=consumerTag->{
            System.out.println("消息消费被中断");
        };
        /*消费者消费消息
        *
        * 1.消费哪个队列
        * 2.消费成功之后是否要自动应答 true代表的自动应答 false代表手动应答
        * 3.消费者未成功消费的回调
        * 4.消费者取消消费的回调
        * */

        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback );
    }
}
