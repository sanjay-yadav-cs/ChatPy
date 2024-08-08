package RealTime;


import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDateTime;
import java.util.Set;
import Helper.*;
import Repository.*;
import Models.*;
import DependencyInjector.DIResolver;

@ServerEndpoint("/chat")
public class ChatEndpoint {
    private UserService userService = DIResolver.resolve(UserService.class);
    private MessageService messageService = DIResolver.resolve(MessageService.class);
    private ChatRoomService chatRoomService = DIResolver.resolve(ChatRoomService.class);
    
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
            .create();
    private static Set<Session> chatEndpoints = new HashSet<Session>();

    @OnOpen
    public void OnOpen(Session session) {
        chatEndpoints.add(session);
    }

    @OnMessage
    public void OnMessage(Session session, String message) {
        for (final Session endpoint : chatEndpoints) {
            synchronized (endpoint) {
                try {
                    Message chat = gson.fromJson(message, Message.class);
                    chat.setDatetime(LocalDateTime.now());
                    messageService.createMessage(chat);
                    endpoint.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @OnClose
    public void OnClose(Session session) {
        chatEndpoints.remove(session);
    }
}
