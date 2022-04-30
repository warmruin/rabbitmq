package com.cheng.rabbitmq.three;

import com.cheng.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * task2
 * 消息在手动应答时是不丢失的，放回队列中重新消费
 * @author Cris
 * @date 2022/04/24
 */
public class Task2 {

    //队列名称
    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明队列
        boolean durable=true;
        channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);
        //从控制台中输入信息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.nextLine();
            //设置生产者发送消息为持久化消息（要求保存到磁盘上）
            channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_BASIC, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("生产者发出消息：" + message);
        }


    }
}
