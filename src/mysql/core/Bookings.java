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
import java.sql.Timestamp;
public class Bookings {
    private int booking_id;
    private int customer_id;
    private int agent_id;
    private int car_id;
    private String customerName;
    private String agentName;
    private String carName;
    private Date dateRented;
    private Date dateBooked;
    private String rentDate;
    private String bookDate;
    private Timestamp timestamp;
    
    public Bookings(String agent,String car,String cust,Date booked,Date rent){
        super();
        this.customerName = cust;
        this.agentName = agent;
        this.carName = car;
        this.dateRented = rent;
        this.dateBooked = booked;
    }
    
    public int getBookingID(){
        return booking_id;
    }
    
    public int getCustomerID(){
        return customer_id;
    }
    
    public int getAgentID(){
        return agent_id;
    }
    
    public int getCarID(){
        return car_id;
    }
    
    public String getCustomerName(){
        return customerName;
    }
    
    public String getAgentName(){
        return agentName;
    }
    
    public String getCarName(){
        return carName;
    }
    
    public Date getDateRented(){
        return dateRented;
    }
    
    public Date getDateBooked(){
        return dateBooked;
    }
    
    public String getStringRentDate(){
        return rentDate;
    }
    
    public String getStringBookDate(){
        return bookDate;
    }
    
    public String getLSDateRented(){
        if(dateRented == null){
            return " ";
        }
        else{
            
            return dateRented.toLocaleString();
        }
    }
    
    public String getLSDateBooked(){
        if(dateBooked == null){
            return " ";
        }
        else{
            return dateBooked.toLocaleString();
        }
    }
    
    public Timestamp getTimestamp(){
        return timestamp;
    }
    
    public void setBookingID(int id){
        this.booking_id = id;
    }
    
    public void setAgentID(int id){
        this.agent_id = id;
    }
    
    public void setCustomerID(int id){
        this.customer_id = id;
    }
    
    public void setCarID(int id){
        this.car_id = id;
    }
    
    public void setDateBooked(Date book){
        this.dateBooked = book;
    }
    
    public void setStringRentDate(String rent){
        this.rentDate = rent;
    }
    
    public void setStringBookDate(String book){
        this.bookDate = book;
    }
    
    public void setTimestamp(Timestamp ts){
        this.timestamp = ts;
    }
}
