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
public class Branch {
    private int branchID;
    private String branchName;
    private String branchManagerFirst;
    private String branchManagerLast;
    private String contactNumber;
    
    public Branch(String brName, String first,String last, String cntNumber){
        super();
        this.branchName = brName;
        this.branchManagerFirst = first;
        this.branchManagerLast = last;
        this.contactNumber = cntNumber;
    }
    
    public String getBranchName(){
        return branchName;
    }
    
    public String getBranchManagerFirst(){
        return branchManagerFirst;
    }
    
    public String getBranchManagerLast(){
        return branchManagerLast;
    }
    
    public String getContactNumber(){
        return contactNumber;
    }
    
    public int getBranchID(){
        return branchID;
    }
    
    public void setBranchID(int id){
        this.branchID = id;
    }
}
