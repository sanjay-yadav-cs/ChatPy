/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DependencyInjector.DIResolver;
import Models.ChatRoom;
import Models.Message;
import Models.User;
import Repository.ChatRoomService;
import Repository.MessageService;
import Repository.UserService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Sanja
 */
public class ChatRoomServiceImplementation implements ChatRoomService {
    private MessageService messageService = DIResolver.resolve(MessageService.class);
    private static List<ChatRoom> chatrooms = new ArrayList<>();
    
    @Override
    public void createChatRoom(ChatRoom chatRoom) {
        chatrooms.add(chatRoom);
    }

    @Override
    public ChatRoom findChatRoomById(int id) {
        return this.chatrooms.get(0);
    }

    @Override
    public void updateChatRoom(ChatRoom chatRoom) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteChatRoom(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChatRoom> findAllChatRooms() {
        return fillMessages(chatrooms);
    }

    @Override
    public List<ChatRoom> findChatRoomsByRoomName(String roomName) {
        return this.chatrooms;
    }
    
    @SuppressWarnings("empty-statement")
    private List<ChatRoom> fillMessages(List<ChatRoom> chatRooms) {
        List<ChatRoom> filtered = new ArrayList<ChatRoom>();
        for(ChatRoom chatRoom: chatRooms){
            List<Message> m = messageService.findMessagesByRoomId(chatRoom.getId());
            if(!m.isEmpty()){
                chatRoom.setMessages(m);
                Message lm = m.get(m.size()-1);
                chatRoom.setLastMeesageDateTime(lm.getDatetime());
                chatRoom.setLastMessage(lm.getMessage());
                filtered.add(chatRoom);
            }
        }
        
         Collections.sort(filtered, new Comparator<ChatRoom>() {
            @Override
            public int compare(ChatRoom o1, ChatRoom o2) {
                // Compare dates in descending order
                return o2.getLastMeesageDateTime().compareTo(o1.getLastMeesageDateTime());
            }
        });
        
         return filtered;
    }
    
}
