package com.jd.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * <p><b>Desc:</b></p>
 * <p></p>
 *
 * @author wangyingjie5
 */
public class T08_SynchronusQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa"); //阻塞等待消费者消费
        strs.put("bbb");
        // strs.add("aaa");
        System.out.println(strs.size());
    }

}
