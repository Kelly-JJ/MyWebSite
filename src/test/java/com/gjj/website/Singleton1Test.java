package com.gjj.website;

import com.gjj.website.singleton.Singleton1;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SpringBootTest
class Singleton1Test {

    @Test
    void contextLoads() {
        Singleton1 singleton1 = Singleton1.getInstance();
        Singleton1 singleton2 = Singleton1.getInstance();
        System.out.println(singleton1 == singleton2);
    }

    @Test
    public void test2() {
        for (int i = 0; i <= 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Singleton1.getInstance());
                }
            }).start();
        }
    }


    @Test
    public void test3() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Singleton1.class;
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Singleton1 o1 = (Singleton1)constructor.newInstance();
        Singleton1 o2 = Singleton1.getInstance();
        System.out.println(o1 == o2);
    }
}
