package com.gjj.website.room4;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Set;

public class ChatServer {

    private static final int DEFAULT_PORT = 8888;
    private static final String QUIT = "quit";
    private static final int BUFFER = 1024;
    private ServerSocketChannel serverSocketChannel; //服务器端用于处理IO的通道
    private Selector selector;
    private ByteBuffer byteBufferReader = ByteBuffer.allocate(BUFFER); //用来读取消息
    private ByteBuffer byteBufferWriter = ByteBuffer.allocate(BUFFER); //用来转发消息时写入其他通道的缓冲区
    private Charset charset = Charset.forName("UTF-8"); //标准化编码解码
    private int port;

    public ChatServer(){
        this(DEFAULT_PORT);
    }
    public ChatServer(int port){
        this.port = port;
    }
    private void start(){
        try {
            serverSocketChannel = ServerSocketChannel.open(); //创建服务器套接字通道
            serverSocketChannel.configureBlocking(false); //设置为非阻塞式调用
            serverSocketChannel.socket().bind(new InetSocketAddress(port));

            selector = Selector.open(); //打开选择器
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("启动服务器，监听端口：" + port + "...");

            while (true) {
                selector.select();
                //selectionKeys包含了select()接收到的所有事件
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for(SelectionKey key : selectionKeys){
                    //处理被触发的事件
                    handles(key);
                }
                selectionKeys.clear(); //把集合清空
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close(selector);//启到既关闭selector又关闭通道的作用
        }
    }

    /**
     * 处理被触发的事件
     * @param key 每当通道被选择器注册时，都会创建一个选择键
     * @throws IOException
     */
    private void handles(SelectionKey key) throws IOException {
        // 触发 ACCEPT事件 --- 和客户端建立了连接
        if(key.isAcceptable()){
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            System.out.println(getClientName(client) + "已连接");
        }
        // 触发 READ事件 --- 客户端发送了消息给服务器端
        else if(key.isReadable()){
            SocketChannel client = (SocketChannel) key.channel();
            String fwdMsg = receive(client); //读取客户端消息
            if(fwdMsg.isEmpty()){ //客户端异常
                key.cancel(); //不再监视这个通道上的read事件
                selector.wakeup();
            }else {
                forwardMessage(client, fwdMsg); //转发客户端消息

                // 检查用户是否退出
                if(readyToQuit(fwdMsg)){
                    key.cancel();//解除监听
                    selector.wakeup();
                    System.out.println(getClientName(client) + "已断开");
                }
            }
        }
    }

    /**
     * 用于转发消息
     * @param client
     * @param fwdMsg
     * @throws IOException
     */
    private void forwardMessage(SocketChannel client, String fwdMsg) throws IOException {
         for(SelectionKey key : selector.keys()){
             Channel connectedClient = key.channel();
             if(connectedClient instanceof ServerSocketChannel) {
                 continue;
             }

             if(key.isValid() && !client.equals(connectedClient)) {
                 byteBufferWriter.clear();
                 byteBufferWriter.put(charset.encode((getClientName(client)) + ":" + fwdMsg));
                 byteBufferWriter.flip(); //写转读
                 while(byteBufferWriter.hasRemaining()){
                     ((SocketChannel)connectedClient).write(byteBufferWriter);
                 }
             }
         }
    }
    private String receive(SocketChannel client) throws IOException {
        byteBufferReader.clear();
        while(client.read(byteBufferReader) > 0);
        byteBufferReader.flip();
        return String.valueOf(charset.decode(byteBufferReader));

    }
    private String getClientName(SocketChannel client){
        return "客户端[" + client.socket().getPort() + "]";
    }
    private boolean readyToQuit(String msg){
        return QUIT.equals(msg);
    }
    private void close(Closeable closeable){
        if(closeable != null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.start();

    }
}

