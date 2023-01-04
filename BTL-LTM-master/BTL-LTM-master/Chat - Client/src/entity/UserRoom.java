package entity;

import java.io.Serializable;

public class UserRoom implements Serializable {
    private int id;
    private User user;
    private int roomId;
    public UserRoom() {
    }

    public UserRoom(int id, User user) {
        this.id = id;
        this.user = user;
    }

    public UserRoom(int id, User user, int roomId) {
        this.id = id;
        this.user = user;
        this.roomId = roomId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
