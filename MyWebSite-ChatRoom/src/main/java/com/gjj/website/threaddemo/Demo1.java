package com.gjj.website.threaddemo;

/**
 * @author :
 * @since 2020/3/18 17:20
 */
public class Demo1 implements  Runnable{

    private int count = 10;
    private Object o = new Object();

    @Override
    public void run(){
        synchronized (o){
            count--;
            System.out.println(Thread.currentThread().getName()+ " coun = "+count );

        }
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        long start = System.currentTimeMillis();
        for (int i = 0; i<10 ;i++){
            new Thread(demo1).start();
        }
       long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
