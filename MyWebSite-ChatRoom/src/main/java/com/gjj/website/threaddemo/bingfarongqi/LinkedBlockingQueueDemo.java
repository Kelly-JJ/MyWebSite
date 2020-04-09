package com.gjj.website.threaddemo.bingfarongqi;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author :
 * @since 2020/3/19 1:16
 */
public class LinkedBlockingQueueDemo {

    static BlockingQueue<String> strs = new LinkedBlockingDeque<>();

    static Random r = new Random();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    strs.put("a" + i);//如果满了，将会等待
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"p1").start();


        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
               for (;;){
                   try {
                       //take方法,如果队列空了，则会等待
                       System.out.println(Thread.currentThread().getName()+" take - "+strs.take());
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
            },"p1").start();

        }
    }
}
