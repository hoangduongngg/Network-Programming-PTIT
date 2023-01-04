/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.DAO;
import dao.UserDAO;
import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class UserDAOImpl extends DAO implements UserDAO {

 

    @Override
    public User checkLogin(String username, String password) {
        String sql = "Select * from user where Username = ? and Password = ? ";
        User user = null;
        try {
            super.connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("ID"));
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                user.setDisplayName(rs.getString("DisplayName"));
            }
        } catch (SQLException e) {
        } finally {
            return user;
        }

    }

    @Override
    public boolean checkExistsUser(String username) {
        String sql = "Select * from user where username = ?";
        boolean flag = false;
        try {
            super.connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            flag = rs.next();
        } catch (SQLException e) {
        } finally {
            return flag;
        }
    }

    @Override
    public List<User> selectAll() {
        String sql = "Select * from user";
        List<User> users = new ArrayList<>();
        try {
            super.connect();
            Statement statement = jdbcConnection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("ID"));
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                user.setDisplayName(rs.getString("DisplayName"));
                user.setActive(rs.getString("Active"));
                users.add(user);
            }
        } catch (SQLException e) {
        } finally {
            return users;
        }

    }

    @Override
    public User selectById(int id) {
        String sql = "Select * from user where id = ?";
        User user = null;
        try {
            super.connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("ID"));
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                user.setDisplayName(rs.getString("DisplayName"));
                user.setActive(rs.getString("Active"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return user;
        }
    }

    @Override
    public boolean insert(User user) {
        String sql = "INSERT INTO USER (USERNAME,"
                + "PASSWORD,"
                + "DISPLAYNAME)"
                + "VALUES (?,?,?)";
        boolean flag = false;
        try {
            super.connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getDisplayName());
            flag = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return flag;
        }
    }

    @Override
    public boolean update(User user) {
        String sql = "UPDATE USER set "
                + "USERNAME = ?,"
                + "PASSWORD = ?,"
                + "DISPLAYNAME = ?,"
                + "ACTIVE = ? "
                + "Where ID = ?";
        boolean flag = false;
        try {
            super.connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getDisplayName());
            statement.setString(4, user.getActive());
            statement.setInt(5, user.getId());
            flag = statement.executeUpdate() > 0;
        } catch (SQLException e) {
        } finally {
            return flag;
        }
    }

}
