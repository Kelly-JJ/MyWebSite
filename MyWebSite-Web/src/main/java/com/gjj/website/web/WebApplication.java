package com.gjj.website.web;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.gjj.website.web.core.JDBCDemo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication(scanBasePackages = {"com.gjj.website.*"},exclude = DataSourceAutoConfiguration.class)
@EnableDubbo
@EnableAspectJAutoProxy
public class WebApplication implements CommandLineRunner {


    public static void main(String[] args) {

        SpringApplication.run(WebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.err.println("服务调用者------>>启动完毕");

    }

}
