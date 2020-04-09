package com.gjj.website.threaddemo;

import java.util.concurrent.TimeUnit;

/**
 * @author :
 * @since 2020/3/18 18:31
 */
public class Demo5 {
//volatile 作用：
    volatile boolean running = true;

    void m() {
        System.out.println("m  start ");
        while (running) {

        }
        System.out.println("m end !");
    }

    public static void main(String[] args) {
        Demo5 t = new Demo5();
        new Thread(t::m,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running=false;



    }
}
