package com.gjj.website.threaddemo.ticketSeller;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author :
 * @since 2020/3/19 0:31
 */
public class TicketSeller4 {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号:" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    //poll拿最首位数据
                    String poll = tickets.poll();
                    if (poll == null) break;
                    else System.out.println("销售了---" + poll);

                }

            }).start();

        }
    }
}
