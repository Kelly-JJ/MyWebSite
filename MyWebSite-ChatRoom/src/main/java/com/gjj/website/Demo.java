package com.gjj.website;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :
 * @since 2020/2/21 14:43
 */
public class Demo {
    @Value("${spring.dataSource.driver-class-name}")
    private static String jdbcName;

    @Value("${spring.datasource.url}")
    private static String url;

    @Value("${spring.dataSource.data-username}")
    private static String user;

    @Value("${spring.dataSource.data-password}")
    private static String password;

    public static List<Map<String, Object>> getMessage() {
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
}
