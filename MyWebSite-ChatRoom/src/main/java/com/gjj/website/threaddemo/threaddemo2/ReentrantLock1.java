package com.gjj.website.threaddemo.threaddemo2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author :
 * @since 2020/3/18 20:21
 */
public class ReentrantLock1 {

    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();//sync(this)
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    void m2(){
        lock.lock();
        System.out.println("m2  ......");
        lock.unlock();
    }


    public static void main(String[] args) {
        ReentrantLock1 rl = new ReentrantLock1();
        new Thread(rl::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(rl::m2).start();
    }
}
