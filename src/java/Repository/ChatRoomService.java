package Repository;

import Models.*;
import java.util.List;

public interface ChatRoomService {
    // Create a new chat room
    void createChatRoom(ChatRoom chatRoom);

    // Find a chat room by its ID
    ChatRoom findChatRoomById(int id);

    // Update an existing chat room
    void updateChatRoom(ChatRoom chatRoom);

    // Delete a chat room by its ID
    void deleteChatRoom(int id);

    // Get all chat rooms
    List<ChatRoom> findAllChatRooms();

    // Find chat rooms by room name
    List<ChatRoom> findChatRoomsByRoomName(String roomName);
}


