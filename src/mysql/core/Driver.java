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
public class Driver {
    private int driver_id;
    private int agent_id;
    private int branch_id;
    private int car_id;
    private String Agent;
    private String CarName;
    private String BranchName;
    private String Driver_First;
    private String Driver_Last;
    private String ContactNumber;

    public Driver(String branch,String agent,String car,
            String first,String last,String contact){
        super();
        this.Agent = agent;
        this.CarName = car;
        this.BranchName = branch;
        this.Driver_First = first;
        this.Driver_Last = last;
        this.ContactNumber = contact;
    }
    
    public int getDriverID(){
        return driver_id;
    }
    
    public int getAgentID(){
        return agent_id;
    }
    
    public int getBranchID(){
        return branch_id;
    }
    
    public int getCarID(){
        return car_id;
    }
    
    public String getAgent(){
        return Agent;
    }
    
    public String getCarName(){
        return CarName;
    }
    
    public String getBranchName(){
        return BranchName;
    }
    
    public String getDriverFirstName(){
        return Driver_First;
    }
    
    public String getDriverLastName(){
        return Driver_Last;
    }
    
    public String getContactNumber(){
        return ContactNumber;
    }
    
    public void setDriverID(int id){
        this.driver_id = id;
    }
    
    public void setAgentID(int id){
        this.agent_id = id;
    }
    
    public void setCarID(int id){
        this.car_id = id;
    }
    
    public void setBranchID(int id){
        this.branch_id = id;
    }
}
