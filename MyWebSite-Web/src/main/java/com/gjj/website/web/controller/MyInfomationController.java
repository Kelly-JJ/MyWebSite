package com.gjj.website.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gjj.website.common.util.RedisUtil;
import com.gjj.website.facaded.model.common.RedisKey;
import com.gjj.website.facaded.model.entity.MyInfomation;
import com.gjj.website.facaded.service.MyInfomationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @Resource
    private RedisUtil redisUtil;

    @PostMapping("selectOne")
    @ResponseBody
    @ApiOperation(value = "查询详情")
    public Object selectOne(@RequestBody Map<Object, Object> model) {
        MyInfomation myInfomation ;
        String userId = model.get("userId").toString();
        String key = RedisKey.MYINFOMATION_USER_ID_+userId;
        if (!redisUtil.hasKey(key)){
            myInfomation= service.selectOne(Integer.valueOf(userId));
            redisUtil.set(key,myInfomation,2000);
        }else{
            myInfomation =(MyInfomation)redisUtil.get(key);
        }

        return myInfomation;
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
        redisUtil.del(RedisKey.MYINFOMATION_USER_ID_+myInfomation.getUserId().toString());
        return service.selectOne(userId);
    }


    @GetMapping("toTestA")
    @ApiOperation(value = "重定向到页面")
    public String toTestA() {
        return "testA";
    }




}
