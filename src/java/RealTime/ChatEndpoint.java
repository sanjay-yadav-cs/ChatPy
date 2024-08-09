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
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat")
public class ChatEndpoint {

    private UserService userService = DIResolver.resolve(UserService.class);
    private MessageService messageService = DIResolver.resolve(MessageService.class);
    private ChatRoomService chatRoomService = DIResolver.resolve(ChatRoomService.class);

    private static Map<Integer, List<Session>> IntrestedRoom = new ConcurrentHashMap<>();
    private static Map<Integer, List<Session>> IntrestedUser = new ConcurrentHashMap<>();

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
            .create();
    private static Set<Session> chatEndpoints = new HashSet<Session>();

    @OnOpen
    public void OnOpen(Session session) throws Exception {
        SocketEvent se = new SocketEvent("Connected", null);
        String message = gson.toJson(se);
        session.getBasicRemote().sendText(message);
        chatEndpoints.add(session);
    }

    @OnMessage
    public void OnMessage(Session session, String message) {
        handleEventData(session, message);
    }

    @OnClose
    public void OnClose(Session session) {
        chatEndpoints.remove(session);
    }

    public void RegisterIntrestedRoom(int RoomId, Session session) {
        List<Session> ss = IntrestedRoom.get(RoomId);
        if (ss == null) {
            ss = new ArrayList<>();
        }
        ss.add(session);
        IntrestedRoom.put(RoomId,ss);
    }

    public void handleEventData(Session session, String message) {
        SocketEvent event = gson.fromJson(message, SocketEvent.class);
        switch (event.getEventName()) {
            case "RegisterIntrestedRoom":
                RegisterIntrestedRoom IRoom = gson.fromJson(event.getData(), RegisterIntrestedRoom.class);

                RegisterIntrestedRoom(IRoom.getId(), session);
                System.out.println("DataClass1 key1: " + IRoom.getId());
                break;
            case "Message":
                Message chat = gson.fromJson(event.getData(), Message.class);
                chat.setDatetime(LocalDateTime.now());
                LocalDateTime y = LocalDateTime.now();
                messageService.createMessage(chat);
                List<Session> recipientSessions = IntrestedRoom.get(chat.getRoomId());

                for (final Session recipientSession : recipientSessions) {
                    synchronized (recipientSession) {
                        try {
                            if (recipientSession != null && recipientSession.isOpen()) {
                                recipientSession.getBasicRemote().sendText(message);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            default:
                System.out.println("Unknown event type: " + event.getEventName());
        }
    }
}

//import javax.websocket.OnClose;
//import javax.websocket.OnError;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.util.Map;
//
//@ServerEndpoint("/chat/{userId}/{sessionId}")
//public class ChatEndpoint {
//
//    @OnOpen
//    public void onOpen(Session session, @PathParam("userId") String userId, @PathParam("sessionId") String sessionId) {
//        System.out.println("Connected with userId: " + userId + ", sessionId: " + sessionId);
//        try {
//            session.getBasicRemote().sendText("Welcome to the server, user " + userId);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @OnMessage
//    public void onMessage(String message, Session session) {
//        System.out.println("Received: " + message);
//        try {
//            // Echo the message back to the client
//            session.getBasicRemote().sendText("Echo: " + message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @OnClose
//    public void onClose(Session session) {
//        System.out.println("Connection closed");
//    }
//
//    @OnError
//    public void onError(Session session, Throwable throwable) {
//        throwable.printStackTrace();
//    }
//}
