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
public class Account {
    private String accountID;
    private String firstName;
    private String lastName;
    private String password;
    
    public Account(String id,String first,String last,String pass){
        super();
        this.accountID = id;
        this.firstName= first;
        this.lastName = last;
        this.password = pass;
    }
    
    public String getAccountID(){
        return accountID;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setAccountID(String id){
        this.accountID = id;
    }
}
