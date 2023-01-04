/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MessageDAO;
import dao.RoomDAO;
import dao.UserDAO;
import dao.UserRoomDAO;
import dao.impl.MessageDAOImpl;
import dao.impl.RoomDAOImpl;
import dao.impl.UserDAOImpl;
import dao.impl.UserRoomDAOImpl;
import entity.Message;
import entity.Response;
import entity.Room;
import entity.User;
import entity.UserRoom;
import flag.ActionFlags;
import flag.ResultFlags;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DUC
 */
public class ServerWorker {

    public ObjectOutputStream objectOutputStream;
    public ObjectInputStream objectInputStream;
    public UserDAO userDAO;
    public RoomDAO roomDAO;
    public MessageDAO messageDAO;
    public UserRoomDAO userRoomDAO;
    public Socket socket;
    public User userc;

    public ServerWorker(Socket socket) throws SQLException, IOException {
        this.socket = socket;
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        this.userDAO = new UserDAOImpl();
        this.roomDAO = new RoomDAOImpl();
        this.messageDAO = new MessageDAOImpl();
        this.userRoomDAO = new UserRoomDAOImpl();
        this.userc = new User();
    }

    public void send(Response response) {
        try {
            objectOutputStream.writeObject(response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void logout() {
        userc.setActive("offline");
        userDAO.update(userc);
    }

    public void checkLogin(String username, String password, String actionType) {
        User user = userDAO.checkLogin(username, password);
        if (user != null) {
            user.setActive("online");
            userDAO.update(user);
            this.userc = user;
            Response response = new Response(actionType, ResultFlags.OK, "OK", user);
            send(response);
        } else {
            Response response = new Response(actionType, ResultFlags.ERROR, "Sai thông tin đăng nhập", null);
            send(response);
        }
    }

    public List<Room> getListRoomByUser() {
        List<Room> listRoom = roomDAO.getListRoomByUserId(userc);
        if (listRoom != null) {
            Response response = new Response(ActionFlags.GET_LIST_ROOM, ResultFlags.OK, "OK", listRoom);
            send(response);
        }
        return listRoom;
    }

    public Room getRoom(int roomId) {
        return roomDAO.getRoomById(roomId);
    }

    public void sendListUserRoom(Room room) {
        Response response = new Response(ActionFlags.UPDATE_USERS_ROOM, ResultFlags.OK, "OK", room);
        send(response);
    }

    public void sendListUserHome() {
        List<User> listUser = userDAO.selectAll();
        if (listUser != null) {
            Response response = new Response(ActionFlags.GET_ALL_USER, ResultFlags.OK, "", listUser);
            send(response);
        }
    }

    public Room updateRoom(Room room) {
        if (roomDAO.updateRoom(room) != false) {
            Room roomUpdated = roomDAO.getRoomById(room.getId());
            Response response = new Response(ActionFlags.UPDATE_ROOM, ResultFlags.OK, "", roomUpdated);
            send(response);
            return roomUpdated;
        } else {
            return null;
        }
    }

    public void createRoomByUser(Room room, String actionType) {
        List<User> listUser = new ArrayList<>();
        listUser.add(userc);
        if (roomDAO.createRoomByUsers(room, listUser) != null) {
            getListRoomByUser();
        }
    }

    private boolean checkExistsUserRoom(User user, Room room) {
        if (room.getListUserRoom().stream().anyMatch(userRoom -> (user.getId() == userRoom.getUser().getId()))) {
            return true;
        }
        return false;
    }

    public Room addUserToRoom(UserRoom userRoom) {
        Room result;
        Room room = getRoom(userRoom.getRoomId());
        if (checkExistsUserRoom(userRoom.getUser(), room)) {
            Response response = new Response(ActionFlags.ADD_USER_TO_ROOM, ResultFlags.ERROR, "User đã có trong Room", null);
            send(response);
            return null;
        }
        if (room.getType().equals("private")) { // tao phong moi
            List<User> listUser = new ArrayList<>();
            listUser.add(userRoom.getUser());
            room.getListUserRoom().forEach(userRoomm -> {
                listUser.add(userRoomm.getUser());
            });
            Room newRoom = room;
            newRoom.setType("public");
            newRoom.setDescription("");
            newRoom = roomDAO.createRoomByUsers(newRoom, listUser);
            openRoomChat(result = getRoom(newRoom.getId()), ActionFlags.OPEN_ROOM_CHAT);
        } else {
            userRoomDAO.createUserRoom(userRoom.getUser().getId(), userRoom.getRoomId());
            sendListUserRoom(result = getRoom(room.getId()));
        }
        return result;
    }

    public Room LeaveRoom(UserRoom userRoom, String actionType) {
        if (userRoomDAO.deleteById(userRoom.getId())) {
            Room room = getRoom(userRoom.getRoomId());
            getListRoomByUser();
            return room;
        } else {
            return null;
        }

    }

    public void checkRegister(User user, String actionType) {
        if (!userDAO.checkExistsUser(user.getUsername())) {
            userDAO.insert(user);
            Response response = new Response(actionType, ResultFlags.OK, "OK", null);
            send(response);
        } else {
            Response response = new Response(actionType, ResultFlags.ERROR, "Tài khoản đã tồn tại", null);
            send(response);
        }
    }

    public void openRoomChat(Room room, String actionType) {
        Response response = new Response(actionType, ResultFlags.OK, "OK", room);
        send(response);
    }

    public void sendMessage(Message message, String actionType) {
        Response response = new Response(actionType, ResultFlags.OK, "OK", message);
        send(response);
    }

    public Room checkExistsPrivateRoom(User user) {
        List<Room> listRoom = roomDAO.getListRoomByUserId(userc);
        if (listRoom != null) {
            for (Room room : listRoom) {
                List<UserRoom> lur = room.getListUserRoom();
                if (lur.size() == 2 && (lur.get(0).getUser().getId() == user.getId() || lur.get(1).getUser().getId() == user.getId())) {
                    // da ton tai -> send
                    System.out.println("Da ton tai");
                    return room;
                }
            }
            // chua ton tai -> create
            List<User> listUser = new ArrayList<>();
            listUser.add(userc);
            listUser.add(user);
            Room room = new Room();
            room.setDescription("Room chat cua: " + user.getDisplayName() + " va " + userc.getDisplayName());
            room.setType("private");
            room = roomDAO.createRoomByUsers(room, listUser);
            return room;
        }
        return null;
    }

    public Message createMessage(Message message) {
        return messageDAO.insertMessage(message, userc.getId(), message.getRoomId());
    }

}
