/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import static SQLconnect.SQLconnect.getUserConn;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ziyin
 */
public class User{

    private String username;
    private String password;
    private String userid;

    /**
     * @return the username
     */
    public User(){}
    
    public User(String username,String password,String userid){
        this.username = username;
        this.password = password;
        this.userid = userid;
    }
    
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    public boolean login(String username, String password) {
            Connection conn =getUserConn();
            boolean flag =false;
            try{
             String sql="select Username,Userpass from user";
             PreparedStatement pstmt = (PreparedStatement)conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();
             while(rs.next()){
             String name =rs.getString(1);
             String pass=rs.getString(2);
             if(username.equals(name)&&password.equals(pass)){
             flag=true;
             break;
             }
             }
            }catch(SQLException e){
                 e.printStackTrace();
            }
            return flag;
        }

    public void register(User user) {
        Connection conn =getUserConn();
        String sql = "insert into user (Userid,Username,Userpass) values(?,?,?)";
        PreparedStatement psmt;
        try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString( 1,user.getUserid());
            psmt.setString(2,user.getUsername());
            psmt.setString(3,user.getPassword());
            psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (SQLException e) {
              e.printStackTrace();
        }
}
    
    public int getTitle(String username,boolean flag){
        Connection conn =getUserConn();
        String sql="select Userid from user where Username = ?";
        PreparedStatement psmt;
        int i=0;
        if(flag){
            try{
                psmt = (PreparedStatement) conn.prepareStatement(sql);
                psmt.setString( 1,username);
                ResultSet rs = psmt.executeQuery();
                while(rs.next()){
                String title=rs.getString(1);
                i=Integer.parseInt(String.valueOf(title.charAt(0)));}
            } catch (SQLException e) {
                  e.printStackTrace();
            }}
            return i;
    }
    
     public String getid(String username){
        Connection conn =getUserConn();
        String userid=null;
        String sql="select Userid from user where Username = ?";
        PreparedStatement psmt;
        try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString( 1,username);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
            userid=rs.getString(1);
            }
          
        } catch (SQLException e) {
              e.printStackTrace();
        }
        return userid;
    }
     
    public static int getuserNum(){
        Connection conn =getUserConn();
        String sql="select Username from user";
         PreparedStatement psmt;
         int i=0;
          try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                i++;
               
            }
        } catch (SQLException e) {
              e.printStackTrace();
        }
          return i;
    }
    
    public static String[] getAllUserName(int i){
        Connection conn =getUserConn();
        String [] arr=new String [i-1];
        String sql="select Username from user";
        PreparedStatement psmt;
        int t=0;
         try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                if(rs.getString(1).equals("DaveZhao")){
                continue;
                }else{
                arr[t]=rs.getString(1);
                t++;}
            }
        } catch (SQLException e) {
              e.printStackTrace();
        }
    return arr;
    }
    
     public static String getUserName(String billcode){
             Connection conn =getUserConn();
     String uid=null;
     String sql="select u.Username from user u inner join projectassignment pa on u.Userid=pa.uid inner join expense e on pa.paID=e.paID where billCode = ?";
     PreparedStatement psmt;
      try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString( 1,billcode);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
            uid=rs.getString(1);
            
            }
          
        } catch (SQLException e) {
              e.printStackTrace();
        }
      return uid;
    }
     
}
