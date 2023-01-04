/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Message;
import entity.Request;
import entity.Room;
import entity.User;
import entity.UserRoom;
import flag.ActionFlags;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DUC
 */
public class UserController {

    private final ObjectOutputStream objectOutputStream;

    public List<Integer> listRoomOpened = new ArrayList<>();

    public UserController(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    private void send(Request request) {
        try {
            objectOutputStream.writeObject(request);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void login(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Request request = new Request(ActionFlags.LOGIN, user);
        send(request);
    }

    public void register(User user) {
        Request request = new Request(ActionFlags.REGISTER, user);
        send(request);
    }

    public void geListUser() {
        Request request = new Request(ActionFlags.GET_ALL_USER, null);
        send(request);
    }

    public void logout() {
        Request request = new Request(ActionFlags.LOGOUT, null);
        send(request);
    }

    public void sendMessage(Message message) {
        Request request = new Request(ActionFlags.SEND_MESSAGE, message);
        send(request);
    }

    public void getAllMessageInRoom(Room room) {
        Request request = new Request(ActionFlags.GET_ALL_MESSAGE, room);
        send(request);
    }

    public void getListRoom() {
        Request request = new Request(ActionFlags.GET_LIST_ROOM, null);
        send(request);
    }

    public void createRoom(Room room) {
        Request request = new Request(ActionFlags.CREATE_ROOM, room);
        send(request);
    }

    public void createOrJoinPrivateRoom(User user) {
        Request request = new Request(ActionFlags.CREATE_OR_JOIN_PRIVATE_ROOM, user);
        send(request);
    }

    public void addUserToRoom(UserRoom userRoom) {
        Request request = new Request(ActionFlags.ADD_USER_TO_ROOM, userRoom);
        send(request);
    }

    public void updateRoom(Room room){
        Request request = new Request(ActionFlags.UPDATE_ROOM, room);
        send(request);
    }
    
    public void leaveRoom(UserRoom userRoom){
        Request request = new Request(ActionFlags.LEAVE_ROOM, userRoom);
        send(request);
    }
}
