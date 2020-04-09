package com.gjj.website.threaddemo.bingfarongqi;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author :
 * @since 2020/3/19 1:00
 */
public class CopyOnWriteListDemo {

    public static void main(String[] args) {
        List<String> lists =
//                new ArrayList<>();
//                new Vector<>();
                  new CopyOnWriteArrayList<>();
        Random r = new Random();
        Thread[] ths = new Thread[100];
        for (int i = 0; i < ths.length; i++) {
            Runnable runnable = () -> {
                for (int i1 = 0; i1 < 1000; i1++) {
                    lists.add("a" + r.nextInt(10000));
                }
            };
            ths[i] = new Thread(runnable);
        }
        runAndComputeTime(ths);
        System.out.println(lists.size());
    }


    static void runAndComputeTime(Thread[] ths) {
        long start = System.currentTimeMillis();
        Arrays.asList(ths).forEach(v -> v.start());

        Arrays.asList(ths).forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

