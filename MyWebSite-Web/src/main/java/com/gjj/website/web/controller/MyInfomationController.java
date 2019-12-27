package com.gjj.website.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gjj.website.facaded.model.MyInfomation;
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
    private MyInfomationService service;

    @RequestMapping("selectOne")
    @ResponseBody
    public Object selectOne() {
        Integer userId = 1;
        return service.selectOne(userId);
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update() {
        MyInfomation myInfomation = new MyInfomation();
        Integer userId = 1;
        myInfomation.setUserId(userId);
        myInfomation.setName("哈哈哈哈");
        service.updateName(myInfomation);
        return service.selectOne(userId);
    }

}
