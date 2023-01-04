/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import java.util.List;
/**
 *
 * @author DUC
 */
public interface UserDAO {
    
    public User checkLogin(String username, String password);
    public boolean checkExistsUser(String username);
    public boolean insert(User user);
    public boolean update(User user);
    public List<User> selectAll();
    public User selectById(int id);
}
