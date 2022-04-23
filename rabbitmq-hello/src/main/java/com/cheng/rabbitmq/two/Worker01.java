package com.cheng.rabbitmq.two;

import com.cheng.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * worker01 这是一个工作代码（相当于之前的消费者）
 *
 * @author Cris
 * @date 2022/04/22
 */
public class Worker01 {

    public static final String QUEUE_NAME ="hello";
//接收消息
    public static void main(String[] args) throws Exception {

        Channel channel= RabbitMqUtils.getChannel();

//消息的接收
        DeliverCallback deliverCallback=(consumerTag, message)->{
            System.out.println("接收到的消息"+new String (message.getBody()));
        };
        CancelCallback cancelCallback= consumerTag->{
            System.out.println(consumerTag+"消息者取消消费接口回调逻辑");
        };
        //消息的接收
        System.out.println("C2等待接受消息....");
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }
}
