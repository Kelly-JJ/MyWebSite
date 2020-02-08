package com.gjj.website;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author :
 * @since 2020/2/1 16:35
 */
public class Suanfa5 {
    /**
     * 设有ｎ个人依围成一圈，从第１个人开始报数，数到第ｍ个人出列，
     * 然后从出列的下一个人开始报数，数到第ｍ个人又出列，…，
     * 如此反复到所有的人全部出列为止。设ｎ个人的编号分别为1，2，…，n，打印出出列的顺序；
     */

    private static boolean same(int[] p, int l, int n) {
        for (int i = 0; i < l; i++) {
            if (p[i] == n) {
                return true;
            }
        }
        return false;
    }

    public static void play1(int playerNum, int step) {
        int[] p = new int[playerNum];
        int counter = 1;
        while (true) {
            if (counter > playerNum * step) {
                break;
            }
            for (int i = 1; i < playerNum + 1; i++) {
                while (true) {
                    if (same(p, playerNum, i) == false) break;
                    else i = i + 1;
                }
                if (i > playerNum) break;
                if (counter % step == 0) {
                    System.out.print(i + " ");
                    p[counter / step - 1] = i;
                }
                counter += 1;
            }
        }
        System.out.println();
    }

    @Test
    public static void main(String[] args) {
//        System.out.println("3 6 9 12 4 8 1 7 2 11 5 10 ");

        long start = System.currentTimeMillis();
        play1(100, 201);
        System.out.println(System.currentTimeMillis() - start + "------");
        start = System.currentTimeMillis();
        play2(100, 201);
        System.out.println(System.currentTimeMillis() - start+ "------");
    }

    public static void play2(int playerNum, int step) {
        List<Integer> list = new ArrayList<>(playerNum);

        for (int i = 1; i <= playerNum; i++) {
            list.add(i);
        }
        System.out.println();
        // playerNum = 12,step = 3
        //   1 2 3 4 5 6 7 8 9 10 11 12
        //>> 1 2   4 5 6 7 8 9 10 11 12       3
        //>> 1 2   4 5   7 8 9 10 11 12       6
        //>> 1 2   4 5   7 8   10 11 12       9
        //>> 1 2   4 5   7 8   10 11          12
        //>> 1 2     5   7 8   10 11          4
        //>> 1 2     5   7     10 11          8
        //>>   2     5   7     10 11          1
        //>>   2     5         10 11          7
        //>>         5         10 11          2
        //>>         5         10             11
        //>>                   10             5
        //>>                                  10
        int count = 1;
        int index = 0;
        int num = 0;
        while (true) {
            if (num != playerNum) {
                for (int i = index; i < playerNum; i++) {
                    index = i + 1;
                    if (list.get(i) != -1) {
                        if (count % step == 0) {
                            if (index >= list.size()) {
                                index = 0;
                            }
                            System.out.print(list.get(i) + " ");
                            num++;
                            list.set(i, -1);
                            count = 1;
                            break;
                        } else {
                            if (index >= list.size()) {
                                index = 0;
                            }
                            count++;
                        }
                    } else {
                        if (index >= list.size()) {
                            index = 0;
                        }
                    }
                }
            } else {
                System.out.println();
                break;
            }
        }

    }
}
