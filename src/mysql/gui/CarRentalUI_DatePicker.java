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
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JOptionPane;
import mysql.core.Car;
import mysql.core.Bookings;
import mysql.core.BookingsHistory;
import mysql.dao.BookingsHistoryDAO;
import mysql.dao.BookingsDAO;

public class CarRentalUI_DatePicker extends javax.swing.JDialog {

    /**
     * Creates new form CarRentalUI_DatePicker
     */
     private Car carBooked;
     private Bookings booking;
     private BookingsDAO bookDAO;
     private BookingsHistoryDAO historyDAO;
     private CarRentalUI_RentACar rentUI;
     private String accountID;
     private String customer;
     
    public CarRentalUI_DatePicker(String accID,String cust,Car car,Bookings book,CarRentalUI_RentACar rent){
        try{
            bookDAO = new BookingsDAO();
            historyDAO = new BookingsHistoryDAO();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        initComponents();
        setLocationRelativeTo(rentUI);
        carBooked = car;
        rentUI = rent;
        booking = book;
        accountID = accID;
        customer = cust;
        populateGUI(car);
    }
    
    public CarRentalUI_DatePicker(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        dcc_Date = new datechooser.beans.DateChooserCombo();
        jbtn_Book = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxt_Car = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox_Brand = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboBox_Type = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N
        jLabel1.setText("Car:");

        dcc_Date.setCalendarPreferredSize(new java.awt.Dimension(350, 250));
        dcc_Date.setFormat(2);
        dcc_Date.setFieldFont(new java.awt.Font("Josefin Sans", java.awt.Font.PLAIN, 14));

        jbtn_Book.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N
        jbtn_Book.setText("Book Car");
        jbtn_Book.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtn_Book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_BookActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Bebas Neue", 0, 18)); // NOI18N
        jLabel2.setText("B O O K  C A R");

        jLabel4.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N
        jLabel4.setText("Date:");

        jtxt_Car.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N
        jtxt_Car.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxt_Car.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jtxt_Car.setEnabled(false);

        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("Cancel");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N
        jLabel5.setText("Brand:");

        jComboBox_Brand.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N
        jComboBox_Brand.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Toyota", "Suzuki", "Nissan", "Mazda", "Mitsubishi", "BMW", "Honda", "Ford" }));
        jComboBox_Brand.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N
        jLabel6.setText("Type:");

        jComboBox_Type.setFont(new java.awt.Font("Josefin Sans", 0, 14)); // NOI18N
        jComboBox_Type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sedan", "Crossover", "SUV", "Hatchback", "Pick-up", "CUV", "Roadster" }));
        jComboBox_Type.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(120, 120, 120))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(12, 12, 12)
                                .addComponent(jtxt_Car, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox_Brand, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox_Type, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(84, 84, 84))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtn_Book)
                                .addGap(61, 61, 61))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dcc_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(46, 46, 46))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxt_Car, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_Brand, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_Type, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcc_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jbtn_Book)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        setVisible(false);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jbtn_BookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_BookActionPerformed
        try{
            Date bookDate = dcc_Date.getSelectedDate().getTime();
            Date curDate = new Date();
            booking.setDateBooked(bookDate);
            
            if(bookDate.getTime() < curDate.getTime()){
                JOptionPane.showMessageDialog(CarRentalUI_DatePicker.this,"Please pick a viable date","Book Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                bookDAO.bookCar(booking);
            
            
                        BookingsHistory history = new BookingsHistory(null,"Booked Car",customer,carBooked.getCarName(),null);
                        history.setAccountID(accountID);
                        historyDAO.recordAction(history);
                        setVisible(false);
                        JOptionPane.showMessageDialog(rentUI,
                                        "Car Details: \n\n"
                                      + "\tCar Name: " + carBooked.getCarName() + "\n"
                                      + "\tPlate Number: " + carBooked.getPlateNumber() + "\n"
                                      + "\tDate Booked: " + bookDate.toLocaleString() + "\n\n"
                                      + "Car has been booked successfully!\n"
                                      + "Note: You have two(2) days before the booking's cancelled.","Booking Success",JOptionPane.INFORMATION_MESSAGE);
            }
            
            
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
    }//GEN-LAST:event_jbtn_BookActionPerformed

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
            java.util.logging.Logger.getLogger(CarRentalUI_DatePicker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CarRentalUI_DatePicker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CarRentalUI_DatePicker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CarRentalUI_DatePicker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CarRentalUI_DatePicker dialog = new CarRentalUI_DatePicker(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dcc_Date;
    private javax.swing.JComboBox<String> jComboBox_Brand;
    private javax.swing.JComboBox<String> jComboBox_Type;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton jbtn_Book;
    private javax.swing.JTextField jtxt_Car;
    // End of variables declaration//GEN-END:variables

    private void populateGUI(Car carBk){
        jtxt_Car.setText(carBk.getCarName());
        jComboBox_Brand.setSelectedItem(carBk.getBrand());
        jComboBox_Type.setSelectedItem(carBk.getCarType());
    }
    
    private Timestamp convertUtilToTimestamp(Date uDate){
        Timestamp timestamp = new Timestamp(uDate.getTime());
        return timestamp;
    }
    
}
