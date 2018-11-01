/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Ziyin
 */
public class Administrator extends User{
    public Administrator(){super();}
    
    public Administrator(String username,String password,String userid){
        super(username,password,userid);
    }
}
