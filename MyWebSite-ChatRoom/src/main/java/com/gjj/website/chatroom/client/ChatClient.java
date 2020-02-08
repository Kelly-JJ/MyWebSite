package com.gjj.website.chatroom.client;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author :
 * @since 2020/1/17 16:39
 */
public class ChatClient {
    private final String DEFAULT_SERVER_HOST = "localhost";
    private final int DEFAULT_SERVER_PORT = 8888;
    private final String QUIT = "quit";

    private Socket socket;

    private BufferedReader reader;
    private BufferedWriter writer;


    //发送消息给服务器
    public void send(String msg) throws IOException {
        //判断输出流是否关闭
        if (!socket.isOutputShutdown()) {
            writer.write(msg + "\n");
            writer.flush();
        }
    }


    //从服务器接收消息
    public String receive() throws IOException {
        String msg = null;
        if (!socket.isInputShutdown()) {
            msg = reader.readLine();
        }
        return msg;
    }

    public Boolean readyToQuit(String msg) {
        return QUIT.equals(msg);
    }

    public void close(Closeable closeable) {
         System.out.println("关闭socket");
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void start() {
        try {
            //读取服务器转发的消息

            //创建socket
            socket = new Socket(DEFAULT_SERVER_HOST, DEFAULT_SERVER_PORT);


            //创建IO流
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


            System.out.println("客户端启动成功!");
            //发送消息
            new Thread(new UserInputHandler(this)).start();
            String msg;
            while ((msg = receive()) != null) {
                System.out.println(msg);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(socket);
            close(reader);
            close(writer);
        }


    }


    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient();
        chatClient.start();
    }

}
