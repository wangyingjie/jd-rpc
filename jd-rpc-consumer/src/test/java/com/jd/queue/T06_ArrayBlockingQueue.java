package com.jd.queue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * <p><b>Desc:</b></p>
 * <p></p>
 *
 * @author wangyingjie5
 */
public class T06_ArrayBlockingQueue {

    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);
        }

        strs.put("aaa"); //满了就会等待，程序阻塞
        //strs.add("aaa");
        //strs.offer("aaa");
        strs.offer("aaa", 10, TimeUnit.SECONDS);

        System.out.println(strs);
    }
}

