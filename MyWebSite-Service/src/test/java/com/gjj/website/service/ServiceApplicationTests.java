package com.gjj.website.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;

@SpringBootTest
class ServiceApplicationTests {

    @Value("${spring.dataSource.driver-class-name}")
    private String jdbcName;

    @Value("${spring.dataSource.url}")
    private String url;
    @Value("${spring.dataSource.data-username}")
    private String user;
    @Value("${spring.dataSource.data-password}")
    private String password;

    @Test
    void contextLoads() {
        try {
            Class.forName(jdbcName);
            Connection con = DriverManager.getConnection(url, user, password);
            String sql = "select * from my_infomation ";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("userId:"+resultSet.getString("user_id")+"  name:"+resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
