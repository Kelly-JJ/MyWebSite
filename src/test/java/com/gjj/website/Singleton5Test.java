package com.gjj.website;

import com.gjj.website.singleton.Singleton4;
import com.gjj.website.singleton.Singleton5;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Singleton5Test {

    @Test
    void test1() {
        Singleton4 s1 = Singleton4.getInstance();
        Singleton4 s2 = Singleton4.getInstance();
        System.out.println(s1 == s2);
    }

    @Test
    void test2() throws Exception {
      for (int i = 1 ;i<20;i++){
          new Thread(new Runnable() {
              @Override
              public void run() {
                  System.out.println(Singleton5.getInstance());
              }
          }).start();
      }
      try {
          System.in.read();
      }catch (Exception e){
          e.printStackTrace();
      }

    }
}
