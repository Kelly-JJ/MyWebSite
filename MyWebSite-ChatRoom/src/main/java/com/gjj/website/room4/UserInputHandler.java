package com.gjj.website.room4;

import com.gjj.website.room4.ChatClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author :
 * @since 2020/1/17 16:54
 */
public class UserInputHandler implements Runnable {

    private ChatClient chatClient;

    public UserInputHandler(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public void run() {
        try {
            //等待用户输入消息
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while (true) {
                input = reader.readLine();
//                if (input != null ){
                chatClient.send(input);
//                }
                if (chatClient.readyToQuit(input)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
