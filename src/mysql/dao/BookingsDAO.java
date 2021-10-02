/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql.dao;

/**
 *
 * @author Jur Yel
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.Date;
import mysql.core.Bookings;
import java.lang.Math;

public class BookingsDAO {
    private Connection myConn;
    private Bookings theBooking;
    
    public BookingsDAO()throws SQLException{
        try{
            Properties props = new Properties();
            props.load(new FileInputStream("sql/car_rentalDB.properties"));
            
            String user = props.getProperty("user");
            String pass = props.getProperty("password");
            String dburl = props.getProperty("dburl");
            
            myConn = DriverManager.getConnection(dburl,user,pass);
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
    }
    
    public void bookCar(Bookings book)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("INSERT INTO booking(customer_id,car_id,agent_id,date_booked) VALUES(?,?,?,?)");
            pst.setInt(1,book.getCustomerID());
            pst.setInt(2,book.getCarID());
            pst.setInt(3,book.getAgentID());
            pst.setTimestamp(4,convertUtilToTimestamp(book.getDateBooked()));
           
            
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
    }
    
    public void rentCar(Bookings book)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("INSERT INTO booking (customer_id,car_id,agent_id,date_booked,date_rented) VALUES(?,?,?,?,?)");
            pst.setInt(1,book.getCustomerID());
            pst.setInt(2,book.getCarID());
            pst.setInt(3,book.getAgentID());
            pst.setTimestamp(4,book.getTimestamp());
            pst.setTimestamp(5,new Timestamp(System.currentTimeMillis()));
            
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
    }
    
    public void returnCar(int bookID)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("DELETE FROM booking WHERE booking_id = ?");
            pst.setInt(1,bookID);
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
    }
    
    public void cancelBooking(int bookID)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("DELETE FROM booking WHERE booking_id = ?");
            pst.setInt(1, bookID);
            pst.executeUpdate();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
    }
    
    public void checkBookingDate()throws SQLException{
        PreparedStatement pst = null;
        java.util.Date curDate = new Date();
        try{
            ArrayList<Bookings> bookings = this.getAllBookings();
            for(int i = 0;i<bookings.size();i++){
                if(!(bookings.get(i).getLSDateBooked().equals(" "))){
                    int curDay = (int)curDate.getTime();
                    int dateBooked = (int)bookings.get(i).getDateBooked().getTime();
                    int curDayNum = convertMillisecondsToDays(curDay);
                    int dateBookedNum = convertMillisecondsToDays(dateBooked);

                    if((curDayNum - dateBookedNum) >= 2){
                        int bookID = bookings.get(i).getBookingID();
                        this.cancelBooking(bookID);
                    }
                }
                
            }
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,null);
        }
    }
    
    public int checkRentalDate()throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        java.util.Date curDate = new Date();
        int cars = 0;
        try{
            ArrayList<Bookings> bookings = this.getAllBookings();
            for(int i = 0;i<bookings.size();i++){
                if(!(bookings.get(i).getLSDateRented().equals(" "))){
                    int curDay = (int)curDate.getTime();
                    int dateRented = (int)bookings.get(i).getDateRented().getTime();
                    int curDayNum = convertMillisecondsToDays(curDay);
                    int dateRentedNum = convertMillisecondsToDays(dateRented);

                    if((curDayNum - dateRentedNum) >= 5){
                        cars++;
                    }
                }
                
            }
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return cars;
    }
    
    public boolean checkIfAgentBooked(int agentID)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = myConn.prepareStatement("SELECT agent_id FROM booking");
            rs = pst.executeQuery();
            
            while(rs.next()){
                if(rs.getInt("agent_id") == agentID){
                    return true;
                }
            }
            return false;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return false;
    }
    
    public boolean checkIfCarBooked(int carID)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = myConn.prepareStatement("SELECT car_id FROM booking");
            rs = pst.executeQuery();
            
            while(rs.next()){
                if(rs.getInt("car_id") == carID){
                    return true;
                }
            }
            return false;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return false;
    }
    
    public boolean checkIfCarRented(int carID)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = myConn.prepareStatement("SELECT car_id FROM booking");
            rs = pst.executeQuery();
            
            while(rs.next()){
                if(rs.getInt("car_id") == carID){
                    return true;
                }
            }
            return false;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return false;
    }
    
    public boolean checkIfCustomerRented(int customer_id)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = myConn.prepareStatement("SELECT customer_id FROM booking");
            rs = pst.executeQuery();
            
            while(rs.next()){
                if(rs.getInt("customer_id") == customer_id){
                    return true;
                }
            }
            return false;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return false;
    }
    
    public boolean checkIfCustomerBooked(int customerID)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = myConn.prepareStatement("SELECT customer_id FROM booking");
            rs = pst.executeQuery();
            
            while(rs.next()){
                if(rs.getInt("customer_id") == customerID){
                    return true;
                }
            }
            return false;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return false;
    }
    
    public int getBookingIDByCustomer(int customerID)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        int id = 0;
        try{
            pst = myConn.prepareStatement("SELECT booking_id FROM booking WHERE customer_id = ?");
            pst.setInt(1,customerID);
            rs = pst.executeQuery();
            
            while(rs.next()){
                id = rs.getInt("booking_id");
            }
            return id;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return id;
    }
    
    public Timestamp getTimestampByCustomer(int customerID)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try{
            pst = myConn.prepareStatement("SELECT date_booked FROM booking WHERE customer_id = ?");
            pst.setInt(1,customerID);
            rs = pst.executeQuery();
            
            while(rs.next()){
                timestamp = rs.getTimestamp("date_booked");
            }
            return timestamp;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return timestamp;
    }
    
    public String getCarBookedByCustomer(int customerID)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        String car = new String();
        try{
            pst = myConn.prepareStatement("SELECT car_name FROM car,booking as bk "
                                        + "WHERE (car.car_id,customer_id) = (bk.car_id,?)");
            pst.setInt(1,customerID);
            rs = pst.executeQuery();
            
            while(rs.next()){
                car = rs.getString("car_name");
            }
            return car;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return car;
    }
    
    public String getDateBookedByCustomer(int customerID)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        String timestamp = new String();
        try{
            pst = myConn.prepareStatement("SELECT DATE_FORMAT(date_booked, '%M %e, %Y - %l:%i %p') as date_booked FROM booking "
                                        + "WHERE customer_id = ?");
            pst.setInt(1,customerID);
            rs = pst.executeQuery();
            
            while(rs.next()){
                timestamp = rs.getString("date_booked");
            }
            return timestamp;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return timestamp;
    }
    
    public String getCarRentedByCustomer(int customerID)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        String car = new String();
        try{
            pst = myConn.prepareStatement("SELECT car_name FROM car,booking as bk "
                                        + "WHERE (car.car_id,customer_id) = (bk.car_id,?)");
            pst.setInt(1,customerID);
            rs = pst.executeQuery();
            
            while(rs.next()){
                car = rs.getString("car_name");
            }
            return car;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return car;
    }
    
    public String getDateRentedByCustomer(int customerID)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        String timestamp = new String();
        try{
            pst = myConn.prepareStatement("SELECT DATE_FORMAT(date_rented, '%M %e, %Y - %l:%i %p') as date_rented FROM booking "
                                        + "WHERE customer_id = ?");
            pst.setInt(1,customerID);
            rs = pst.executeQuery();
            
            while(rs.next()){
                timestamp = rs.getString("date_rented");
            }
            return timestamp;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return timestamp;
    }
    
    public ArrayList<Bookings> getAllBookings()throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Bookings> list = new ArrayList<>();
        try{
            pst = myConn.prepareStatement("SELECT booking_id, first_name,last_name,agent_firstName,agent_lastName,car_name,date_rented,date_booked "
                                        + "FROM customer as cu, agent as ag, car, booking as bk "
                                        + "WHERE (bk.customer_id,bk.agent_id,bk.car_id) = (cu.customer_id,ag.agent_id,car.car_id)");
            rs = pst.executeQuery();
            
            while(rs.next()){
                Bookings book = convertRowToBooking(rs);
                list.add(book);
            }
            return list;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return list;
    }
    
    public ArrayList<Bookings> searchBooking(String search)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Bookings> list = new ArrayList<>();
        try{
            pst = myConn.prepareStatement("SELECT booking_id, first_name,last_name,agent_firstName,agent_lastName,car_name,date_rented,date_booked "
                                        + "FROM customer as cu, agent as ag, car, booking as bk "
                                        + "WHERE (bk.customer_id,bk.agent_id,bk.car_id) = (cu.customer_id,ag.agent_id,car.car_id) "
                                        + "AND CONCAT(first_name,last_name,agent_firstName,agent_lastName,car_name) LIKE '%"+search+"%' ");
            rs = pst.executeQuery();
            
            while(rs.next()){
                Bookings book = convertRowToBooking(rs);
                list.add(book);
            }
            return list;
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            close(pst,rs);
        }
        return list;
    }
    
    private Date convertTimestampToUtil(Timestamp timestamp){
        Date uDate = new Date(timestamp.getTime());
        return uDate;
    }
    
    private Timestamp convertUtilToTimestamp(Date uDate){
        Timestamp timestamp = new Timestamp(uDate.getTime());
        return timestamp;
    }
    
    private int convertMillisecondsToDays(int milliseconds){
        int days = (int) Math.ceil(milliseconds/(1000*60*60*24))+1;
        return days;
    }
    
    private Bookings convertRowToBooking(ResultSet rs)throws SQLException{
        int bookingID = rs.getInt("booking_id");
        String agent = String.format("%s %s",rs.getString("agent_firstName"),rs.getString("agent_lastName"));
        String car = rs.getString("car_name");
        String customer = String.format("%s %s",rs.getString("first_name"),rs.getString("last_name"));
//        Date dateRent = convertTimestampToUtil(rs.getTimestamp("date_rented"));
//        Date dateBooked = convertTimestampToUtil(rs.getTimestamp("date_booked"));
        
        Date dateRent = new Date();
        Date dateBooked = new Date();
        boolean rented = true;
        boolean booked = true;
        if(rs.getTimestamp("date_rented") != null){
            dateRent = convertTimestampToUtil(rs.getTimestamp("date_rented"));
            rented = true;
        }
        else{
            rented = false;
        }
        
        if(rs.getTimestamp("date_booked")!=null){
            dateBooked = convertTimestampToUtil(rs.getTimestamp("date_booked"));
            booked = true;
        }
        else{
            booked = false;
        }
        
        if((booked == true) && (rented == false)){
            theBooking = new Bookings(agent,car,customer,dateBooked,null);
            theBooking.setBookingID(bookingID);
        }
        else if((booked == false) && (rented == true)){
            theBooking = new Bookings(agent,car,customer,null,dateRent);
            theBooking.setBookingID(bookingID);
        }
        else if((booked == true) && (rented == true)){
            theBooking = new Bookings(agent,car,customer,dateBooked,dateRent);
            theBooking.setBookingID(bookingID);
        }
//        
//        theBooking = new Bookings(agent,car,customer,dateRent,dateBooked);
//        theBooking.setBookingID(bookingID);

        return theBooking;
    }
    
    private void close(PreparedStatement pst, ResultSet rs)throws SQLException{
        if(pst!=null){
            pst.close();
        }
        if(rs!=null){
            rs.close();
        }
    }
}
