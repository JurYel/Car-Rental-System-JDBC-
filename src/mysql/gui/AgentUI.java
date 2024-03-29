/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql.gui;

/**
 *
 * @author Jur Yel
 */
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import mysql.core.Branch;
import mysql.dao.BranchDAO;
import mysql.core.Agent;
import mysql.dao.AgentDAO;
import mysql.dao.BookingsDAO;

public class AgentUI extends javax.swing.JFrame {

    /**
     * Creates new form AgentUI
     */
    private AgentDAO agentDAO;
    private BranchDAO branchDAO;
    private BookingsDAO bookDAO;
    private String accountID;
    public AgentUI(String accID) {
        try{
            bookDAO = new BookingsDAO();
            agentDAO = new AgentDAO();
            branchDAO = new BranchDAO();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        initComponents();
        setLocationRelativeTo(null);
        accountID = accID;
        populateTable();
        populateGUI();
    }
    
    public AgentUI(){
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox_Branch = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jtxt_FirstName = new javax.swing.JTextField();
        jtxt_LastName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtxt_Contact = new javax.swing.JTextField();
        jbtn_Update = new javax.swing.JButton();
        jbtn_Fire = new javax.swing.JButton();
        jbtn_Employ = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jtxt_Search = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_Agents = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Bebas Neue", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("A G E N T  D E T A I L S");

        jLabel2.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Branch:");

        jComboBox_Branch.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("First Name:");

        jtxt_FirstName.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N
        jtxt_FirstName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxt_FirstName.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jtxt_LastName.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N
        jtxt_LastName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxt_LastName.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Last Name:");

        jLabel5.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Contact Number:");

        jtxt_Contact.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N
        jtxt_Contact.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxt_Contact.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jtxt_Contact)
                    .addComponent(jtxt_LastName)
                    .addComponent(jComboBox_Branch, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtxt_FirstName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox_Branch, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtxt_FirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtxt_LastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtxt_Contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jbtn_Update.setBackground(new java.awt.Color(153, 153, 153));
        jbtn_Update.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N
        jbtn_Update.setText("Update Agent");
        jbtn_Update.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_UpdateActionPerformed(evt);
            }
        });

        jbtn_Fire.setBackground(new java.awt.Color(153, 153, 153));
        jbtn_Fire.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N
        jbtn_Fire.setText("Fire Agent");
        jbtn_Fire.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtn_Fire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_FireActionPerformed(evt);
            }
        });

        jbtn_Employ.setBackground(new java.awt.Color(153, 153, 153));
        jbtn_Employ.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N
        jbtn_Employ.setText("Employ Agent");
        jbtn_Employ.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtn_Employ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_EmployActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jbtn_Update)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jbtn_Fire, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtn_Employ, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtn_Employ, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtn_Fire, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 99, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 66, -1, -1));

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        jtxt_Search.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N
        jtxt_Search.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxt_Search.setText("Search Agent...");
        jtxt_Search.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jtxt_Search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jtxt_SearchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jtxt_SearchMouseExited(evt);
            }
        });
        jtxt_Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxt_SearchKeyTyped(evt);
            }
        });

        jtbl_Agents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtbl_Agents.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtbl_Agents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbl_AgentsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtbl_Agents);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtxt_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(211, 211, 211))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jtxt_Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 66, 625, 458));

        jLabel6.setFont(new java.awt.Font("Bebas Neue", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("R E G I S T E R E D  A G E N T S");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/icons8_home_page_30px.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxt_SearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxt_SearchMouseEntered
        if(jtxt_Search.getText().contains("Search Agent...")){
            jtxt_Search.setText("");
        }
    }//GEN-LAST:event_jtxt_SearchMouseEntered

    private void jtxt_SearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxt_SearchMouseExited
        if(jtxt_Search.getText().isEmpty()){
            jtxt_Search.setText("Search Agent...");
        }
    }//GEN-LAST:event_jtxt_SearchMouseExited

    private void jtxt_SearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_SearchKeyTyped
        String search = jtxt_Search.getText().trim();
        try{
            if(search.isEmpty()){
                ArrayList<Agent> list = agentDAO.getAllAgents();
                AgentTableModel model = new AgentTableModel(list);
                jtbl_Agents.setModel(model);
            }
            else{
                ArrayList<Agent> list = agentDAO.searchAgent(search);
                AgentTableModel model = new AgentTableModel(list);
                jtbl_Agents.setModel(model);
            }
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
    }//GEN-LAST:event_jtxt_SearchKeyTyped

    private void jbtn_EmployActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_EmployActionPerformed
        try{
            String branch = jComboBox_Branch.getSelectedItem().toString();
            String first = jtxt_FirstName.getText().trim();
            String last = jtxt_LastName.getText().trim();
            String contact = jtxt_Contact.getText().trim();
            
            if(first.isEmpty() || last.isEmpty() || contact.isEmpty()){
                JOptionPane.showMessageDialog(AgentUI.this,"Fill up all fields!","Employ Error",JOptionPane.ERROR_MESSAGE);
                reset();
            }
            else{
                if(agentDAO.checkIfAgent(String.format("%s %s",first,last))){
                    String agBranch = agentDAO.getBranchByAgent(String.format("%s %s",first,last));
                    
                    JOptionPane.showMessageDialog(AgentUI.this,String.format("%s %s",first,last) + " is already an agent of " + agBranch + "!","Employ Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int branchID = branchDAO.getBranchID(branch);
                    
                    Agent agent = new Agent(branch,first,last,contact);
                    agentDAO.AddNewAgent(agent, branchID);
                    
                    JOptionPane.showMessageDialog(AgentUI.this,String.format("%s %s",first,last) + " is now an agent of Branch " + branch + "!","Employ Success",JOptionPane.INFORMATION_MESSAGE);
                    populateTable();
                    reset();
                }
            }
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        
    }//GEN-LAST:event_jbtn_EmployActionPerformed

    private void jbtn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_UpdateActionPerformed
        int row = jtbl_Agents.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(AgentUI.this,"Select a row","Update Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            try{
                int agentID = Integer.parseInt(jtbl_Agents.getValueAt(row,-2).toString());
                String branch = jComboBox_Branch.getSelectedItem().toString();
                String first = jtxt_FirstName.getText().trim();
                String last = jtxt_LastName.getText().trim();
                String contact = jtxt_Contact.getText().trim();

                int branchID = branchDAO.getBranchID(branch);
                Agent agent = new Agent(branch,first,last,contact);
                agent.setAgentID(agentID);
                agentDAO.UpdateAgent(agent, branchID);

                JOptionPane.showMessageDialog(AgentUI.this,"Agent updated successfully!","Update Success",JOptionPane.INFORMATION_MESSAGE);
                populateTable();
                reset();
            }
            catch(Exception exc){
                exc.printStackTrace();
            }
        }
    }//GEN-LAST:event_jbtn_UpdateActionPerformed

    private void jbtn_FireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_FireActionPerformed
            int row = jtbl_Agents.getSelectedRow();
            if(row < 0){
                JOptionPane.showMessageDialog(AgentUI.this,"Select a row","Fire Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    String first = jtxt_FirstName.getText().trim();
                    String last = jtxt_LastName.getText().trim();
                    
                    int agentID = agentDAO.getAgentID(String.format("%s %s",first,last));
                    
                    if(bookDAO.checkIfAgentBooked(agentID)){
                        JOptionPane.showMessageDialog(AgentUI.this,"Unable to fire agent!\nA car is being booked/rented under this agent.","Fire Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        if(agentDAO.checkIfAgent(String.format("%s %s",first,last))){
                            if((JOptionPane.showConfirmDialog(AgentUI.this,
                                    "Are you sure to fire agent?","Confirm Action",
                                    JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)) == JOptionPane.YES_OPTION){
                                agentDAO.DeleteAgent(agentID);

                                JOptionPane.showMessageDialog(AgentUI.this,"Agent has been fired!","Fire Success",JOptionPane.INFORMATION_MESSAGE);
                                populateTable();
                                reset();

                            }
                        }
                    }
                    
                    
                    
                }
                catch(Exception exc){
                    exc.printStackTrace();
                }
            }
    }//GEN-LAST:event_jbtn_FireActionPerformed

    private void jtbl_AgentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbl_AgentsMouseClicked
        int row = jtbl_Agents.getSelectedRow();
        if((evt.getButton() == MouseEvent.BUTTON1) && (evt.getClickCount() == 1)){
            if(row >= 0){
                String branch = jtbl_Agents.getValueAt(row,0).toString();
                String first = jtbl_Agents.getValueAt(row,1).toString();
                String last = jtbl_Agents.getValueAt(row,2).toString();
                String contact = jtbl_Agents.getValueAt(row,3).toString();

                jComboBox_Branch.setSelectedItem(branch);
                jtxt_FirstName.setText(first);
                jtxt_LastName.setText(last);
                jtxt_Contact.setText(contact);
            }
        }
        else if((evt.getButton() == MouseEvent.BUTTON1) && (evt.getClickCount() == 2)){
            reset();
        }
    }//GEN-LAST:event_jtbl_AgentsMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        HomePageUI homeUI = new HomePageUI(accountID);
        setVisible(false);
        homeUI.setVisible(true);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        HomePageUI homeUI = new HomePageUI(accountID);
        homeUI.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AgentUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgentUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgentUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgentUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgentUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox_Branch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtn_Employ;
    private javax.swing.JButton jbtn_Fire;
    private javax.swing.JButton jbtn_Update;
    private javax.swing.JTable jtbl_Agents;
    private javax.swing.JTextField jtxt_Contact;
    private javax.swing.JTextField jtxt_FirstName;
    private javax.swing.JTextField jtxt_LastName;
    private javax.swing.JTextField jtxt_Search;
    // End of variables declaration//GEN-END:variables

    private void disableAll(){
        jComboBox_Branch.setEnabled(false);
        jtxt_FirstName.setEnabled(false);
        jtxt_LastName.setEnabled(false);
        jtxt_Contact.setEnabled(false);
    }
    
    private void reset(){
        jComboBox_Branch.setSelectedIndex(0);
        jtxt_FirstName.setText("");
        jtxt_LastName.setText("");
        jtxt_Contact.setText("");
    }
    
    private void populateGUI(){
        try{
            ArrayList<Branch> list = branchDAO.getAllBranches();
            
            for(int i = 0; i<list.size();i++){
                jComboBox_Branch.addItem(list.get(i).getBranchName());
            }
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
    }
    
    private void populateTable(){
        try{
            ArrayList<Agent> list = agentDAO.getAllAgents();
            AgentTableModel model = new AgentTableModel(list);
            jtbl_Agents.setModel(model);
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
    }
}