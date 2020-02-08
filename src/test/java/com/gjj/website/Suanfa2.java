package com.gjj.website;

import org.junit.jupiter.api.Test;

/**
 * @author :
 * @since 2020/1/16 16:33
 */
public class Suanfa2 {
    @Test
    public void test() {
        //两个大数相乘
        String a = "12";
        String b = "12";
        System.out.println(Integer.valueOf(a) * Integer.valueOf(b));
        char[] large = null;
        char[] small = null;

        if (a.length() >= b.length()) {
            large = a.toCharArray();
            small = b.toCharArray();
        } else {
            large = b.toCharArray();
            small = a.toCharArray();
        }
        int[] num = new int[large.length + b.length()];
        for (int i = small.length-1; i >= 0; i--) {
            for (int j = large.length-1; j >= 0; j--) {
                int num1 = small[i] - '0';
                int num2 = large[j] - '0';
                num[large.length - 1 - j + small.length - 1 - i] += num1 * num2;
            }
        }
        for (int i = num.length-1; i >= 0; i--) {
            if (num[i] >= 10) {
                num[i + 1] += num[i] / 10;
                num[i] = num[i]%10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = num.length - 1; i >= 0; i--) {
            sb.append(num[i]);
        }
        String re = sb.toString();
        if (re.startsWith("0")) {
            re = re.substring(1);
        }
        System.out.println(re);

    }
}
