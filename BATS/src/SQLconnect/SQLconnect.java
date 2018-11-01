/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLconnect;
import Entities.User;
import Entities.Projectassignment;
import Entities.Project;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Ziyin
 */
public class SQLconnect {
    public static Connection getUserConn() {
        Connection conn=null;
        try{
    String url = "jdbc:mysql://localhost/ism6259v1?autoReconnect=true&useSSL=false&user=root&password=111111";
    Class.forName("com.mysql.jdbc.Driver");
     conn = (Connection) DriverManager.getConnection(url);
        }catch(ClassNotFoundException cfe){
        cfe.printStackTrace();
        }catch (SQLException sqle)
        {sqle.printStackTrace();}
        return conn;
    } 
    
    public static String[][] importEDB(String[][]BnN,int i){
    java.sql.Connection conn =getUserConn();
    String [][] arr=new String [i][9];
    String sql="select * from expense where billCode = ?";
    PreparedStatement psmt;
    try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            for(int t=0;t<i;t++){
            psmt.setString(1,BnN[t][0]);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                
                arr[t][0]=BnN[t][1];
                arr[t][1]=rs.getString(1);
                arr[t][2]=rs.getString(2);
                arr[t][3]=rs.getString(3);
                arr[t][4]=rs.getString(4);
                arr[t][5]=rs.getString(5);
                arr[t][6]=rs.getString(6);
                arr[t][7]=rs.getString(8);
                arr[t][8]=rs.getString(9);
            }}
        } catch (SQLException e) {
              e.printStackTrace();
        }
    return arr;
    }
    
     public static String[][] importAll(int i){
    java.sql.Connection conn =getUserConn();
    String [][] arr=new String [i][10];
    String sql="select * from expense";
    PreparedStatement psmt;
    try{
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            int t=0;
            while(rs.next()){
                Projectassignment pa =new Projectassignment();
                String pid=pa.getpid(rs.getString(1));
                String username=User.getUserName(rs.getString(1));
                String pname =Project.getProjectName(pid);
                arr[t][0]=pname;
                arr[t][1]=username;
                arr[t][2]=rs.getString(1);
                arr[t][3]=rs.getString(2);
                arr[t][4]=rs.getString(3);
                arr[t][5]=rs.getString(4);
                arr[t][6]=rs.getString(5);
                arr[t][7]=rs.getString(6);
                arr[t][8]=rs.getString(8);
                arr[t][9]=rs.getString(9);
                t++;
            }
        } catch (SQLException e) {
              e.printStackTrace();
        } 
    return arr;
    }
}
