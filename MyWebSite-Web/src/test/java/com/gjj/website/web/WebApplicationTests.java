package com.gjj.website.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class WebApplicationTests {

    @Value("${spring.datasource.driver-class-name}")
    private  String jdbcName;

    @Value("${spring.datasource.url}")
    private  String url;

    @Value("${spring.datasource.data-username}")
    private  String user;

    @Value("${spring.datasource.data-password}")
    private  String password;

    public List<Map<String, Object>> getMessage() {
        List<Map<String, Object>> dataList = new ArrayList<>();
        try {
            Class.forName(jdbcName);
            Connection con = DriverManager.getConnection(url, user, password);
            String sql = "select * from my_infomation ";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(metaData.getColumnName(i), resultSet.getObject(i));
                }
                dataList.add(map);
            }
            for (int i = 0; i < dataList.size(); i++) {
                System.out.println(dataList.get(i).get("live_place"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }

    @Test
    public  void test() {
        System.out.println(jdbcName);
        System.out.println(url);
        System.out.println(user);
        System.out.println(password);
        getMessage();
    }
}
