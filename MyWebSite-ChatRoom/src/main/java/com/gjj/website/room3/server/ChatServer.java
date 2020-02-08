package com.gjj.website.room3.server;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用NIO来实现多人聊天室
 *
 * @author :
 * @since 2020/1/17 16:06
 */
public class ChatServer {
    private static final int DEFAULT_PORT = 8888;
    private final String QUIT = "quit";

    private static final int BUFFER = 1024;
    private ServerSocketChannel server;
    private Selector selector;
    //读Buffer缓冲流
    private ByteBuffer rBuffer = ByteBuffer.allocate(BUFFER);
    //写Buffer缓冲流
    private ByteBuffer wBuffer = ByteBuffer.allocate(BUFFER);
    //统一字节编码
    private Charset charset = Charset.forName("utf-8");
    private int port;

    public ChatServer() {
        this(DEFAULT_PORT);
    }

    public ChatServer(int port) {
        this.port = port;
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
            selector = Selector.open();

            //开启服务端的通道，但处于阻塞状态
            server = ServerSocketChannel.open();
            //设置serverSocketChannel为非阻塞状态
            server.configureBlocking(false);
            server.socket().bind(new InetSocketAddress(port));
            //将serverSocketChannel注册事件到Selector中,SelectionKey.事件
            server.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务器启动成功！监听端口【" + port + "】......");
            //阻塞性的，获取已经注册的通道个数，若没有注册的通道，则无返回值。
            while (true) {
                selector.select();
                //返回的是selector监听的所触发的所有事件
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey key : selectionKeys) {
                    //处理被触发的事件，通过函数来编写
                    handles(key);
                }
                //清空所有已经触发的事件
                selectionKeys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(selector);
        }
    }

    public void handles(SelectionKey key) throws IOException {
        //ACCECPT事件 - 与客户端建立了连接
        if (key.isValid() && key.isAcceptable()) {
            //从key中获取服务端channel，服务端accept客户端的连接，并将客户端channel注册事件到selector中去
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
//                client.register(selector, SelectionKey.OP_WRITE);
            System.out.println("客户端【" + client.socket().getPort() + "】已连接.....");
        } else if (key.isValid() && key.isReadable()) {
            //READ事件 - 客户端发送了消息事件
            SocketChannel client = (SocketChannel) key.channel();
            String fwdMsg = recieve(client);
            if (fwdMsg.isEmpty()) {
                System.out.println("??????");
                //客户端出现异常
                key.cancel();//selector不在监听该事件
                selector.wakeup();//刷新selector的监听事件的状态
            } else {
                System.out.println(getClientName(client) + ":" + fwdMsg);
                if (readyToQuit(fwdMsg)) {
                    key.cancel();//selector不在监听该事件
                    selector.wakeup();//刷新selector的监听事件的状态
                    System.out.println(getClientName(client) + "已断开连接.....");
                }
                //转发消息到其他客户端
                forwardMessage(client, fwdMsg);

            }
        }
    }

    public String recieve(SocketChannel client) throws IOException {
        //因为使用的都是同一个buffer，因此每次都需要clear操作，防止消息留存
        rBuffer.clear();
        while (client.read(rBuffer) > 0) {

        }
        rBuffer.flip();
        return String.valueOf(charset.decode(rBuffer));
    }


    public void forwardMessage(SocketChannel client, String fwdMsg) throws IOException {
        Set<SelectionKey> keys = selector.keys();
        for (SelectionKey key : keys) {

            //只有客户端才会被转发消息
            if (key.channel() instanceof ServerSocketChannel) {
                continue;
            }
            SocketChannel otherClient = (SocketChannel) key.channel();
            if (key.isValid() && (!otherClient.equals(client))) {

                //当前客户端未断开连接，并且当前客户端并非发送消息的客户端才会被转发
                if (key.isValid() && !otherClient.equals(client)) {
                    wBuffer.put(charset.encode(getClientName(client) + ":" + fwdMsg));
                    wBuffer.flip();
                    while (wBuffer.hasRemaining()) {
                        otherClient.write(wBuffer);
                    }
                } else {
                    break;
                }
            }
        }
    }


    private String getClientName(SocketChannel client) {
        return "客户端【" + client.socket().getPort() + "】";
    }

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.start();
    }

}
