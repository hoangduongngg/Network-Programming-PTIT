/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.UserRoom;
import java.util.List;

/**
 *
 * @author DUC
 */
public interface UserRoomDAO {
    
    public List<UserRoom> selectListUserRoomByRoomId(int roomId);
    public boolean createUserRoom(int userId, int roomId);
    public boolean deleteById(int userRoomId);
}
