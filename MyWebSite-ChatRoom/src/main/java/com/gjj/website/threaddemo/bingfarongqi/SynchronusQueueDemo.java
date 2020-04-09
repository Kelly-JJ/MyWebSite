package com.gjj.website.threaddemo.bingfarongqi;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author :
 * @since 2020/3/19 1:53
 */
public class SynchronusQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        //容量为0,因此会导致该消息必须消费
        BlockingQueue<String> strs = new SynchronousQueue<>();


        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

//        strs.add("aaa");

        strs.put("aaa");
        System.out.println(strs.size());

    }
}
