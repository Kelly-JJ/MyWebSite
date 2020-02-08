package com.gjj.website;

import com.gjj.website.singleton.Singleton6;
import com.gjj.website.singleton.Singleton7;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Singleton7Test {

    @Test
    void test1() {
        Singleton7 s1 = Singleton7.getInstance();
        Singleton7 s2 = Singleton7.getInstance();
        System.out.println(s1 == s2);
    }

    @Test
    void test2() throws Exception {
      for (int i = 1 ;i<40;i++){
          new Thread(new Runnable() {
              @Override
              public void run() {
                  System.out.println(Singleton7.getInstance());
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
