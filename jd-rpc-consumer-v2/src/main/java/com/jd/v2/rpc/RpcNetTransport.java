package com.jd.v2.rpc;

import com.jd.bean.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcNetTransport {

    private String host;
    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public Object send(RpcRequest rpcRequest) {

        Socket socket = null;

        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        Object result = null;
        try {
            socket = new Socket(host, port);

            objectOutputStream =new ObjectOutputStream(socket.getOutputStream());//网络socket
            objectOutputStream.writeObject(rpcRequest); //序列化()
            objectOutputStream.flush();

            objectInputStream=new ObjectInputStream(socket.getInputStream());
            result=objectInputStream.readObject();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
