/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Message;
import entity.Room;
import java.util.List;

/**
 *
 * @author DUC
 */
public interface MessageDAO {

    public List<Message> selectByRoomId(int roomId);
    public Message insertMessage(Message message, int userId, int roomId);
}
