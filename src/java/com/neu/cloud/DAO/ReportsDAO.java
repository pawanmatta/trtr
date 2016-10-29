/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.cloud.DAO;

import com.neu.cloud.POJO.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class ReportsDAO extends DAO {

    private Connection conn;
    private Statement s;

    public ArrayList<ArrayList<User>> generateUseCaseReport(int n) throws ClassNotFoundException, SQLException {
        ResultSet resultSet = null;
        ArrayList<ArrayList<User>> result = new ArrayList<ArrayList<User>>();

        for (int i = 1; i <= n; i++) {

            ArrayList<User> userList = new ArrayList<User>();
            try {
                conn = getConnection();

                String query = "select * from orders limit "+ 1000+ " offset "+ (i-1)*1000 +";";
                s = conn.prepareStatement(query);
                resultSet = s.executeQuery(query);

                while (resultSet.next()) {

                    int userID = resultSet.getInt("User_ID");
                    String username = resultSet.getString("User_name");
                    String password = resultSet.getString("Password");
                    String productName = resultSet.getString("product_name");

                    User user = new User();
                    user.setUserID(userID);
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setProductName(productName);

                    userList.add(user);
                }
                
                result.add(userList);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } finally {
                close(s);
                close(resultSet);
                close(conn);
            }
        }
        return result;

    }

}
