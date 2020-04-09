package com.gjj.website.threaddemo.bingfarongqi;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author :
 * @since 2020/3/19 1:29
 */
public class ArrayBlockQueueDemo {

    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);
        }
        //如果容量满了，则会是程序阻塞，
        strs.put("aaa");
        //若容量满了，则会抛异常
        strs.add("aaa");
        //offer将会返回是否添加成功的bool值
        strs.offer("aaa");
        strs.offer("aaa",1, TimeUnit.SECONDS);
        System.out.println(strs);

    }

}
