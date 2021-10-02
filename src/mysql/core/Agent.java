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
public class Agent {
    private int agent_id;
    private String BranchName;
    private String FirstName;
    private String LastName;
    private String ContactNumber;
    
    public Agent(String branch,String first,String last,String contact){
        super();
        this.BranchName = branch;
        this.FirstName = first;
        this.LastName = last;
        this.ContactNumber = contact;
    }
    
    public int getAgentID(){
        return agent_id;
    }
    
    public String getBranchName(){
        return BranchName;
    }
    
    public String getFirstName(){
        return FirstName;
    }
    
    public String getLastName(){
        return LastName;
    }
    
    public String getContactNumber(){
        return ContactNumber;
    }
    
    public void setAgentID(int id){
        this.agent_id = id;
    }
}
