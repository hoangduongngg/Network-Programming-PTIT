/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

/**
 *
 * @author DUC
 */
import dao.DAO;
import dao.MessageDAO;
import dao.UserDAO;
import entity.Message;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
public class MessageDAOImpl extends DAO implements MessageDAO {

    UserDAO userDAO = new UserDAOImpl();

  

//    @Override
//    public List<Message> selectAll() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    
//
//    public int insert(User user, Room room, Message message) {
//        String sql = "INSERT INTO MESSAGE (USERID,"+
//				"ROOMID,"+
//				"CONTENT,"+
//				"SENDTIME)"+
//				"VALUES (?,?, ?, ?)";
//        try{
//            this.preStatement = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            this.preStatement.setInt(1, user.getId());
//            this.preStatement.setInt(2, room.getId());
//            this.preStatement.setString(3, message.getContent());
////            this.preStatement.setDate(4, java.sql.Date.valueOf(message.getSendTime()));
//            int check = this.preStatement.executeUpdate();
//            return check;
//        }catch(SQLException e){
//            e.printStackTrace();
//            return 0;
//        }
//       
//    }
//    
//    @Override
//    public Message selectById(int id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public List<Message> selectByRoomId(int roomId) {
        List<Message> messages = new ArrayList<>();
        String sql = "Select * from message \n" +
                    "where RoomId = ?\n" +
                    "order by SendTime";

        try {
            super.connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, roomId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getInt("ID"));
                message.setRoomId(roomId);
                message.setUser(userDAO.selectById(rs.getInt("UserID")));
                message.setContent(rs.getString("Content"));
                message.setSendTime(rs.getDate("SendTime"));
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return messages;
        }
    }

    @Override
    public Message insertMessage(Message message, int userId, int roomId) {
        String sql = "INSERT INTO Message (UserID,RoomID,Content,SendTime) VALUES(?,?,?,?)";
        String sql2 = "SELECT MAX(ID) FROM Message";
        boolean flag = false;
        try {
            super.connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, roomId);
            statement.setString(3, message.getContent());
            statement.setDate(4,new Date(message.getSendTime().getTime()));
            flag = (statement.executeUpdate() > 0);
            System.out.println(flag);
            if (flag) {
                Statement statement1 = jdbcConnection.createStatement();
                ResultSet resultSet = statement1.executeQuery(sql2);
                if (resultSet.next()) {
                    message.setId(resultSet.getInt("MAX(ID)"));
                    message.setUser(userDAO.selectById(userId));
                }
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return message;
        }
    }

}
