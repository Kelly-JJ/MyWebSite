package com.gjj.website;

import com.gjj.website.singleton.Singleton2;
import com.gjj.website.singleton.Singleton3;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Constructor;

@SpringBootTest
class Singleton3Test {

    @Test
    void test1() {
        Singleton3 s1 = Singleton3.INSTANCE;
        Singleton3 s2 = Singleton3.INSTANCE;
        System.out.println(s1 == s2);
    }

    @Test
    void test2() throws Exception {
        Class clzz = Singleton3.class;
        Constructor c = clzz.getDeclaredConstructor();
        c.setAccessible(true);
        Singleton3 s = Singleton3.INSTANCE;
        Singleton3 o = (Singleton3)c.newInstance();
        System.out.println(s == o);
    }
}
