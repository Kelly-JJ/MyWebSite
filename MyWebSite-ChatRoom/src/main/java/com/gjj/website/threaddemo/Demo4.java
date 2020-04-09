package com.gjj.website.threaddemo;

import java.util.concurrent.TimeUnit;

/**
 * @author :
 * @since 2020/3/18 18:13
 */
public class Demo4 {

    synchronized void m1() {
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
    }

    synchronized void m2() {
        System.out.println("m2 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Demo4 demo4 = new Demo4();

        new Thread(()->demo4.m1()).start();

    }
}
