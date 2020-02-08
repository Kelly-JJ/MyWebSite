package com.gjj.website.singleton;

/**
 * @author :
 * @since 2020/1/16 15:16
 */
public class Singleton5 {
    private static volatile Singleton5 instance = null;

    private Singleton5() {
    }


    public static Singleton5 getInstance() {
        if(instance == null){
            synchronized (Singleton5.class) {
                if (instance == null) {
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}
