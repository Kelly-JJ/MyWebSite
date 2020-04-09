package com.gjj.website.threaddemo;

import java.util.concurrent.TimeUnit;

/**
 * @author :
 * @since 2020/3/18 18:05
 */
public class Account {
    String name;
    double balance;

    public synchronized void set(String name ,double balance) {
        this.name = name ;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public synchronized double getBalance(String name){
        return this.balance;
    }


    public static void main(String[] args) {
        Account a = new Account();
        new Thread(()->a.set("张三",100.0)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance("zhangsan"));


        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance("zhangsan"));
    }
}
