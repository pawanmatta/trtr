/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.cloud.DAO;

import com.neu.cloud.POJO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author admin
 */
public class UserDAO extends DAO{
    
    private Connection connection;
    private Statement statement;

    public void addUser(List<User> userList) throws ClassNotFoundException, SQLException {

        try{
        connection = getConnection();
        statement = connection.createStatement();
        
        String sql = "";
        for (User user : userList) {
            sql = "Insert into orders (User_Id, User_name, Password, product_name) values(" + user.getUserID() + ",'" + user.getUsername() + "','" + user.getPassword() + "','" + user.getProductName() + "');";
            statement.addBatch(sql);
        }
        statement.executeBatch();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            close(statement);
            close(connection);
        }
    }
}
