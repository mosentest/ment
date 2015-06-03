package hemu.ment.core.chat;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by muu on 2015/6/3.
 */
@ServerEndpoint(value = "/chat")
public class ChatEndPoint {

    @OnOpen
    public void open(final Session session) {
        System.out.println(session.getId() + " has connected.");
        session.getQueryString();
    }

    @OnMessage
    public void onMessage(final Session session, String message) {
        try {
           for (Session s : session.getOpenSessions()) {
               if (s.isOpen()) {
                   s.getBasicRemote().sendText(message);
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
