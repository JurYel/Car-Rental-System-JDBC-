/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql.dao;

/**
 *
 * @author Jur Yel
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.ArrayList;
import mysql.core.Customer;

public class CustomerDAO {
    private Connection myConn;
    
    public CustomerDAO()throws SQLException{
        try{
            Properties props = new Properties();
            props.load(new FileInputStream("sql/car_rentalDB.properties"));
            
            String user = props.getProperty("user");
            String pass = props.getProperty("password");
            String dburl = props.getProperty("dburl");
            
            myConn = DriverManager.getConnection(dburl,user,pass);
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
    }
    
    public void registerCustomer(Customer customer)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("INSERT INTO customer (first_name,last_name,contact_number) VALUES(?,?,?)");
            pst.setString(1,customer.getFirstName());
            pst.setString(2,customer.getLastName());
            pst.setString(3,customer.getContactNumber());
            
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
        
    }
    
    public int getCustomerID(String customer)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        int id = 0;
        try{
           pst = myConn.prepareStatement("SELECT customer_id,first_name,last_name FROM customer ");
           rs = pst.executeQuery();
           
           while(rs.next()){
               String name = String.format("%s %s",rs.getString("first_name"),rs.getString("last_name"));
               if(name.equalsIgnoreCase(customer)){
                
                 id = rs.getInt("customer_id");   
               }
           }
           return id;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return id;
    }
    
    public boolean checkIfRegistered(String first,String last)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = myConn.prepareStatement("SELECT first_name,last_name FROM customer");
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                
                if(rs.getString("first_name").equalsIgnoreCase(first) || 
                        rs.getString("last_name").equalsIgnoreCase(last)){
                    return true;
                }
            }
            return false;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return false;
    }
    
    public boolean checkIfBooked(String customer)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = myConn.prepareStatement("SELECT first_name,last_name FROM customer as cu, booking as bk "
                                            + "WHERE cu.customer_id = bk.customer_id");
            rs = pst.executeQuery();
            
            while(rs.next()){
                 String name = String.format("%s %s",rs.getString("first_name"),rs.getString("last_name"));
                 if(name.equalsIgnoreCase(customer)){
                     return true;
                 }
            }
            return false;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return false;
    }
    
    public ArrayList<Customer> getAllCustomer()throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Customer> list = new ArrayList<>();
        try{
            pst = myConn.prepareStatement("SELECT * FROM customer");
            rs = pst.executeQuery();
            
            while(rs.next()){
                Customer customer = convertRowToCustomer(rs);
                list.add(customer);
            }
            return list;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return list;
    }
    
    public ArrayList<Customer> searchCustomer(String search)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Customer> list = new ArrayList<>();
        try{
            pst = myConn.prepareStatement("SELECT * FROM customer WHERE CONCAT(first_name,last_name) LIKE '%"+search+"%' ");
            rs = pst.executeQuery();
            
            while(rs.next()){
                Customer customer = convertRowToCustomer(rs);
                list.add(customer);
            }
            return list;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return list;
    }
    
    private Customer convertRowToCustomer(ResultSet rs)throws SQLException{
        int id = rs.getInt("customer_id");
        String first = rs.getString("first_name");
        String last = rs.getString("last_name");
        String contact = rs.getString("contact_number");
        
        Customer customer = new Customer(first,last,contact);
        customer.setCustomerID(id);
        
        return customer;
    }
    
    private void close(PreparedStatement pst,ResultSet rs)throws SQLException{
        if(pst!=null){
            pst.close();
        }
        if(rs!=null){
            rs.close();
        }
    }
}
