package com.gjj.website.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gjj.website.facaded.service.RocketMqService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author :
 * @since 2020/1/2 21:08
 */
@Controller
@RequestMapping("RocketMQ")
@Api(description = "RocketMQ")
public class RocketMqController {

    @Reference
    private RocketMqService rocketMqService;


    @PostMapping("sendMessage")
    @ResponseBody
    @ApiOperation(value = "测试RocketMQ发送消息")
    public void sendMessage(@RequestBody Map<Object, Object> map) {
        String message = "123";
        if (map.containsKey("message")) {
            message = map.get("message").toString();
        }
        rocketMqService.sendMessage(message);



    }
}
