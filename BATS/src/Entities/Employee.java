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
public class Employee extends User{
    public Employee(){super();}
    
    public Employee(String username,String password,String userid){
        super(username,password,userid);
    }
}
