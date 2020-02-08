package com.gjj.website.chatroom.server;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author :
 * @since 2020/1/17 16:06
 */
public class ChatServer {
    private final int DEFAULT_PORT = 8888;
    private final String QUIT = "quit";
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private Map<Integer, Writer> connectedClients;





    public ChatServer() {
        executorService = Executors.newFixedThreadPool(10);
        connectedClients = new HashMap<>();
    }

    //连接服务器
    public synchronized void addClients(Socket socket) throws IOException {
        if (socket != null) {
            int port = socket.getPort();
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );
            connectedClients.put(port, bufferedWriter);
            System.out.println("客户端:【" + port + "】已连接到服务器");
        }
    }

    //下线用户
    public synchronized void removeClients(Socket socket) throws IOException {
        if (socket != null) {
            int port = socket.getPort();
            if (connectedClients.containsKey(port)) {
                connectedClients.get(port).close();
            }
            connectedClients.remove(port);
            System.out.println("客户端:【" + port + "】已断开服务器");
        }
    }

    //将用户发送的消息发送给其他用户
    public synchronized void forwardMsg(Socket socket, String msg) throws IOException {
        int port = socket.getPort();
        for (Integer id : connectedClients.keySet()) {
            if (!id.equals(port)) {
                Writer writer = connectedClients.get(id);
                writer.write(msg);
                writer.flush();
            }
        }
    }

    //检查是否断开连接
    public Boolean readyToQuit(String msg) {
        return QUIT.equals(msg);
    }

    public void close(Closeable closeable) {
            try {
                System.out.println("关闭服务器serverSocket");
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    public void start() {
        try {
            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("启动服务器，监听端口【" + DEFAULT_PORT + "】....");
            Socket socket;
            while (true) {
                //等待客户连接
                socket = serverSocket.accept();
                //创建多线程
                executorService.execute(new ChatHandler(this, socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(serverSocket);
        }
    }


    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();

        chatServer.start();
    }


}
