package com.gjj.website.chatroom;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest

@RunWith(SpringJUnit4ClassRunner.class)
class ChatroomApplicationTests {
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
//            ProducerRecord<byte[],byte[]> record = new ProducerRecord<>(TOPIC,"100001".getBytes("utf-8"),"bbb".getBytes("utf-8"));
//
//            String url = "jdbc:mysql://localhost:3306/website?serverTimezone=UTC";
//            String jdbcName = "com.mysql.jdbc.Driver";
//            String user = "root";
//            String password = "admin";
            Class.forName(jdbcName);
            Connection con = DriverManager.getConnection(url, user, password);
            String sql = "select * from my_infomation ";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            List<Map<String, Object>> dataList = new ArrayList<>();
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
    }
}
