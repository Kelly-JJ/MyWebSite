package com.gjj.website.room3.client;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * @author :
 * @since 2020/1/17 16:39
 */
public class ChatClient {
    private static final String DEFAULT_SERVER_HOST = "localhost";
    private static final int DEFAULT_SERVER_PORT = 8888;
    private final String QUIT = "quit";

    private String host;
    private int port;

    private SocketChannel client;
    private static final int BUFFER = 1024;
    //读Buffer缓冲流
    private ByteBuffer rBuffer = ByteBuffer.allocate(BUFFER);
    //写Buffer缓冲流
    private ByteBuffer wBuffer = ByteBuffer.allocate(BUFFER);
    private Selector selector;
    private Charset charset = Charset.forName("utf-8");

    public ChatClient() {
        this(DEFAULT_SERVER_HOST, DEFAULT_SERVER_PORT);
    }

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void start() {
        try {
            client = SocketChannel.open();
            client.configureBlocking(false);

            selector = Selector.open();
            client.register(selector, SelectionKey.OP_CONNECT);
            client.connect(new InetSocketAddress(host, port));
            while (true) {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                for (SelectionKey key : keys) {
                    handles(key);
                }
                keys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (
                ClosedSelectorException e) {
            //用户正常退出
        } finally {
            close(selector);
        }
    }

    private void handles(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        if (key.isConnectable()) {
            if (client.isConnectionPending()) {
                //连接是否就绪
                client.finishConnect();
                System.out.println("连接成功！");
                //处理用户输入的信息
                new Thread(new UserInputHandler(this)).start();
            } else {
                System.out.println("连接进行中！请等待!");
            }
        } else if (key.isReadable()) {
            //收到服务端发送的消息
            String msg = recieve(channel);
            if (msg.isEmpty()) {
                close(selector);
            } else {
                System.out.println(msg);
            }
        }
    }

    public void send(String msg) throws IOException {
        if (msg.isEmpty()) {
            return;
        } else {
            wBuffer.clear();
            wBuffer.put(charset.encode(msg));
            wBuffer.flip();
            while (wBuffer.hasRemaining()) {
                //确保消息写入完整
                client.write(wBuffer);
            }
            //检查用户是否准备退出
            if (readyToQuit(msg)) {
                close(selector);
                return;
            }
        }
    }


    public String recieve(SocketChannel client) throws IOException {
        rBuffer.clear();
        while (client.read(rBuffer) != -1) ;
        rBuffer.flip();
        return String.valueOf(charset.decode(rBuffer));
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

    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient();
        chatClient.start();
    }
}
