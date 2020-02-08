package com.gjj.website;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :
 * @since 2020/1/16 16:59
 */
public class Suanfa3 {
    //找出字符串中出现次数最多的字符
    @Test
    public void test() {
        String str = "aaabbbbb";
        char c = str.charAt(0);
        Integer max = 0;
        //统计每个字符串的次数
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i <= str.length()-1; i++) {
            c = str.charAt(i);
            Integer count ;
            if (!map.containsKey(str.charAt(i))) {
                count = 1;
            } else {
                count = map.get(str.charAt(i)) + 1;
            }
            map.put(str.charAt(i), count);
            if (count > max) {
                c = str.charAt(i);
                max = count;
            }
        }
        System.out.println(c+"次数最多："+max);
    }
}
