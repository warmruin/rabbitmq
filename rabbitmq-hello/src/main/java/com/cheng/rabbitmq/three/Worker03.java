package com.cheng.rabbitmq.three;

import com.cheng.rabbitmq.utils.RabbitMqUtils;
import com.cheng.rabbitmq.utils.SleepUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class Worker03 {

    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("C1等待接受消息处理时间较短");
        //消息消费的时候如何处理消息
        DeliverCallback deliverCallback = (consumerTag, delivery) ->
        {
            String message = new String(delivery.getBody());
            SleepUtils.sleep(1);
            System.out.println("接收到消息:" + message);
/**
 * 1.消息标记 tag
 * 2.是否批量应答未应答消息
 */
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

        };

        //采用手动应答
        boolean autoAck=false;
        channel.basicConsume(TASK_QUEUE_NAME,autoAck,deliverCallback,(consumerTag)->{
            System.out.println(consumerTag+"消费者取消消费接口回调逻辑");
        });

    }

}
