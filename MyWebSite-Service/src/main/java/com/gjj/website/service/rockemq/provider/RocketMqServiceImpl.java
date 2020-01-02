package com.gjj.website.service.rockemq.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.gjj.website.facaded.service.RocketMqService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author :
 * @since 2020/1/2 21:09
 */
@Service(interfaceClass = RocketMqService.class)
public class RocketMqServiceImpl implements RocketMqService {

    @Resource
    private RocketMQTemplate template;

    @Override
    public void sendMessage(String message) {
        template.convertAndSend("test-topic-1", message+"-----------------");
        template.send("test-topic-2", MessageBuilder.withPayload(message).build());

        System.err.println("发送成功...");
    }
}
