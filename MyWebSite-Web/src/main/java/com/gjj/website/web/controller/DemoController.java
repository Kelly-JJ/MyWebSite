package com.gjj.website.web.controller;

import com.gjj.website.web.core.JDBCDemo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author :
 * @since 2020/2/22 19:28
 */
@Controller
public class DemoController {

    @Resource
    private JDBCDemo jdbcDemo;

        @RequestMapping("/test")
    public void testDemo(){
        List<Map<String, Object>> message = jdbcDemo.getMessage();
        for (Map<String, Object> map : message) {
            System.out.println(map.get("name"));
        }
    }
}
