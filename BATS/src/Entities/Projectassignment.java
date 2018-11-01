/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Entities.Expense;
import static SQLconnect.SQLconnect.getUserConn;
import com.mysql.jdbc.PreparedStatement;
import java.awt.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ziyin
 */
public class Projectassignment {
    
//    private String billcode;
    private String paID;
    private String userid;
    private String projectid;
    private String projectResp;
    
    

    public Projectassignment() {
       
    }

    public Projectassignment(String userid, String projectid, String projectResp) {
        this.userid = userid;
        this.projectid = projectid;
        this.projectResp = projectResp;
       
    }

    public String getPaID() {
        return paID;
    }

    public void setPaID(String paID) {
        this.paID = paID;
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

    /**
     * @return the projectid
     */
    public String getProjectid() {
        return projectid;
    }

    /**
     * @param projectid the projectid to set
     */
    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectResp() {
        return projectResp;
    }

    public void setProjectResp(String projectResp) {
        this.projectResp = projectResp;
    }
    
    public String getpid(String billcode){
         Connection conn =getUserConn();
         String proID=null;
         int i=0;
         String sql="select pa.pid from projectassignment pa inner join expense e on pa.paID=e.paID where e.billCode = ?";
         PreparedStatement psmt;
      try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString( 1,billcode);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
            proID=rs.getString(1);
            }
        } catch (SQLException e) {
              e.printStackTrace();
        }
      return proID;
    }
    
     public int getpaID(String uid,String pID){
        Connection conn =getUserConn();
        int paID=0;
        String sql="select paID from projectassignment where  uid = ? and pID = ?";
        PreparedStatement psmt;
        try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString( 1,uid);
            psmt.setString( 2,pID);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
            paID=rs.getInt(1);
            }
        } 
        catch (SQLException e) {
              e.printStackTrace();
        }
        return paID;
    }
     
     public static void insertpa(Projectassignment pa) {
        Connection conn =getUserConn();
        String sql = "insert into projectassignment values(null,?,?,?)";
        PreparedStatement psmt;
        try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString(1,pa.getUserid());
            psmt.setString(2,pa.getProjectid());
            psmt.setString(3,pa.getProjectResp());
            psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (SQLException e) {
              e.printStackTrace();
        }
    }
    
     public static boolean checkpa(String uid,String pid){
      Connection conn =getUserConn();
 
        String sql="select * from projectassignment where uid=? and pID=?";
        PreparedStatement psmt;
        boolean flag= false;
         try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString(1, uid);
            psmt.setString(2, pid);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                flag = true;
            }
        } catch (SQLException e) {
              e.printStackTrace();
        }
         return flag;
      }
}
