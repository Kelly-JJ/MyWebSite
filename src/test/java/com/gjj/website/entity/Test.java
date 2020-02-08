package com.gjj.website.entity;

import java.util.Date;

public class Test extends Date {
    public static void main(String[] args) {
        int a = 0;
        System.out.println("函数前：" + a);
        num(a);
        System.out.println("函数后:" + a);
    }


    public static void num(int a) {
        System.out.println("传参：" + a);
        a = 10;
        System.out.println("传参后修改值：" + a);
    }

}