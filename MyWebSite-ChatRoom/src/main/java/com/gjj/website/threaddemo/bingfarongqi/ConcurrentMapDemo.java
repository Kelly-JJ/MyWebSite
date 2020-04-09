package com.gjj.website.threaddemo.bingfarongqi;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author :
 * @since 2020/3/19 0:39
 */
public class ConcurrentMapDemo {


    public static void main(String[] args) {
        //3148   2493    2371    2778
        Map<String, String> map = new ConcurrentHashMap<>(10000000);
//        Map<String, String> map = new ConcurrentSkipListMap<>();//高并发   并且排序
        //2742   3325     2641   3236
//        Map<String, String> map = new HashMap<>(10000000);
        //4314    3982   3982    4517
//        Map<String, String> map = new Hashtable<>(10000000);
        Random r = new Random();
        Thread[] ths = new Thread[100];

        //弄了个门栓，门栓的次数为100
        CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    //每个线程都往map中添加1w+数据
                    map.put("a" + r.nextInt(10000), "a" + r.nextInt(10000));
                }
                //门栓-1
                latch.countDown();
            });
        }


        Arrays.asList(ths).forEach(v -> v.start());

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
