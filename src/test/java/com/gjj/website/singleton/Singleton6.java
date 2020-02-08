package com.gjj.website.singleton;

/**
 * @author :
 * @since 2020/1/16 15:27
 */
public class Singleton6 {
    private static Singleton6 instance = null;

    private Singleton6() {
    }

    private static final ThreadLocal<Singleton6> threadLocal = new ThreadLocal<Singleton6>() {
        @Override
        protected Singleton6 initialValue() {
            return new Singleton6();
        }
    };

    public static Singleton6 getInstance() {
        return threadLocal.get();
    }
}
