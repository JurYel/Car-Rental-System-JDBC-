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
import java.util.Properties;
import java.io.FileInputStream;
import java.util.ArrayList;
import mysql.core.Account;
import mysql.core.EncryptPassword;

public class AccountsDAO {
    private Connection myConn;
    
    public AccountsDAO()throws SQLException{
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
    
    public void registerAccount(Account acc)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("INSERT INTO accounts(account_id,first_name,last_name,password) VALUES(?,?,?,?) ");
            pst.setString(1,acc.getAccountID());
            pst.setString(2,acc.getFirstName());
            pst.setString(3,acc.getLastName());
            pst.setString(4,EncryptPassword.getHash(acc.getPassword().getBytes(),"MD5"));
            
            pst.executeUpdate();
            
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
    }
    
    public void updateAccount(Account acc)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("UPDATE accounts SET first_name = ?, last_name = ? WHERE account_id = ?");
            pst.setString(1,acc.getFirstName());
            pst.setString(2,acc.getLastName());
            pst.setString(3,acc.getAccountID());
            
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
        
    }
    
    public void changePassword(String name,String pass)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("UPDATE accounts set Password = ? WHERE CONCAT(first_name,last_name) LIKE '%"+name+"%' ");
            pst.setString(1,EncryptPassword.getHash(pass.getBytes(), "MD5"));
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
    }
    
    public void deleteAccount(String id)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("DELETE FROM accounts WHERE account_id = ?");
            pst.setString(1,id);
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
    }
    
    public boolean checkIfAccountExists(String id)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = myConn.prepareStatement("SELECT account_id FROM accounts");
            
            rs = pst.executeQuery();
            while(rs.next()){
                
                if(rs.getString("account_id").equalsIgnoreCase(id)){
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
    
    public boolean checkEncryptedPassword(String id,String pass)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = myConn.prepareStatement("SELECT password FROM accounts WHERE account_id = ? ");
            pst.setString(1,id);
            rs = pst.executeQuery();
            
            while(rs.next()){
                if(rs.getString("password").equals(EncryptPassword.getHash(pass.getBytes(), "MD5"))){
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
    
    public boolean checkPassword(String id, String pass)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = myConn.prepareStatement("SELECT password FROM accounts WHERE account_id = ?");
            pst.setString(1,id);
            rs = pst.executeQuery();
            
            while(rs.next()){
                if(rs.getString("password").equals(pass)){
                    return true;
                }
            }
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return false;
            
    }
    
    public String getAccountID(String name)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        String id = new String();
        try{
            pst = myConn.prepareStatement("SELECT account_id FROM accounts WHERE CONCAT(first_name,last_name) LIKE '%"+name+"%'");
            rs = pst.executeQuery();
            
            while(rs.next()){
                id = rs.getString("account_id");
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
    
    public ArrayList<Account> getAllAccounts()throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Account> list = new ArrayList<>();
        try{
            pst = myConn.prepareStatement("SELECT * FROM accounts");
            
            rs = pst.executeQuery();
            while(rs.next()){
                Account acc = convertRowToAccount(rs);
                list.add(acc);
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
    
    public ArrayList<Account> searchAccount(String search)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Account> list = new ArrayList<>();
        try{
            pst = myConn.prepareStatement("SELECT account_id, first_name, last_name, password "
                                        + "FROM account WHERE CONCAT(first_name,last_name) LIKE '%"+search+"%' ");
            rs = pst.executeQuery();
            
            while(rs.next()){
                Account account = convertRowToAccount(rs);
                list.add(account);
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
    
    private Account convertRowToAccount(ResultSet rs)throws SQLException{
        String id = rs.getString("account_id");
        String first = rs.getString("first_name");
        String last = rs.getString("last_name");
        String pass = rs.getString("password");
        
        Account account = new Account(id,first,last,pass);
       
        
        return account;
    }
    
    private void close(PreparedStatement pst, ResultSet rs)throws SQLException{
        if(pst!=null){
            pst.close();
        }
        if(rs!=null){
            rs.close();
        }
    }
}
