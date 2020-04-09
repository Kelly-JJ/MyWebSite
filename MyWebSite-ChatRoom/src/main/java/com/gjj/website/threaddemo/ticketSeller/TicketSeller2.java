package com.gjj.website.threaddemo.ticketSeller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * @author :
 * @since 2020/3/19 0:18
 */
public class TicketSeller2 {

    static Vector<String> tickets = new Vector<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号:" + i);

        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {

            new Thread(() -> {
                //由于判断和操作分离，也会导致超卖的现象
                while (tickets.size() > 0) {//size是原子性
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println("销售了--" + tickets.remove(0));//remove是原子性
                }
            }).start();
        }
    }
}
