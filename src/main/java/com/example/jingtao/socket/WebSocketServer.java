package com.example.jingtao.socket;


import com.example.jingtao.entrity.Room;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/hellosocket/{user}")
@Component
public class WebSocketServer {

    public static int onLineCount = 0;
    public static ConcurrentHashMap<String, WebSocketServer> webSocketServers = new ConcurrentHashMap<>();
    public Session session;
    public String user;

    @OnOpen
    public void OnOpen(Session session, @PathParam("user") String user) {
        this.session = session;
        this.user = user;
        if (webSocketServers.containsKey(user)) {
            System.out.println("该用户已经存在于服务器 user=" + user);

        } else {
            webSocketServers.put(user, this);
            onLineCount++;
            System.out.println("用户" + user + "添加到服务器 当前用户数为" + onLineCount);
            if (user.split("_")[1].equals("white")) {
                try {
                    sendInfo(user.split("_")[0] + "_black", "whiteIsIn");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @OnClose
    public void OnClose(Session session, @PathParam("user") String user) {
        this.session = session;
        this.user = user;
        if (webSocketServers.containsKey(user)) {
            webSocketServers.remove(user);
            onLineCount--;
            System.out.println("用户" + user + "已经退出 当前连接用户数为" + onLineCount);
            String s = user.split("_")[0];
            Room.roomMap.remove(s);
        }
    }

    @OnMessage
    public void OnMessage(Session session, String msg) {

        System.out.println("用户" + user + "发来了消息：" + msg);
        String[] split = msg.split(",");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        String[] s = user.split("_");
        String id = s[0];
        String color = s[1];
        if (color.equals("black")) {
            try {
                sendInfo(id + "_white", x + "," + y);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                sendInfo(id + "_black", x + "," + y);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("用户错误:" + this.user + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

//    public void sendMessage(String user, String message) throws IOException {
//
//        webSocketServers.get(user).session.getAsyncRemote().sendText(message);
//    }

    public void sendInfo(String user, String message) throws IOException {
        if (webSocketServers.containsKey(user)) {
            webSocketServers.get(user).sendMessage(message);
            System.out.println("服务器向用户" + user + "发送了消息：" + message);
        } else {
            System.out.println("用户" + user + "不在服务器内");
        }
    }


}
