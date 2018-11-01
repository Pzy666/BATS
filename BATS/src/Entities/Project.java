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
public class Project {
    private String projectID;
    private String projetName;
    private String projectDetail;

    /**
     * @return the projectID
     */
    
    
    public Project() {
    }

    public Project(String projectID, String projetName, String projectDetail) {
        this.projectID = projectID;
        this.projetName = projetName;
        this.projectDetail = projectDetail;
    }

    public String getProjectID() {
        return projectID;
    }

    /**
     * @param projectID the projectID to set
     */
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    /**
     * @return the projetName
     */
    public String getProjetName() {
        return projetName;
    }

    /**
     * @param projetName the projetName to set
     */
    public void setProjetName(String projetName) {
        this.projetName = projetName;
    }

    /**
     * @return the projectDetail
     */
    public String getProjectDetail() {
        return projectDetail;
    }

    /**
     * @param projectDetail the projectDetail to set
     */
    public void setProjectDetail(String projectDetail) {
        this.projectDetail = projectDetail;
    }
    
    public static String getProjectName(String proID){
         Connection conn =getUserConn();
         String proN=null;
      try{
            String sql="select projectName from project where projectID = ?";
            PreparedStatement psmt;
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString( 1,proID);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
            proN=rs.getString(1);
            continue;
            }
        } catch (SQLException e) {
              e.printStackTrace();
        }
      return proN;
    }
    
      public static int getproNum(){
        Connection conn =getUserConn();
        String sql="select projectName from project";
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
    
      public static String getpidbyName(String proname){
        Connection conn =getUserConn();
        String proID=null;
        String sql="select projectID from project where projectName = ?";
        PreparedStatement psmt;
        try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString( 1,proname);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
            proID=rs.getString(1);
            }
        } 
        catch (SQLException e) {
              e.printStackTrace();
        }
        return proID;
      }
      
     public static String[] getAllProjectName(int i){
        Connection conn =getUserConn();
        String [] arr=new String [i];
        String sql="select projectName from project";
        PreparedStatement psmt;
        int t=0;
         try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                arr[t]=rs.getString(1);
                t++;
            }
        } catch (SQLException e) {
              e.printStackTrace();
        }
         return arr;
    }
    
    public static String getProjectID(String name){
     Connection conn =getUserConn();
     String userid=null;
     String sql="select projectID from project where projectName = ?";
     PreparedStatement psmt;
      try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString( 1,name);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
            userid=rs.getString(1);
            }
          
        } catch (SQLException e) {
              e.printStackTrace();
        }
      return userid;
    }
    
     public static String getdescp(String proname){
     Connection conn =getUserConn();
     String userid=null;
     String sql="select projectDes from project where projectName = ?";
     PreparedStatement psmt;
      try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString( 1,proname);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
            userid=rs.getString(1);
            }
          
        } catch (SQLException e) {
              e.printStackTrace();
        }
      return userid;
    }
    
    public static void insertProject(Project pro) {
        Connection conn =getUserConn();
        String sql = "insert into project values(?,?,?)";
        PreparedStatement psmt;
        try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString(1,pro.getProjetName());
            psmt.setString(2,pro.getProjectID());
            psmt.setString(3,pro.getProjectDetail());
            psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (SQLException e) {
              e.printStackTrace();
        }
    }
    
    public static void updateProjectdes(String name,String desc){
         Connection conn =getUserConn();
         String userid=null;
         String sql = "update project set projectDes='" + desc + "' where projectName='" + name+ "'";
         PreparedStatement psmt;
          try{
                psmt = (PreparedStatement) conn.prepareStatement(sql);
                psmt.executeUpdate();
            } catch (SQLException e) {
                  e.printStackTrace();
            }
    }
}
