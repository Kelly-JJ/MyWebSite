package com.gjj.website;

import com.gjj.website.singleton.Singleton4;
import com.gjj.website.singleton.Singleton5;
import com.gjj.website.singleton.Singleton6;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Singleton6Test {

    @Test
    void test1() {
        Singleton6 s1 = Singleton6.getInstance();
        Singleton6 s2 = Singleton6.getInstance();
        System.out.println(s1 == s2);
    }

    @Test
    void test2() throws Exception {
      for (int i = 1 ;i<20;i++){
          new Thread(new Runnable() {
              @Override
              public void run() {
                  System.out.println(Singleton6.getInstance());
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
