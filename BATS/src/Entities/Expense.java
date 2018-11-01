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
public class Expense {
    
    private String billcode;
    private String flightEx;
    private String hotelEx;
    private String groundEx;
    private String diningEx;
    private String otherEx;
    private int paID;
    private String startdate;
    private String enddate;

    public Expense() {
    }

    public Expense(String billcode, String flightEx, String hotelEx, String groundEx, String diningEx, String otherEx, int paID, String startdate, String enddate) {
        this.billcode = billcode;
        this.flightEx = flightEx;
        this.hotelEx = hotelEx;
        this.groundEx = groundEx;
        this.diningEx = diningEx;
        this.otherEx = otherEx;
        this.paID = paID;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public String getBillcode() {
        return billcode;
    }

    public void setBillcode(String billcode) {
        this.billcode = billcode;
    }

    public String getFlightEx() {
        return flightEx;
    }

    public void setFlightEx(String flightEx) {
        this.flightEx = flightEx;
    }

    public String getHotelEx() {
        return hotelEx;
    }

    public void setHotelEx(String hotelEx) {
        this.hotelEx = hotelEx;
    }

    public String getGroundEx() {
        return groundEx;
    }

    public void setGroundEx(String groundEx) {
        this.groundEx = groundEx;
    }

    public String getDiningEx() {
        return diningEx;
    }

    public void setDiningEx(String diningEx) {
        this.diningEx = diningEx;
    }

    public String getOtherEx() {
        return otherEx;
    }

    public void setOtherEx(String otherEx) {
        this.otherEx = otherEx;
    }

    public int getPaID() {
        return paID;
    }

    public void setPaID(int paID) {
        this.paID = paID;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
    
    public static int getBillNum(String userid){
             Connection conn =getUserConn();
     int i=0;
     String sql="select e.billCode from expense e inner join projectassignment pa on e.paID=pa.paID  where pa.uid = ?";
     PreparedStatement psmt;
      try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString( 1,userid);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                i++;
            }
          
        } catch (SQLException e) {
              e.printStackTrace();
        }
      return i;
    }
    
     public static String [][]getBillCode(String userid,int t){
     Connection conn =getUserConn();
     String[][] BnN=new String[t][2];
     int i=0;
     String sql="select e.billCode from expense e inner join projectassignment pa on e.paID=pa.paID where pa.uid = ?";
     PreparedStatement psmt;
     try{
        psmt = (PreparedStatement) conn.prepareStatement(sql);
        psmt.setString(1,userid);
        ResultSet rs = psmt.executeQuery();
        while(rs.next()){
            BnN[i][0]=rs.getString(1);
            i++;
        }
    } catch (SQLException e) {
          e.printStackTrace();
      }
          for(int p=0;p<t;p++){
            Projectassignment pa = new Projectassignment();
            String aa =pa.getpid(BnN[p][0]);
            BnN[p][1]=Project.getProjectName(aa);
            }
         return BnN;
    }
     
    public static void insertexp(Expense exp) {
        Connection conn =getUserConn();
        String sql = "insert into expense values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement psmt;
        try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString(1,exp.getBillcode());
            psmt.setDouble(2,Double.parseDouble(exp.getFlightEx()));
            psmt.setDouble(3,Double.parseDouble(exp.getHotelEx()));
            psmt.setDouble(4,Double.parseDouble(exp.getGroundEx()));
            psmt.setDouble(5,Double.parseDouble(exp.getDiningEx()));
            psmt.setDouble(6,Double.parseDouble(exp.getOtherEx()));
            psmt.setInt(7,exp.getPaID());
            psmt.setString(8,exp.getStartdate());
            psmt.setString(9,exp.getEnddate());
            psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (SQLException e) {
              e.printStackTrace();
        } 
    }
    
    public static void updateExpense(String expense,String billCode,double NewNum){
    Connection conn =getUserConn();
    String userid=null;
    String sql = null;  
    switch(expense){
        case "Flight Expense":
            sql = "update expense set flightE='" + NewNum + "' where billCode='" + billCode + "'";
            break;
        case "Hotel Expense":
            sql = "update expense set hotelE='" + NewNum + "' where billCode='" + billCode + "'";
            break;
        case "Ground Transportation":
            sql = "update expense set groundE='" + NewNum + "' where billCode='" + billCode + "'";
            break;
        case "Dining Expense":
            sql = "update expense set diningE='" + NewNum + "' where billCode='" + billCode + "'";
            break;
        case "Other Expenses":
            sql = "update expense set othersE='" + NewNum + "' where billCode='" + billCode + "'";
            break;
    }
     
     PreparedStatement psmt;
      try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.executeUpdate();
        } catch (SQLException e) {
              e.printStackTrace();
        }
    }
    
    public static int getAllBillCodeNum(){
        Connection conn =getUserConn();
        int i=0;
        String sql="select billCode from expense";
        PreparedStatement psmt;
        try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                i++;
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
