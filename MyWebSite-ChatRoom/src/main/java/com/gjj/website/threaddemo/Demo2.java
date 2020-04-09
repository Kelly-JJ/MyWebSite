package com.gjj.website.threaddemo;

/**
 * 同步和非同步方法是否可以同时调用
 * @author :
 * @since 2020/3/18 17:55
 */
public class Demo2 {

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+"  m1 start ...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"  m1 end ...");
    }


    public void m2(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"m2.");
    }


    public static void main(String[] args) {
        Demo2 demo2 = new Demo2();
        new Thread(demo2::m1,"thread1").start();
        new Thread(demo2::m2,"thread2").start();

    }
}
