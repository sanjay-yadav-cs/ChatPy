package Models;

import java.time.*;
import java.util.List;

public class ChatRoom {

    private int id;
    private String roomName;
    private List<User> members;
    private boolean isPersonal;
    private String roomImage;
    
    private List<Message> messages;
    private LocalDateTime lastMeesageDateTime;
    private String lastMessage;

    public ChatRoom(int id, String roomName, List<User> members, boolean isPersonal, String roomImage) {
        this.id = id;
        this.roomName = roomName;
        this.members = members;
        this.isPersonal = isPersonal;
        this.roomImage = roomImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public boolean isIsPersonal() {
        return isPersonal;
    }

    public void setIsPersonal(boolean isPersonal) {
        this.isPersonal = isPersonal;
    }

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public LocalDateTime getLastMeesageDateTime() {
        return lastMeesageDateTime;
    }

    public void setLastMeesageDateTime(LocalDateTime lastMeesageDateTime) {
        this.lastMeesageDateTime = lastMeesageDateTime;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

}
