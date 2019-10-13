package com.naived.sparkpm25b.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/pm/websocket")
@Component
public class PMInfoWebsocket {

    private static CopyOnWriteArraySet<PMInfoWebsocket> websockets = new CopyOnWriteArraySet<>();

    private Session session;

    @OnOpen
    public void OnOpen(Session session){
        this.session = session;
        websockets.add(this);
    }

    @OnClose
    public void OnClose(Session session){
        websockets.remove(this);
    }

    private void send(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static void sendAll(String message){
        System.out.println(websockets.size());
        Arrays.asList(websockets.toArray()).forEach(item -> {
            PMInfoWebsocket websocket = (PMInfoWebsocket) item;
            try {
                websocket.send(message);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
    }

}
