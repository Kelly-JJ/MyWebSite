package com.gjj.website.chatroom.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author :
 * @since 2020/1/17 16:20
 */
public class ChatHandler implements Runnable {
    //服务器
    private ChatServer server;
    //客户端的对象
    private Socket socket;

    public ChatHandler(ChatServer chatServer, Socket socket) {
        this.server = chatServer;
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            server.addClients(socket);
            //读取用户发来的信息
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            String msg;
            while ((msg = reader.readLine()) != null) {
                if (server.readyToQuit(msg)) {
                    break;
                }
                String fwdMsg = "客戶端【" + socket.getPort() + "】：" + msg+"\n";
                System.out.println(fwdMsg);
                server.forwardMsg(socket, fwdMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                server.removeClients(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
