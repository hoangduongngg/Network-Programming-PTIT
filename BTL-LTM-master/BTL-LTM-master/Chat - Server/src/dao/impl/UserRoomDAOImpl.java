/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.DAO;
import static dao.DAO.jdbcConnection;
import dao.UserDAO;
import dao.UserRoomDAO;
import entity.UserRoom;
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
public class UserRoomDAOImpl extends DAO implements UserRoomDAO {

    UserDAO userDAO = new UserDAOImpl();

    
    
    
//    @Override
//    public UserRoom selectById(int id) {
//        String sql = "Select * from Userroom where id='" + id + "'";
//
//        try {
//            UserRoom userroom = null;
//            rs = statement.executeQuery(sql);
//            int i = 0;
//            while (rs.next()) {
//                int idUser = rs.getInt(2);
//                User user = userDAO.selectById(idUser);
//                userroom = new UserRoom(id, user);
//                i++;
//            }
//            return userroom;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    @Override
    public List<UserRoom> selectListUserRoomByRoomId(int roomId) {
        List<UserRoom> userRooms = new ArrayList<>();
        String sql = "Select * from userroom where RoomId = ?";
        try {
            super.connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, roomId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                UserRoom userRoom = new UserRoom();
                userRoom.setId(rs.getInt("ID"));
                userRoom.setUser(userDAO.selectById(rs.getInt("UserID")));
                System.out.println(userRoom.getUser().toString());
                userRooms.add(userRoom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return userRooms;
        }
    }

    @Override
    public boolean createUserRoom(int userId, int roomId) {
        String sql = "INSERT INTO USERROOM (RoomID, UserId) VALUES (?,?)";
        boolean flag = false;
        try {
            super.connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, roomId);
            statement.setInt(2, userId);
            flag = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return flag;
        }
    }

    @Override
    public boolean deleteById(int userRoomId) {
        String sql = "Delete from USERROOM where ID = ?";
        boolean flag = false;
        try {
            super.connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, userRoomId);
            flag = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return flag;
        }
    }

}
