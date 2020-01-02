package com.gjj.website.service.rockemq.consumer;

import com.alibaba.dubbo.config.annotation.Service;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

/**
 * @author :
 * @since 2020/1/2 22:33
 */
@Service
@RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer_test-topic-2")
public class RocketMqConsumerImpl implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        System.out.println(s);
    }
}
