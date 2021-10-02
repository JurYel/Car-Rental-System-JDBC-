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
import java.util.Date;
public class BookingsHistory {
    private int historyID;
    private String accountID;
    private String employeeName;
    private String action;
    private String customerName;
    private String carName;
    private Date actionDate;
    private String datePerformed;
    
    public BookingsHistory(String empName,String act,String cust,String car,Date date){
        super();
        this.employeeName = empName;
        this.action = act;
        this.customerName = cust;
        this.carName = car;
        this.actionDate = date;
    }
    
    public void setHistoryID(int id){
        this.historyID = id;
    }
    
    public void setAccountID(String id){
        this.accountID = id;
    }
    
    public void setDatePerformed(String prfd){
        this.datePerformed = prfd;
    }
    
    public int getHistoryID(){
        return historyID;
    }
    
    public String getAccountID(){
        return accountID;
    }
    
    public String getEmployeeName(){
        return employeeName;
    }
    
    public String getActionPerformed(){
        return action;
    }
    
    public String getCustomerName(){
        return customerName;
    }
    
    public String getCarName(){
        return carName;
    }
    
    public String getDatePerformed(){
        return datePerformed;
    }
    
    public String getHistoryDate(){
        return actionDate.toLocaleString();
    }
}
