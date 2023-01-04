/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.DAO;
import static dao.DAO.jdbcConnection;
import dao.MessageDAO;
import dao.RoomDAO;
import dao.UserRoomDAO;
import entity.Room;
import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DUC
 */
public class RoomDAOImpl extends DAO implements RoomDAO {

    UserRoomDAO userRoomDAO = new UserRoomDAOImpl();
    MessageDAO messageDAO = new MessageDAOImpl();

    @Override
    public List<Room> getListRoomByUserId(User user) {
        List<Room> listRoom = new ArrayList<>();
        String sql = "SELECT room.ID, room.Description, room.Type "
                + "FROM room, userroom, user "
                + "WHERE user.ID = ? "
                + "AND user.ID = userroom.UserID "
                + "AND userroom.RoomID = room.ID;";

        try {
            super.connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("ID"));
                room.setDescription(rs.getString("Description"));
                room.setType(rs.getString("Type"));
                room.setListMessage(messageDAO.selectByRoomId(room.getId()));
                room.setListUserRoom(userRoomDAO.selectListUserRoomByRoomId(room.getId()));
                listRoom.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return listRoom;
        }
    }

    @Override
    public Room createRoomByUsers(Room room, List<User> listUser) {
        String sql = "INSERT INTO Room (Description,Type) VALUES (?,?)";
        String sql2 = "SELECT MAX(ID) FROM ROOM";
        boolean flag = false;
        try {
            super.connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, room.getDescription());
            statement.setString(2, room.getType());
            flag = statement.executeUpdate() > 0;
            if (flag) {
                Statement statement1 = jdbcConnection.createStatement();
                ResultSet resultSet = statement1.executeQuery(sql2);
                if (resultSet.next()) {
                    room.setId(resultSet.getInt("MAX(ID)"));
                    for (User user : listUser) {
                        flag = userRoomDAO.createUserRoom(user.getId(), room.getId());
                        if (!flag) {
                            return null;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return room;
        }
    }

    @Override
    public Room getRoomById(int roomId) {
        String sql = "SELECT * from room where ID = ?";
        Room room = null;
        try {
            super.connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, roomId);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                room = new Room();
                room.setId(rs.getInt("ID"));
                room.setDescription(rs.getString("Description"));
                room.setType(rs.getString("Type"));
                room.setListMessage(messageDAO.selectByRoomId(room.getId()));
                room.setListUserRoom(userRoomDAO.selectListUserRoomByRoomId(room.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return room;
        }
        
    }

    @Override
    public boolean updateRoom(Room room) {
        String sql = "UPDATE Room set "
                + "Description = ?,"
                + "Type = ?"
                + "Where ID = ?";
        boolean flag = false;
        try {
            super.connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, room.getDescription());
            statement.setString(2, room.getType());
            statement.setInt(3, room.getId());
            flag = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return flag;
        }
    }

}
