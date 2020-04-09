package com.gjj.website.threaddemo.bingfarongqi;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author :
 * @since 2020/3/19 1:13
 */
public class ConcurrentQueueDemo {

    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 10; i++) {
            strs.offer("a" + i);
        }

        System.out.println(strs);
        System.out.println(strs.size());
        //拿出头位数据，并且删除该数据
        System.out.println(strs.poll());
        System.out.println(strs.size());
        //拿出头位数据，但并不删除
        System.out.println(strs.peek());
        System.out.println(strs.size());


    }
}
