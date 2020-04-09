package com.gjj.website.threaddemo.bingfarongqi.test;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.Executor;

/**
 * @author :
 * @since 2020/3/20 13:35
 */
public class PipedStreamDemo {

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();


        PipedInputStream input1 = new PipedInputStream();
        PipedInputStream input2 = new PipedInputStream();
        PipedOutputStream out1 = new PipedOutputStream();
        PipedOutputStream out2 = new PipedOutputStream();

    }



}
