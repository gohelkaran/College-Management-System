/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fees_management_system;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;



/**
 *
 * @author Karan Gohel
 */
public class Signup_Page extends javax.swing.JFrame {

    String fname,lname,uname,password,con_pass,contact_no;
    Date dob;
    int Id=0;
    
    public Signup_Page() {
        initComponents();
        
        
    }
    public int getId()
    {
        ResultSet rs=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management","root","");
            Statement st=Con.createStatement();
            String sql="select max(id) from signup";
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                Id=rs.getInt(1);
                Id++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return Id;
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_contactno = new javax.swing.JTextField();
        txt_firstname = new javax.swing.JTextField();
        txt_lastname = new javax.swing.JTextField();
        txt_username = new javax.swing.JTextField();
        txt_con_password = new javax.swing.JPasswordField();
        txt_password = new javax.swing.JPasswordField();
        txt_dob = new com.toedter.calendar.JDateChooser();
        btn_login = new javax.swing.JButton();
        btn_signup = new javax.swing.JButton();
        lbl_contactno_error = new javax.swing.JLabel();
        lbl_password_error = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 45)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Sign Up");
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 130));

        jPanel2.setBackground(new java.awt.Color(0, 152, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Contact No :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Firstname :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Lastname :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Username :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Password :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Conform Password :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("D.O.B :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, -1, -1));

        txt_contactno.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_contactno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_contactnoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_contactnoKeyReleased(evt);
            }
        });
        jPanel2.add(txt_contactno, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, 230, -1));

        txt_firstname.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jPanel2.add(txt_firstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 230, -1));

        txt_lastname.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_lastname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_lastnameActionPerformed(evt);
            }
        });
        jPanel2.add(txt_lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 230, -1));

        txt_username.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jPanel2.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 230, -1));

        txt_con_password.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jPanel2.add(txt_con_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 230, -1));

        txt_password.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_passwordKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_passwordKeyReleased(evt);
            }
        });
        jPanel2.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 230, -1));
        jPanel2.add(txt_dob, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 229, 230, 30));

        btn_login.setBackground(new java.awt.Color(0, 51, 51));
        btn_login.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btn_login.setForeground(new java.awt.Color(0, 51, 51));
        btn_login.setText("Login");
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        jPanel2.add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 330, 100, 30));

        btn_signup.setBackground(new java.awt.Color(0, 51, 51));
        btn_signup.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btn_signup.setForeground(new java.awt.Color(0, 51, 51));
        btn_signup.setText("SignUp");
        btn_signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_signupActionPerformed(evt);
            }
        });
        jPanel2.add(btn_signup, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 100, 30));

        lbl_contactno_error.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_contactno_error.setForeground(new java.awt.Color(255, 51, 51));
        jPanel2.add(lbl_contactno_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, 210, 20));

        lbl_password_error.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_password_error.setForeground(new java.awt.Color(255, 51, 51));
        jPanel2.add(lbl_password_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 200, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 740, 400));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_lastnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_lastnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lastnameActionPerformed

    private void btn_signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_signupActionPerformed
        if (validation())
        {
            InsertDetails();
            Login_Page login = new Login_Page();
            login.show();
            this.dispose();
        }
        
    }//GEN-LAST:event_btn_signupActionPerformed

    private void txt_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyPressed
              checkpassword();
    }//GEN-LAST:event_txt_passwordKeyPressed

    private void txt_passwordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyReleased
              checkpassword();
    }//GEN-LAST:event_txt_passwordKeyReleased

    private void txt_contactnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_contactnoKeyReleased
              checkcontactno();
    }//GEN-LAST:event_txt_contactnoKeyReleased

    private void txt_contactnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_contactnoKeyPressed
              checkcontactno();
    }//GEN-LAST:event_txt_contactnoKeyPressed

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        Login_Page login = new Login_Page();
        login.show();
        this.dispose();
    }//GEN-LAST:event_btn_loginActionPerformed
        boolean validation()
        {
            
            
            fname=txt_firstname.getText();
            lname=txt_lastname.getText();
            uname=txt_username.getText();
            password=txt_password.getText();
            con_pass=txt_con_password.getText();
            contact_no=txt_contactno.getText();
            dob=txt_dob.getDate();
            
            if(fname.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Please Enter FirstName");
                return false;
            }
            
            if(lname.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Please Enter LastName");
                return false;
            }
            
            if(password.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Please Enter Password");
                return false;
            }
            
            if(con_pass.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Please Conform The Password");
                return false;
            }
            
            if(dob.equals(null))
            {
                JOptionPane.showMessageDialog(this, "Please Enter Date of Birth");
                return false;
            }
            
            if(!password.equals(con_pass))
            {
                JOptionPane.showMessageDialog(this, "Password not Matched");
                return false;
            }
            
            
            return true;
            
        }
        
        public void checkpassword()
        {
            password=txt_password.getText();
            if(password.length()<8)
            {
                lbl_password_error.setText("password Should Be 8 Digit");
            }
            else
            {
                lbl_password_error.setText("");
            }
        }
        public void checkcontactno()
        {
           contact_no=txt_contactno.getText();
           if(contact_no.length()==10)
           {
               lbl_contactno_error.setText("");
           }
           else
           {
               lbl_contactno_error.setText("Contact No Should Be of 10 Digit");
           }
        }
        void InsertDetails()
        {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            String myDob=format.format(dob);
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management","root","");
                String sql="insert into signup values(?,?,?,?,?,?,?)";
                PreparedStatement stat=con.prepareCall(sql);
                stat.setInt(1, getId());
                stat.setString(2, fname);
                stat.setString(3, lname);
                stat.setString(4, uname);
                stat.setString(5, password);
                stat.setString(6, myDob);
                stat.setString(7, contact_no);
                int i=stat.executeUpdate();
                if(i>0)
                {
                    JOptionPane.showMessageDialog(this, "Record Insertd");
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Record Not Insertd");
                }
            }
            catch(Exception e)
            {
                        e.printStackTrace();
            }
        }
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
            java.util.logging.Logger.getLogger(Signup_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Signup_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Signup_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Signup_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Signup_Page().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_login;
    private javax.swing.JButton btn_signup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_contactno_error;
    private javax.swing.JLabel lbl_password_error;
    private javax.swing.JPasswordField txt_con_password;
    private javax.swing.JTextField txt_contactno;
    private com.toedter.calendar.JDateChooser txt_dob;
    private javax.swing.JTextField txt_firstname;
    private javax.swing.JTextField txt_lastname;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables

    private void setInt(int i, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
