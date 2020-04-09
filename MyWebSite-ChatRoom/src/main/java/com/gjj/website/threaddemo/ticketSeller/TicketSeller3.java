package com.gjj.website.threaddemo.ticketSeller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author :
 * @since 2020/3/19 0:25
 */
public class TicketSeller3 {

    static List<String> tickets = new LinkedList<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号:" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            //将会导致卖重，超卖的现象
            new Thread(() -> {
                while (true) {
                    synchronized (tickets) {
                        if (tickets.size() <= 0) {
                            break;
                        }
//                        try {
//                            TimeUnit.MILLISECONDS.sleep(10);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
                        System.out.println("销售了--" + tickets.remove(0));
                    }
                }
            }).start();

        }
    }
}
