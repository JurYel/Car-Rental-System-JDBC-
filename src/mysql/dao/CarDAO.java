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
import java.util.Locale;
import mysql.core.Car;

public class CarDAO {
    private Connection myConn;
    
    public CarDAO()throws SQLException{
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
    
    public void RegisterNewCar(Car car)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("INSERT INTO car (owner_id,plate_number,car_name,brand,car_type) "
                                        + "VALUES(?,?,?,?,?)");
            pst.setInt(1,car.getOwnerID());
            pst.setString(2,car.getPlateNumber().toUpperCase(Locale.CANADA));
            pst.setString(3,car.getCarName());
            pst.setString(4,car.getBrand());
            pst.setString(5,car.getCarType());
            
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
    }
    
    public void UpdateCar(Car car)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("UPDATE car SET owner_id = ?, plate_number = ?, car_name = ?, brand = ?, car_type = ? "
                                        + "WHERE car_id = ?");
            pst.setInt(1,car.getOwnerID());
            pst.setString(2,car.getPlateNumber().toUpperCase(Locale.CANADA));
            pst.setString(3,car.getCarName());
            pst.setString(4,car.getBrand());
            pst.setString(5,car.getCarType());
            pst.setInt(6,car.getCarID());
            
            pst.executeUpdate();
            
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
        
    }
    
    public void DumpCar(int carID)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("DELETE FROM car WHERE car_id = ?");
            pst.setInt(1,carID);
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
            
    }
    
    public int getAgentIDByOwner(String owner)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        int id = 0;
        try{
            pst = myConn.prepareStatement("SELECT co.agent_id,owner_firstName,owner_lastName FROM carowner as co, agent as ag "
                                        + "WHERE ag.agent_id = co.agent_id ");
            rs = pst.executeQuery();
            
            while(rs.next()){
                String theOwner = String.format("%s %s",rs.getString("owner_firstName"),rs.getString("owner_lastName"));
                if(theOwner.equalsIgnoreCase(owner)){
                    id = rs.getInt("agent_id");
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
    
    public String getAgentNameByOwner(String owner)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        String agent = new String();
        try{
            pst = myConn.prepareStatement("SELECT agent_firstName,agent_lastName FROM agent as ag, carowner as co "
                                        + "WHERE ag.agent_id = co.agent_id AND CONCAT(agent_firstName,agent_lastName) LIKE '%"+owner+"%'");
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
    
    public String getCarOwner(String car)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        String owner = new String();
        try{
            pst = myConn.prepareStatement("SELECT owner_firstName, owner_lastName FROM car, carowner as co "
                                        + "WHERE car.car_id = co.car_id AND car_name = ?");
            pst.setString(1,car);
            rs = pst.executeQuery();
            
            while(rs.next()){
                owner = String.format("%s %s",rs.getString("owner_firstName"),rs.getString("owner_lastName"));
            }
            return owner;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return owner;
    }
   
    public int getCarID(String car_Name)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        int id = 0;
        try{
            pst = myConn.prepareStatement("SELECT car_id FROM car WHERE car_name = ?");
            pst.setString(1,car_Name);
            rs = pst.executeQuery();
            
            while(rs.next()){
                id = rs.getInt("car_id");
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
    
    public boolean checkIfCarExist(String car_name)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = myConn.prepareStatement("SELECT car_name FROM car");
            
            rs = pst.executeQuery();
            while(rs.next()){
                if(rs.getString("car_name").equalsIgnoreCase(car_name)){
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
    
    public ArrayList<Car> getAllCars()throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Car> list = new ArrayList<>();
        try{
            pst = myConn.prepareStatement("SELECT car_id, owner_firstName, owner_lastName, plate_number, car_name, brand, car_type "
                                            + "FROM car , carowner as co WHERE car.owner_id = co.owner_id");
            rs = pst.executeQuery();
            while(rs.next()){
                Car car = convertRowToCar(rs);
                list.add(car);
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
    
    public ArrayList<Car> searchCar(String search)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs =  null;
        ArrayList<Car> list = new ArrayList<>();
        try{
            pst = myConn.prepareStatement("SELECT car_id,owner_firstName,owner_lastName,plate_number,car_name,brand,car_type "
                                        + "FROM car, carowner as co WHERE car.owner_id = co.owner_id AND "
                                        + "CONCAT(owner_firstName,owner_lastName,plate_number,car_name,car_type) LIKE '%"+search+"%' ");
            rs = pst.executeQuery();
            
            while(rs.next()){
                Car car = convertRowToCar(rs);
                list.add(car);
                
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
    
    private Car convertRowToCar(ResultSet rs)throws SQLException{
        int carID = rs.getInt("car_id");
        String owner = String.format("%s %s",rs.getString("owner_firstName"),rs.getString("owner_lastName"));
        String plate = rs.getString("plate_number");
        String car_name = rs.getString("car_name");
        String brand = rs.getString("brand");
        String type = rs.getString("car_type");
        
        Car newCar = new Car(car_name,owner,brand,type,plate);
        newCar.setCarID(carID);
        
        return newCar;
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
