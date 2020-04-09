package com.gjj.website.threaddemo;

/**
 * @author :
 * @since 2020/3/18 18:18
 */
public class T {
    synchronized void m() {
        System.out.println("m start .....");
    }

    public static void main(String[] args) {
        new TT().m();
    }
}
class TT extends T {
    @Override
    synchronized void m() {
        System.out.println("clild m start ");
        super.m();
        System.out.println("clild m end ");
    }
}
