package com.gjj.website;

import org.junit.jupiter.api.Test;

/**
 * @author :
 * @since 2020/1/16 15:51
 */
public class Suanfa1 {

    @Test
    public void test1() {

        //两个大数相加
        String a = "666";
        String b = "33";
        System.out.println(Integer.valueOf(a) + Integer.valueOf(b));
        char[] large = null;
        char[] small = null;
        if (a.length() >= b.length()) {
            large = a.toCharArray();//大
            small = b.toCharArray();//小
        } else {
            large = b.toCharArray();
            small = a.toCharArray();
        }
        int[] sums = new int[large.length + 1];
        //sums[] = [0,0,0,0]

        for (int i = 0; i < large.length; i++) {
            sums[i] = large[large.length - i - 1] - '0';
        }
        for (int i = 0; i < small.length; i++) {
            sums[i] += small[small.length - i - 1] - '0';
        }
        for (int sum : sums) {
            System.out.print(sum);
        }
        for (int i = 0; i < sums.length; i++) {
            if (sums[i] >= 10) {
                sums[i] = sums[i] % 10;
                sums[i + 1] += 1;
            }
        }
        System.out.println();
//        for (int sum : sums) {
//            System.out.print(sum);
//        }
        StringBuilder sb = new StringBuilder();
        for (int i = sums.length - 1; i >= 0; i--) {
            sb.append(sums[i]);
        }
        String s = sb.toString();
        if (s.startsWith("0")) {
            s = s.substring(1);
        }
        System.out.println(s);

    }
}
