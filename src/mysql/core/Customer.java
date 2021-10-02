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
public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private String contactNum;
    
    public Customer(String first,String last,String contact){
        super();
        this.firstName = first;
        this.lastName = last;
        this.contactNum = contact;
    }
    
    public int getCustomerID(){
        return customerID;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getContactNumber(){
        return contactNum;
    }
    
    public void setCustomerID(int id){
        this.customerID = id;
    }
}
