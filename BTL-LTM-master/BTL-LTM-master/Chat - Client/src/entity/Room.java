package entity;

import java.io.Serializable;
import java.util.List;

public class Room implements Serializable {

    private int id;
    private String description;
    private List<UserRoom> listUserRoom;
    private List<Message> listMessage;
    private String type;

    public Room() {
    }

    public Room(int id, String description, List<UserRoom> listUserRoom, List<Message> listMessage) {
        this.id = id;
        this.description = description;
        this.listUserRoom = listUserRoom;
        this.listMessage = listMessage;
    }

    public Room(int id, String description, List<UserRoom> listUserRoom, List<Message> listMessage, String type) {
        this.id = id;
        this.description = description;
        this.listUserRoom = listUserRoom;
        this.listMessage = listMessage;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserRoom> getListUserRoom() {
        return listUserRoom;
    }

    public void setListUserRoom(List<UserRoom> listUserRoom) {
        this.listUserRoom = listUserRoom;
    }

    public List<Message> getListMessage() {
        return listMessage;
    }

    public void setListMessage(List<Message> listMessage) {
        this.listMessage = listMessage;
    }

    public Object[] toObjects() {
        return new Object[]{
            description, listUserRoom.size()
        };
    }

}
