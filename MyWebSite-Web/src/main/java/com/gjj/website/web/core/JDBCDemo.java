package com.gjj.website.web.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :
 * @since 2020/2/21 15:47
 */

@Component
public class JDBCDemo {
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
            //要修改的语句
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }

}
