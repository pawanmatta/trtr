/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.cloud.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author admin
 */
public class LoginDAO extends DAO {

    private Connection connection;
    private PreparedStatement statement;

    public boolean checkUser(String username, String password) throws ClassNotFoundException, SQLException {
        try {
            connection = getConnection();

            String query = "select * from orders where User_name=? and Password=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return true;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            close(statement);
            close(connection);
        }
        return false;
    }
}
