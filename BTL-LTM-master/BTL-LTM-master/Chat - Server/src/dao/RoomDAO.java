/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Room;
import entity.User;
import java.util.List;

/**
 *
 * @author DUC
 */
public interface RoomDAO {
    
    public List<Room> getListRoomByUserId(User user);
    public Room createRoomByUsers(Room room, List<User> listUser);
    public Room getRoomById(int roomId);
    public boolean updateRoom(Room room);
}
