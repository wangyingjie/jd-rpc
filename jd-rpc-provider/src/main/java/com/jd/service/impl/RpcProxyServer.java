package com.jd.service.impl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcProxyServer {

    //ExecutorService executorService = Executors.newFixedThreadPool(10);
    ExecutorService executorService = Executors.newFixedThreadPool(10);


    public void publisher(int port, Object service) {

        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(port);

            while (true) {
                // 接收rpc请求 阻塞
                Socket socket = serverSocket.accept();

                // 处理rpc请求
                executorService.execute(new ProcessorHandler(socket, service));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
