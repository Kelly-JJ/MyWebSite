package com.gjj.website;


import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author :
 * @since 2020/2/21 13:38
 */
@Component
public class JDBCDemo {


    public static void main(String[] args) {
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(1,
                        2,
                        0,
                        TimeUnit.SECONDS,
                        new LinkedBlockingQueue<Runnable>() {
                        });
        executor.execute(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(123);
            }
        }));

        System.out.println(executor.isShutdown());

        System.out.println(executor.getActiveCount());
        System.out.println(executor.getPoolSize());

        System.out.println(executor.getMaximumPoolSize());

    }

    public static String replace(String a, String b, String c) {


        char[] bChar = b.toCharArray();
        char[] aChar = a.toCharArray();
        char[] cChar = c.toCharArray();
        StringBuilder resultChar = new StringBuilder();

        for (int i = 0; i < aChar.length; i++) {
            if (aChar[i] == bChar[0] && (i + b.length()) <= aChar.length) {
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < b.length(); k++) {
                    sb.append(aChar[i + k]);
                }
                if (sb.toString().equals(b)) {
                    for (int j = 0; j < cChar.length; j++) {
                        resultChar.append(cChar[j]);
                    }
                    i = i + cChar.length - 1;
                } else {
                    resultChar.append(aChar[i]);
                }
            } else {
                resultChar.append(aChar[i]);
            }
        }
        return resultChar.toString();
    }


}
