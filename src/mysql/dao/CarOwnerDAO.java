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
import java.util.ArrayList;
import java.io.FileInputStream;
import mysql.core.CarOwner;

public class CarOwnerDAO {
    private Connection myConn;
    
    public CarOwnerDAO()throws SQLException{
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
    
    public void AddCarOwner(CarOwner owner)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("INSERT INTO carowner(branch_id,agent_id,owner_firstName,owner_lastName,contact_number,cars_owned) "
                                        + "VALUES(?,?,?,?,?,?)");
            
            pst.setInt(1,owner.getBranchID());
            pst.setInt(2,owner.getAgentID());
            pst.setString(3,owner.getOwnerFirstName());
            pst.setString(4,owner.getOwnerLastName());
            pst.setString(5, owner.getContactNumber());
            pst.setInt(6,owner.getOwnedCars());
            
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
    }
    
    public void UpdateCarOwner(CarOwner owner)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("UPDATE carowner SET agent_id = ?, branch_id = ?, owner_firstName = ?, owner_lastName = ?, contact_number = ? "
                                        + "WHERE owner_id = ?");
            pst.setInt(1,owner.getAgentID());
            pst.setInt(2,owner.getBranchID());
            pst.setString(3,owner.getOwnerFirstName());
            pst.setString(4,owner.getOwnerLastName());
            pst.setString(5,owner.getContactNumber());
            pst.setInt(6,owner.getOwnerID());
            
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
    }
    
    public void DeleteCarOwner(int ownerID)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("DELETE FROM carowner WHERE owner_id = ?");
            pst.setInt(1,ownerID);
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
    }
    
    public void updateCarsOwned(int ownerID,int cars)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("UPDATE carowner SET cars_owned = ? WHERE owner_id = ?");
            pst.setInt(1,cars);
            pst.setInt(2,ownerID);
            
            pst.executeUpdate();
            
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
        
    }
    
    public int getCarOwnerID(String owner)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        int id = 0;
        try{
            pst = myConn.prepareStatement("SELECT owner_id,owner_firstName,owner_lastName FROM carowner");
            rs = pst.executeQuery();
            
            while(rs.next()){
                String theOwner = String.format("%s %s",rs.getString("owner_firstName"),rs.getString("owner_lastName")); 
                if(theOwner.equalsIgnoreCase(owner)){
                    id = rs.getInt("owner_id");
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
        
    public String getBranchByCarOwner(String owner)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        String branch = new String();
        try{
            pst = myConn.prepareStatement("SELECT branch_name FROM carowner as co, branch as br "
                                        + "WHERE CONCAT(owner_firstName,owner_lastName) LIKE '%"+owner+"%' "
                                        + "AND co.branch_id = br.branch_id");
            rs  = pst.executeQuery();
            
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
    
    public String getAgentByCarOwner(String owner)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        String agent = new String();
        try{
            pst = myConn.prepareStatement("SELECT agent_firstName,agent_lastName FROM carowner as co, agent as ag "
                                        + "WHERE CONCAT(owner_firstName,owner_lastName) LIKE '%"+owner+"%' "
                                        + "AND co.agent_id = ag.agent_id");
            rs = pst.executeQuery();
            
            while(rs.next()){
                agent = String.format("%s %s",rs.getString("agent_firstName"),rs.getString("agent_lastName"));
            }
            return agent;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return agent;
    }
    
    public int getOwnedCars(String owner)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        int cars = 0;
        try{
            pst = myConn.prepareStatement("SELECT cars_owned,owner_firstName,owner_lastName FROM carowner ");
            rs = pst.executeQuery();
            
            while(rs.next()){
                String name = String.format("%s %s",rs.getString("owner_firstName"),rs.getString("owner_lastName"));
                if(name.equals(owner)){
                    cars = rs.getInt("cars_owned");
                }
                
            }
            return cars;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return cars;
    }
    
    public boolean checkIfOwner(String owner)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = myConn.prepareStatement("SELECT owner_firstName,owner_lastName FROM carowner");
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                String name = String.format("%s %s",rs.getString("owner_firstName"),rs.getString("owner_lastName"));
                if(name.equalsIgnoreCase(owner)){
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
    
    public ArrayList<CarOwner> getAllCarOwners()throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<CarOwner> list = new ArrayList<>();
        try{
            pst = myConn.prepareStatement("SELECT owner_id,agent_firstName,agent_lastName,branch_name,owner_firstName,owner_lastName,co.contact_number,cars_owned "
                                            + "FROM carowner as co, agent as ag, branch as br "
                                            + "WHERE (co.agent_id,co.branch_id) = (ag.agent_id,br.branch_id)");
            rs = pst.executeQuery();
            
            while(rs.next()){
                CarOwner owner = convertRowToOwner(rs);
                list.add(owner);
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
    
    public ArrayList<CarOwner> searchCarOwner(String search)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<CarOwner> list = new ArrayList<>();
        try{
            pst = myConn.prepareStatement("SELECT owner_id,agent_firstName,agent_lastName,branch_name,owner_firstName,owner_lastName,co.contact_number,cars_owned "
                                        + "FROM carowner as co, agent as ag, branch as br "
                                        + "WHERE (co.agent_id,co.branch_id) = (ag.agent_id,br.branch_id) "
                                        + "AND CONCAT(agent_firstName,agent_lastName,branch_name,owner_firstName,owner_lastName) LIKE '%"+search+"%'");
            rs = pst.executeQuery();
            
            while(rs.next()){
                CarOwner owner = convertRowToOwner(rs);
                list.add(owner);
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
    
    private CarOwner convertRowToOwner(ResultSet rs)throws SQLException{
        int ownerID = rs.getInt("owner_id");
        int cars_owned = rs.getInt("cars_owned");
        String agent = rs.getString("agent_firstName") + " " + rs.getString("agent_lastName");
        String branch = rs.getString("branch_name");
        String first = rs.getString("owner_firstName");
        String last = rs.getString("owner_lastName");
        String contact = rs.getString("co.contact_number");
        
        CarOwner owner = new CarOwner(branch,agent,first,last,contact);
        owner.setOwnedCrs(cars_owned);
        owner.setOwnerID(ownerID);
        
        return owner;
    }
        private void close(PreparedStatement pst, ResultSet rs)throws SQLException{
            if(pst!=null){
                pst.close();
            }
            if(rs!= null){
                rs.close();
            }
        }
    
}
