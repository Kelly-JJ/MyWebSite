package com.gjj.website.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gjj.website.facaded.model.MyInfomation;
import com.gjj.website.facaded.service.MyInfomationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author :
 * @since 2019/12/26 0:43
 */
@Api(description = "个人信息")
@Controller
@RequestMapping("/")
public class MyInfomationController {

    @Reference
    private MyInfomationService service;

    @PostMapping("selectOne")
    @ResponseBody
    @ApiOperation(value = "查询详情")
    public Object selectOne(@RequestBody Map<Object, Object> model) {
        return service.selectOne(Integer.valueOf(model.get("userId").toString()));
    }

    @PostMapping("update")
    @ResponseBody
    @ApiOperation(value = "修改姓名")
    public Object update() {
        MyInfomation myInfomation = new MyInfomation();
        Integer userId = 1;
        myInfomation.setUserId(userId);
        myInfomation.setName("哈哈哈哈");
        service.updateName(myInfomation);
        return service.selectOne(userId);
    }


    @RequestMapping("toTestA")
    @ApiOperation(value = "重定向到页面")
    public String toTestA() {
        return "testA";
    }


}
