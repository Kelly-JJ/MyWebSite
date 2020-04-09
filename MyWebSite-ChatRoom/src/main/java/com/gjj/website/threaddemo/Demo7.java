package com.gjj.website.threaddemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 与demo6相比较
 *
 * @author :
 * @since 2020/3/18 18:50
 */
public class Demo7 {

    AtomicInteger count = new AtomicInteger(0);

    void m() {
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet();//相当于count++
        }
    }


    public static void main(String[] args) {
        Demo7 demo7 = new Demo7();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(demo7::m, "thread-" + i));
        }

        threads.forEach(Thread::start);

        threads.forEach(v -> {
            try {
                v.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(demo7.count);
    }
}
