package com.gjj.website.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gjj.website.facaded.service.MyInfomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author :
 * @since 2019/12/26 0:43
 */
@Controller
@RequestMapping("/")
public class MyInfomationController {

    @Reference
//    @Resource
    private MyInfomationService service;

    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        String str = "hello";
        String hello = service.hello(str);
        return hello;
    }
}
