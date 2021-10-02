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
import java.util.ArrayList;
import java.util.Properties;
import java.io.FileInputStream;
import mysql.core.Branch;

public class BranchDAO {
    
    private Connection myConn;
    
    public BranchDAO()throws SQLException{
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
    
    
    public void AddNewBranch(Branch branch)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("INSERT INTO branch(branch_name,branch_mngr_first, branch_mngr_last,contact_number) "
                                + "VALUES(?,?,?,?)");
            pst.setString(1,branch.getBranchName());
            pst.setString(2, branch.getBranchManagerFirst());
            pst.setString(3,branch.getBranchManagerLast());
            pst.setString(4,branch.getContactNumber());
            
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
    }
    
    public void UpdateBranch(Branch branch)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("UPDATE branch SET branch_name = ? , branch_mngr_first = ? , branch_mngr_last = ? , contact_number = ? "
                                            + "WHERE branch_id = ?");
            pst.setString(1,branch.getBranchName());
            pst.setString(2,branch.getBranchManagerFirst());
            pst.setString(3,branch.getBranchManagerLast());
            pst.setString(4,branch.getContactNumber());
            pst.setInt(5, branch.getBranchID());
            
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
    }
    
    public void DeleteBranch(int id)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("DELETE FROM branch WHERE branch_id = ?");
            pst.setInt(1,id);
            
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
    }
    
    public int getBranchID(String branch)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        int branchID = 0;
        try{
            pst = myConn.prepareStatement("SELECT branch_id FROM branch "
                                            + "WHERE (branch_name) = (?)");
            pst.setString(1,branch);
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                branchID = rs.getInt("branch_id");
            }
            return branchID;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return branchID;
            
    }
        
    
    public String getBranchByManager(String manager)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        String branch = new String();
        try{
            pst = myConn.prepareStatement("SELECT branch_name FROM branch WHERE CONCAT(branch_mngr_first,branch_mngr_last) LIKE '%"+manager+"%'");
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                branch = rs.getString("branch_name");
            }
            return branch;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return branch;
    }
    
    public String getManagerByBranch(String branch)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        String manager = new String();
        try{
            pst = myConn.prepareStatement("SELECT branch_mngr_first,branch_mngr_last FROM branch WHERE branch_name = ?");
            pst.setString(1,branch);
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                manager = String.format("%s %s",rs.getString("branch_mngr_first"),rs.getString("branch_mngr_last"));
            }
            
            return manager;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return manager;
    }
    
    public boolean checkIfBranchExists(String branchName)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = myConn.prepareStatement("SELECT branch_name FROM branch");
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                if(rs.getString("branch_name").equalsIgnoreCase(branchName)){
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
    
    public boolean checkIfManager(String manager)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = myConn.prepareStatement("SELECT branch_mngr_first,branch_mngr_last FROM branch");
            rs = pst.executeQuery();
            
            while(rs.next()){
                String name = String.format("%s %s",rs.getString("branch_mngr_first"),rs.getString("branch_mngr_last"));
                if(name.equalsIgnoreCase(manager)){
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
    
    public ArrayList<Branch> getAllBranches()throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Branch> list = new ArrayList<>();
        try{
            pst = myConn.prepareStatement("SELECT * FROM branch");
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                Branch branch = convertRowToBranch(rs);
                list.add(branch);
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
    
    public ArrayList<Branch> searchBranch(String key)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Branch> list = new ArrayList<>();
        try{
            pst = myConn.prepareStatement("SELECT * FROM branch "
                                            + "WHERE CONCAT(branch_name,branch_mngr_first,branch_mngr_last) LIKE '%"+key+"%'");
            rs = pst.executeQuery();
            
            while(rs.next()){
                Branch branch = convertRowToBranch(rs);
                list.add(branch);
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
    
    private Branch convertRowToBranch(ResultSet rs)throws SQLException{
        int branchID = rs.getInt("branch_id");
        String branchName = rs.getString("branch_name");
        String branchManagerFirst = rs.getString("branch_mngr_first");
        String branchManagerLast = rs.getString("branch_mngr_last");
        String contactNum = rs.getString("contact_number");
        
        Branch branch = new Branch(branchName,branchManagerFirst,branchManagerLast,contactNum);
        branch.setBranchID(branchID);
        
        return branch;
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
