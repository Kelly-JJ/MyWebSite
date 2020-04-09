package com.gjj.website.threaddemo.bingfarongqi;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author :
 * @since 2020/3/19 1:47
 */
public class TransferQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();


//        new Thread(() -> {
//            try {
//                System.out.println(strs.take());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
        //如果没有消费者时，将会阻塞
//        strs.transfer("aaa");
        strs.put("aaa");

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
