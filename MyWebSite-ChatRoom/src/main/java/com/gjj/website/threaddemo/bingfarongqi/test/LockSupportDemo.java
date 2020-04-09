package com.gjj.website.threaddemo.bingfarongqi.test;

import java.util.concurrent.locks.LockSupport;

/**
 * @author :
 * @since 2020/3/20 12:49
 */
public class LockSupportDemo {
    static Thread t1 = null,t2 = null;

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        t1 = new Thread(()->{
            for (char c : aI) {
                System.out.print(c);
                //接触阻塞
                LockSupport.unpark(t2);
                LockSupport.park();

            }
        },"t1");

        t2 = new Thread(()->{
            for (char c : aC) {
                //阻塞
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        },"t2");


        t1.start();
        t2.start();
    }
}
