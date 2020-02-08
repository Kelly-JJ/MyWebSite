package com.gjj.website.singleton;

/**
 * @author :
 * @since 2020/1/16 15:16
 */
public class Singleton4 {
    private static Singleton4 instance = null;

    private Singleton4() {
    }


    public static Singleton4 getInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }
}
