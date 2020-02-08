package com.gjj.website;

import com.gjj.website.singleton.Singleton1;
import com.gjj.website.singleton.Singleton2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SpringBootTest
class Singleton2Test {

    @Test
    void contextLoads() {
//        Singleton2 s1 = Singleton2.getInstance();
//        Singleton2 s2 = Singleton2.getInstance();
//        System.out.println(s1 == s2);
        try {
            Class.forName("com.gjj.website.singleton.Singleton2");
            Singleton2.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Test
    public void test2 () throws Exception {
        Class clazz = Singleton2.class;
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Singleton2 o1 = (Singleton2)constructor.newInstance();
        Singleton2 o2 = Singleton2.getInstance();
        System.out.println(o1 == o2);
    }
}
