package com.jd.queue;

import java.util.ArrayList;
import java.util.concurrent.LinkedTransferQueue;

/**
 * <p><b>Desc:</b></p>
 * <p></p>
 *
 * @author wangyingjie5
 */
public class T09_TransferQueue {

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> transferQueue = new LinkedTransferQueue<>(new ArrayList<>(1));

        // 异步线程
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println("thread--->" + transferQueue.take());
                }
                System.out.println("thread--->" + transferQueue.take());

                // System.out.println("thread--->" + transferQueue.take());
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        for (int i = 0; i < 5; i++) {
            //transferQueue.add("transfer" + i);
            transferQueue.put("xxxx" + i);
        }

        transferQueue.transfer("yyyyy");

        System.out.println(transferQueue);

    }
}
