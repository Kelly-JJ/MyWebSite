package com.gjj.website.threaddemo.bingfarongqi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author :
 *
 * @since 2020/3/19 1:12
 */
public class SynchronizedListDemo {
    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();

        List<String> strings = Collections.synchronizedList(strs);
    }
}
