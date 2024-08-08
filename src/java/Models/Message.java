
package Models;

import java.time.LocalDateTime;

public class Message {
    private int id;
    private int roomId;
    private int senderId;
    private int reciverId;
    private LocalDateTime datetime;
    private String message;
    
    private User sender;
    private User reciver;
   

    public Message(int id, int roomId, int senderid, int reciverId, LocalDateTime datetime, String message) {
        this.id = id;
        this.roomId = roomId;
        this.senderId = senderid;
        this.reciverId = reciverId;
        this.datetime = datetime;
        this.message = message;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getSenderid() {
        return senderId;
    }

    public void setSenderid(int senderid) {
        this.senderId = senderid;
    }

    public int getReciverId() {
        return reciverId;
    }

    public void setReciverId(int reciverId) {
        this.reciverId = reciverId;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReciver() {
        return reciver;
    }

    public void setReciver(User reciver) {
        this.reciver = reciver;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", roomId=" + roomId + ", senderId=" + senderId + ", reciverId=" + reciverId + ", datetime=" + datetime + ", message=" + message + ", sender=" + sender + ", reciver=" + reciver + '}';
    }
    
    
    
}
