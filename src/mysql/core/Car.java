/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql.core;

/**
 *
 * @author Jur Yel
 */
public class Car {
    private int car_id;
    private int ownerID;
    private String CarOwner;
    private String PlateNumber;
    private String CarName;
    private String Brand;
    private String CarType;
    
    public Car(String name,String owner,String brand,String type,String plate){
        super();
        this.CarOwner = owner;
        this.PlateNumber = plate;
        this.CarName = name;
        this.Brand = brand;
        this.CarType = type;
    }
    
    public int getCarID(){
        return car_id;
    }
    
    public int getOwnerID(){
        return ownerID;
    }
    
    public String getCarOwner(){
        return CarOwner;
    }
    
    public String getPlateNumber(){
        return PlateNumber;
    }
    
    public String getCarName(){
        return CarName;
    }
    
    public String getBrand(){
        return Brand;
    }
    
    public String getCarType(){
        return CarType;
    }
    
    public void setCarID(int id){
        this.car_id = id;
    }
    
    public void setOwnerID(int id){
        this.ownerID = id;
    }
}
