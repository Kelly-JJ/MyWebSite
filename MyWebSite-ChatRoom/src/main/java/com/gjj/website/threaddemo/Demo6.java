package com.gjj.website.threaddemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :
 * @since 2020/3/18 18:42
 */
public class Demo6 {

   volatile int count = 0;

    void m() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }


    public static void main(String[] args) {
        Demo6 demo6 = new Demo6();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(demo6::m, "thread-" + i));
        }

        threads.forEach(Thread::start);

        threads.forEach(v -> {
            try {
                v.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(demo6.count);
    }
}
