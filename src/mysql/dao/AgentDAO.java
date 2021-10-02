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
import mysql.core.Agent;

public class AgentDAO {
     private Connection myConn;
     
     public AgentDAO()throws SQLException{
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
     
     public void AddNewAgent(Agent agent, int branchID)throws SQLException{
         PreparedStatement pst = null;
         try{
             pst = myConn.prepareStatement("INSERT INTO agent (branch_id,agent_firstName,agent_lastName,contact_number) "
                                        + "VALUES(?,?,?,?)");
             pst.setInt(1,branchID);
             pst.setString(2,agent.getFirstName());
             pst.setString(3,agent.getLastName());
             pst.setString(4,agent.getContactNumber());
             
             pst.executeUpdate();
         }
         catch(Exception exc){
             exc.printStackTrace();
         }
         finally{
             close(pst,null);
         }
     }
     
     public void UpdateAgent(Agent agent, int branchID)throws SQLException{
         PreparedStatement pst = null;
         try{
             pst = myConn.prepareStatement("UPDATE agent SET branch_id = ? , agent_firstName = ? , agent_lastName = ? , contact_number = ? "
                                            + "WHERE agent_id = ?");
             pst.setInt(1, branchID);
             pst.setString(2,agent.getFirstName());
             pst.setString(3,agent.getLastName());
             pst.setString(4,agent.getContactNumber());
             pst.setInt(5,agent.getAgentID());
             
             pst.executeUpdate();
         }
         catch(Exception exc){
             exc.printStackTrace();
         }
         finally{
             close(pst,null);
         }
     }
     
     public void DeleteAgent(int agent_id)throws SQLException{
         PreparedStatement pst = null;
         try{
             pst = myConn.prepareStatement("DELETE FROM agent WHERE agent_id = ?");
             pst.setInt(1,agent_id);
             pst.executeUpdate();
         }
         catch(Exception exc){
             exc.printStackTrace();
         }
         finally{
             close(pst,null);
         }
     }
     
     public int getAgentID(String agent)throws SQLException{
         PreparedStatement pst = null;
         ResultSet rs = null;
         int id = 0;
         try{
             pst = myConn.prepareStatement("SELECT agent_id,agent_firstName,agent_lastName FROM agent ");
             
             rs = pst.executeQuery();
             
             while(rs.next()){
                 String agentName = String.format("%s %s",rs.getString("agent_firstName"),rs.getString("agent_lastName"));
                if(agentName.equalsIgnoreCase(agent)){
                    id = rs.getInt("agent_id");
                }
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
     
     public String getBranchByAgent(String agent)throws SQLException{
         PreparedStatement pst = null;
         ResultSet rs = null;
         String branch = new String();
         try{
             pst = myConn.prepareStatement("SELECT branch_name FROM agent as ag, branch as br "
                                            + "WHERE CONCAT(agent_firstName,agent_lastName) LIKE '%"+agent+"%' "
                                            + "AND ag.branch_id = br.branch_id");
             rs = pst.executeQuery();
             
             while(rs.next()){
                 branch = rs.getString("branch_name");
             }
             return branch;
         }
         catch(Exception exc){
             exc.printStackTrace();
         }
         finally{
             close(pst,rs);
         }
         return branch;
     }
     
     public ArrayList<String> getAgentsByBranch(String branch)throws SQLException{
         PreparedStatement pst = null;
         ResultSet rs = null;
         ArrayList<String> list = new ArrayList<>();
         try{
             pst = myConn.prepareStatement("SELECT agent_firstName,agent_lastName FROM agent as ag, branch as br "
                                            + "WHERE branch_name LIKE '%"+branch+"%' AND ag.branch_id = br.branch_id");
             rs = pst.executeQuery();
             
             while(rs.next()){
                 String agent = String.format("%s %s",rs.getString("agent_firstName"),rs.getString("agent_lastName"));
                 list.add(agent);
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
     
     public boolean checkIfAgent(String agent)throws SQLException{
         PreparedStatement pst = null;
         ResultSet rs = null;
         try{
             pst = myConn.prepareStatement("SELECT agent_firstName, agent_lastName FROM agent");
             rs = pst.executeQuery();
             
             while(rs.next()){
                 String agentName = rs.getString("agent_firstName") + " " + rs.getString("agent_lastName");
                 if(agentName.equalsIgnoreCase(agent)){
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
     
     public ArrayList<Agent> getAllAgents()throws SQLException{
         PreparedStatement pst = null;
         ResultSet rs = null;
         ArrayList<Agent> list = new ArrayList<>();
         try{
             pst = myConn.prepareStatement("SELECT agent_id, branch_name, agent_lastName, agent_firstName, ag.contact_number "
                                        + " FROM agent as ag, branch as br WHERE ag.branch_id = br.branch_id");
             rs = pst.executeQuery();
             
             while(rs.next()){
                 Agent agent = convertRowToAgent(rs);
                 list.add(agent);
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
     
     public ArrayList<Agent> searchAgent(String search)throws SQLException{
         PreparedStatement pst = null;
         ResultSet rs = null;
         ArrayList<Agent> list = new ArrayList<>();
         try{
             pst = myConn.prepareStatement("SELECT agent_id, branch_name, agent_lastName, agent_firstName, ag.contact_number "
                                           + "FROM agent as ag, branch as br WHERE ag.branch_id = br.branch_id "
                                            + "AND CONCAT(branch_name,agent_lastName,agent_firstName) LIKE '%"+search+"%'");
             rs = pst.executeQuery();
             
             while(rs.next()){
                 Agent agent = convertRowToAgent(rs);
                 list.add(agent);
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
     
     private Agent convertRowToAgent(ResultSet rs)throws SQLException{
         int agentID = rs.getInt("agent_id");
         String branch = rs.getString("branch_name");
         String agentLast = rs.getString("agent_lastName");
         String agentFirst = rs.getString("agent_firstName");
         String contact = rs.getString("ag.contact_number");
         
         Agent agent = new Agent(branch,agentFirst,agentLast,contact);
         agent.setAgentID(agentID);
         
         return agent;
     }
     
     private void close(PreparedStatement pst, ResultSet rs)throws SQLException{
         if(pst!=null){
             pst.close();
         }
         if(rs!= null){
             rs.close();
         }
     }
}
