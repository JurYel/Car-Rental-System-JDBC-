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

import mysql.core.Manager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ArrayList;
import java.io.FileInputStream;

public class ManagerDAO {
    private Connection myConn;
    
    public ManagerDAO()throws SQLException{
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
    
    public void AddNewManager(Manager manager,int branchID)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("INSERT INTO manager(branch_id,mngr_lastName,mngr_firstName,contact_number) "
                                        + "VALUES(?,?,?,?)");
            pst.setInt(1,branchID);
            pst.setString(2,manager.getManagerLastName());
            pst.setString(3,manager.getManagerFirstName());
            pst.setString(4,manager.getContactNumber());
            
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
    }
    
    public void UpdateManager(Manager manager,int branchID)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("UPDATE manager SET branch_id = ?, mngr_lastName = ?,mngr_firstName = ?, "
                                        + "contact_number = ? WHERE manager_id = ?");
            pst.setInt(1,branchID);
            pst.setString(2,manager.getManagerLastName());
            pst.setString(3,manager.getManagerFirstName());
            pst.setString(4,manager.getContactNumber());
            pst.setInt(5, manager.getManagerID());
            
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
    }
    
    public void FireManager(String manager)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("DELETE FROM manager WHERE "
                                        + "CONCAT(mngr_lastName,mngr_firstName) LIKE '%"+manager+"%'");
            pst.executeUpdate();
            
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
        
    }
    
    
    public ArrayList<Manager> getAllManagers()throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Manager> list = new ArrayList<>();
        try{
            pst = myConn.prepareStatement("SELECT branch_id, branch_name, branch_mngr_first,branch_mngr_last,contact_number "
                                            + "FROM branch");
            rs = pst.executeQuery();
            
            while(rs.next()){
                Manager manager = convertRowToManager(rs);
                list.add(manager);
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
    
    public ArrayList<Manager> searchManager(String search)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Manager> list = new ArrayList<>();
        try{
            pst = myConn.prepareStatement("SELECT branch_id, branch_name, branch_mngr_first,branch_mngr_last,contact_number "
                                        + "FROM branch as br "
                                        + "WHERE CONCAT(branch_name,branch_mngr_first,branch_mngr_last) LIKE '%"+search+"%'");
            rs = pst.executeQuery();
            
            while(rs.next()){
                Manager manager = convertRowToManager(rs);
                list.add(manager);
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
    
    private Manager convertRowToManager(ResultSet rs)throws SQLException{
        int managerID = rs.getInt("branch_id");
        String branch = rs.getString("branch_name");
        String last = rs.getString("branch_mngr_last");
        String first = rs.getString("branch_mngr_first");
        String contact = rs.getString("contact_number");
        
        Manager manager = new Manager(branch,last,first,contact);
        manager.setManagerID(managerID);
        
        return manager;
    } 
    
    private void close(PreparedStatement pst, ResultSet rs)throws SQLException{
        if(pst!= null){
            pst.close();
        }
        if(rs!=null){
            rs.close();
        }
    }
    
}
