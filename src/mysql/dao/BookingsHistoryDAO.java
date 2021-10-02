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
import mysql.core.BookingsHistory;

public class BookingsHistoryDAO {
    private Connection myConn;
    
    public BookingsHistoryDAO()throws SQLException {
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
    
    public void recordAction(BookingsHistory bh)throws SQLException{
        PreparedStatement pst = null;
        try{
            pst = myConn.prepareStatement("INSERT INTO booking_history (account_id,action,customer,car,action_date) VALUES(?,?,?,?,?)");
            pst.setString(1,bh.getAccountID());
            pst.setString(2,bh.getActionPerformed());
            pst.setString(3,bh.getCustomerName());
            pst.setString(4,bh.getCarName());
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
    
    public ArrayList<BookingsHistory> getAllHistory()throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<BookingsHistory> list = new ArrayList<>();
        try{
            pst = myConn.prepareStatement("SELECT history_id,bh.account_id,action,acc.first_name,acc.last_name,customer,car, "
                                            + "DATE_FORMAT(action_date,'%M %e, %Y - %l:%i %p') as action_date FROM booking_history as bh,accounts as acc "
                                            + "WHERE bh.account_id = acc.account_id");
            rs = pst.executeQuery();
            
            while(rs.next()){
                BookingsHistory bookHistory = convertRowToHistory(rs);
                list.add(bookHistory);
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
    
    public ArrayList<BookingsHistory> searchHistory(String search)throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<BookingsHistory> list = new ArrayList<>();
        try{
            pst = myConn.prepareStatement("SELECT history_id,bh.account_id,action,acc.first_name,acc.last_name,customer,car, "
                                        + "DATE_FORMAT(action_date,'%M %e, %Y - %l:%i %p') as action_date FROM booking_history as bh, accounts as acc "
                                        + "WHERE bh.account_id = acc.account_id "
                                        + "AND CONCAT(acc.first_name,acc.last_name,customer,car) LIKE '%"+search+"%'");
            rs = pst.executeQuery();
            
            while(rs.next()){
                BookingsHistory bookHistory = convertRowToHistory(rs);
                list.add(bookHistory);
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
    
    private java.util.Date convertTimestampToUtil(Timestamp timestamp){
        java.util.Date uDate = new java.util.Date(timestamp.getTime());
        return uDate;
    }
    
    private BookingsHistory convertRowToHistory(ResultSet rs)throws SQLException{
        int historyID = rs.getInt("history_id");
        String accID = rs.getString("bh.account_id");
        String empName = String.format("%s %s",rs.getString("acc.first_name"),rs.getString("acc.last_name"));
        String action = rs.getString("action");
        String customer = rs.getString("customer");
        String car = rs.getString("car");
//        java.util.Date actionDate = convertTimestampToUtil(rs.getTimestamp("action_date"));
        String datePrfmd = rs.getString("action_date");
        
        BookingsHistory bookHistory = new BookingsHistory(empName,action,customer,car,null);
        bookHistory.setHistoryID(historyID);
        bookHistory.setAccountID(accID);
        bookHistory.setDatePerformed(datePrfmd);
        
        return bookHistory;
    }
    
    private void close(PreparedStatement pst,ResultSet rs)throws SQLException{
        if(pst != null){
            pst.close();
        }
        if(rs != null){
            rs.close();
        }
    }
}
