 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Message;
import Repository.MessageService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sanja
 */
public class MessageServiceImplementation implements MessageService {
    private static List<Message> messages = new ArrayList<>();
    @Override
    public void createMessage(Message message) {
        messages.add(message);
    }

    @Override
    public Message findMessageById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateMessage(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteMessage(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Message> findAllMessages() {
         return this.messages; 
    }

   @Override
    public List<Message> findMessagesByRoomId(int roomId) {
        List<Message> filteredMessaages = new ArrayList<>();
        for (Message message : messages) {
            if (message.getRoomId() == roomId) {
                filteredMessaages.add(message);
            }
        }
        return filteredMessaages;
    }

    @Override
    public List<Message> findMessagesBySenderId(int senderId) {
        return this.messages;
    }
    
}
