package com.gjj.website.singleton;

/**
 * @author :
 * @since 2020/1/16 14:34
 */
public class Singleton1 {
    /**
     * 饿汉式 ，线程安全，但反射和序列化中不安全，若序列化需要安全，则在实现一个readResolve方法
     */
    private static Singleton1 instance = new Singleton1();

    private Singleton1() {

    }

    public static Singleton1 getInstance() {
        return instance;
    }

}
