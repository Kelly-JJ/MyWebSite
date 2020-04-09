package com.gjj.website.threaddemo.threaddemo2;

import java.util.concurrent.TimeUnit;

/**
 * @author :
 * @since 2020/3/18 22:57
 */
public class ThreadLocalDemo {

    static ThreadLocal<Person> tl = new ThreadLocal<Person>();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                System.out.println(Thread.currentThread().getName()+" "+tl.get());
            }
        }).start();



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Person person = new Person();
                tl.set(person);
                System.out.println(Thread.currentThread().getName()+" "+tl.get());
                System.out.println(person.name);
            }
        }).start();
    }


    static class Person {
         String name = "张三";
    }

}


