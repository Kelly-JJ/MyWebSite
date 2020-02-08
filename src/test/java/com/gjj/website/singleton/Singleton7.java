package com.gjj.website.singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author :
 * @since 2020/1/16 15:34
 */
public class Singleton7 {
    private static final AtomicReference<Singleton7> instance = new AtomicReference<>();

    private Singleton7() {
        System.out.println("Singleton7  loaded");
    }

    public static final Singleton7 getInstance() {
        for (;;){
            Singleton7 s1 = instance.get();
            if (s1 != null) {
                return s1;
            }
            s1 = new Singleton7();
            if (instance.compareAndSet(null, s1)) {
                return s1;
            }
        }
    }
}
