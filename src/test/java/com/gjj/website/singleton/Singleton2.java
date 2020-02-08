package com.gjj.website.singleton;

/**
 * @author :
 * @since 2020/1/16 14:46
 */
public class Singleton2 {
    private static class SingletonHold {
        private static Singleton2 instance = new Singleton2();
    }

    private Singleton2() {
        System.out.println("Singleton2 load");
    }

    public static Singleton2 getInstance() {
        return SingletonHold.instance;
    }
}
