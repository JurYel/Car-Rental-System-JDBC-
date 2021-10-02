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
public class CarOwner {
    private int owner_id;
    private int agent_id;
    private int branch_id;
    private int owned_cars;
    private String Agent;
    private String Branch;
    private String FirstName;
    private String LastName;
    private String ContactNumber;
    
    public CarOwner(String branch,String agent,
                String first,String last,String contact){
        super();
        this.Agent = agent;
        this.Branch = branch;
        this.FirstName = first;
        this.LastName = last;
        this.ContactNumber = contact;
    }
    
    public int getOwnerID(){
        return owner_id;
    }
    
    public int getAgentID(){
        return agent_id;
    }
    
    public int getBranchID(){
        return branch_id;
    }
    
    public String getBranch(){
        return Branch;
    }
    
    public int getOwnedCars(){
        return owned_cars;
    }
    
    public String getAgent(){
        return Agent;
    }
    
    public String getOwnerFirstName(){
        return FirstName;
    }
    
    public String getOwnerLastName(){
        return LastName;
    }
    
    public String getContactNumber(){
        return ContactNumber;
    }
    
    public void setOwnedCrs(int owned){
        this.owned_cars = owned;
    }
    
    public void setOwnerID(int id){
        this.owner_id = id;
    }
    
    public void setAgentID(int id){
        this.agent_id = id;
    }
    
    public void setBranchID(int id){
        this.branch_id = id;
    }
}
    
