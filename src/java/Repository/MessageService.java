package Repository;
import Models.Message;
import java.util.List;

public interface MessageService {
    // Create a new message
    void createMessage(Message message);

    // Find a message by its ID
    Message findMessageById(int id);

    // Update an existing message
    void updateMessage(Message message);

    // Delete a message by its ID
    void deleteMessage(int id);

    // Get all messages
    List<Message> findAllMessages();

    // Find messages by room ID
    List<Message> findMessagesByRoomId(int roomId);

    // Find messages by sender ID
    List<Message> findMessagesBySenderId(int senderId);
}


