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
import mysql.core.Driver;
public class DriverDAO {
    private Connection myConn;
    
    public DriverDAO()throws SQLException{
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
    
    public void EmployDriver(Driver driver)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("INSERT INTO driver (agent_id,branch_id,car_id,driver_firstName,driver_lastName,contact_number) "
                                        + "VALUES(?,?,?,?,?,?)");
            pst.setInt(1,driver.getAgentID());
            pst.setInt(2,driver.getBranchID());
            pst.setInt(3,driver.getCarID());
            pst.setString(4, driver.getDriverFirstName());
            pst.setString(5,driver.getDriverLastName());
            pst.setString(6,driver.getContactNumber());
            
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
        
    }
    
    public void UpdateDriver(Driver driver)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("UPDATE driver SET agent_id = ?, branch_id = ?, car_id = ?, driver_firstName = ?, driver_lastName = ?, contact_number = ? "
                                        + "WHERE driver_id = ?");
            pst.setInt(1,driver.getAgentID());
            pst.setInt(2,driver.getBranchID());
            pst.setInt(3,driver.getCarID());
            pst.setString(4,driver.getDriverFirstName());
            pst.setString(5,driver.getDriverLastName());
            pst.setString(6,driver.getContactNumber());
            pst.setInt(7,driver.getDriverID());
            
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
    }
    
    public void FireDriver(int driverID)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("DELETE FROM driver WHERE driver_id = ?");
            pst.setInt(1,driverID);
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
            
    }
    
    public ArrayList<Driver> getAllDrivers()throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Driver> list = new ArrayList<>();
        try{
            pst = myConn.prepareStatement("SELECT driver_id, agent_firstName,agent_lastName,car_name,branch_name,driver_firstName,driver_lastName,drv.contact_number "
                                        + "FROM driver as drv, agent as ag, branch as br, car "
                                        + "WHERE (drv.agent_id,drv.car_id,drv.branch_id) = (ag.agent_id,car.car_id,br.branch_id) ");
            rs = pst.executeQuery();
            
            while(rs.next()){
                Driver driver = convertRowToDriver(rs);
                list.add(driver);
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
    
    public ArrayList<Driver> searchDriver(String search)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Driver> list = new ArrayList<>();
        try{
            pst = myConn.prepareStatement("SELECT driver_id, agent_firstName,agent_lastName,car_name,branch_name,driver_firstName,driver_lastName,drv.contact_number "
                                        + "FROM driver as drv, agent as ag, branch as br, car "
                                        + "WHERE (drv.agent_id,drv.car_id,drv.branch_id) = (ag.agent_id,car.car_id,br.branch_id) "
                                        + "AND CONCAT(agent_firstName,agent_lastName,car_name,branch_name,driver_firstName,driver_lastName) LIKE '%"+search+"%' ");
            rs = pst.executeQuery();
            
            while(rs.next()){
                Driver driver = convertRowToDriver(rs);
                list.add(driver);
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
    
    public int getDriverID(String driver)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        int id = 0;
        try{
            pst = myConn.prepareStatement("SELECT driver_id FROM driver ");
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                String name = String.format("%s %s",rs.getString("driver_firstName"),rs.getString("driver_lastName"));
                if(name.equalsIgnoreCase(driver)){
                    id = rs.getInt("driver_id");
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
    
    public String getCarDrived(String driver)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        String car = new String();
        try{
            pst = myConn.prepareStatement("SELECT car_name FROM driver as drv, car WHERE drv.car_id = car.car_id ");
            rs = pst.executeQuery();
            
            while(rs.next()){
                String name = String.format("%s %s",rs.getString("driver_firstName"),rs.getString("driver_lastName"));
                if(name.equalsIgnoreCase(driver)){
                    car = rs.getString("car_name");
                }
                
            }
            return car;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return car;
    }
    
    public String getDriverByCar(int carID)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        String driver = new String();
        try{
            pst = myConn.prepareStatement("SELECT driver_firstName,driver_lastName FROM driver WHERE car_id = ? ");
            pst.setInt(1,carID);
            rs = pst.executeQuery();
            
            while(rs.next()){
                driver = String.format("%s %s",rs.getString("driver_firstName"),rs.getString("driver_lastName"));
            }
            return driver;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return driver;
    }
    
    public boolean checkIfDriver(String driver)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = myConn.prepareStatement("SELECT driver_firstName,driver_lastName FROM driver");
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                String name = String.format("%s %s",rs.getString("driver_firstName"),rs.getString("driver_lastName"));
                if(name.equalsIgnoreCase(driver)){
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
    
    public boolean checkIfCarHasDriver(int carID)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = myConn.prepareStatement("SELECT drv.car_id FROM driver as drv, car WHERE car.car_id = drv.car_id");
            rs = pst.executeQuery();
            
            while(rs.next()){
                if(rs.getInt("drv.car_id") == carID){
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
    
    private Driver convertRowToDriver(ResultSet rs)throws SQLException{
        int driver_id = rs.getInt("driver_id");
        String agent = String.format("%s %s",rs.getString("agent_firstName"),rs.getString("agent_lastName"));
        String branch = rs.getString("branch_name");
        String car = rs.getString("car_name");
        String first = rs.getString("driver_firstName");
        String last = rs.getString("driver_lastName");
        String contact = rs.getString("drv.contact_number");
        
        Driver driver = new Driver(branch,agent,car,first,last,contact);
        driver.setDriverID(driver_id);
        
        return driver;
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
