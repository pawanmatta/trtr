/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.cloud.DAO;

import com.neu.cloud.POJO.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dishujindal
 */
public class DAO {
        
    private final String db_driver="com.mysql.jdbc.Driver";
    private final String db_url = "jdbc:mysql://localhost:3306/clouddb";
//    private final String db_url = "jdbc:mysql://cloudprojectdb.c5ufm637qjg3.us-east-1.rds.amazonaws.com:3306/clouddb";
    //private final String db_url = "jdbc:mysql://clouddb2.c5ufm637qjg3.us-east-1.rds.amazonaws.com:3306/clouddb";
    private final String db_user="root";
    private final String db_password="root";
   // private final String db_password="rootroot";
    
    public DAO(){
        try{
        Class.forName(db_driver);
        }
        catch(ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public Connection getConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(db_url,db_user ,db_password);
            
        }catch(SQLException e){
            System.out.println(e.getMessage()); 
        }
        return conn;
    }
    
    public void close(ResultSet rs){
        if(rs!= null){
            try{
            rs.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage()); 
            }
        }
    }
    public void close(Statement statement){
        if(statement!= null){
            try{
            statement.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage()); 
            }
        }
    }
    public void close(Connection conn){
        if(conn!= null){
            try{
            conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage()); 
            }
        }
    }
    
}
