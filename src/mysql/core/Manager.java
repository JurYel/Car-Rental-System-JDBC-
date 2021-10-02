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
public class Manager {
    private int manager_id;
    private String Branch;
    private String Mngr_LastName;
    private String Mngr_FirstName;
    private String ContactNumber;
    
    public Manager(String branch,String last,String first,String contact){
        super();
        this.Branch = branch;
        this.Mngr_LastName = last;
        this.Mngr_FirstName = first;
        this.ContactNumber = contact;
    }
    
    public int getManagerID(){
        return manager_id;
    }
    
    public String getBranchName(){
        return Branch;
    }
    
    public String getManagerLastName(){
        return Mngr_LastName;
    }
    
    public String getManagerFirstName(){
        return Mngr_FirstName;
    }
    
    public String getContactNumber(){
        return ContactNumber;
    }
    
    public void setManagerID(int id){
        this.manager_id = id;
    }
}
