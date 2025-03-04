/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fees_management_system;


import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 *
 * @author Karan Gohel
 */
public class update_fees_page extends JFrame {

    /**
     * Creates new form AddFees
     */
    public update_fees_page() {
        initComponents();
        fetchdate();
        displatCashFirst();
        fillComboBox();
        int recepitNo = getRecipitNo();
        txt_Recieptno.setText(Integer.toString(recepitNo));
        
    }
    public void fetchdate()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        txt_date.setText(dtf.format(now));
        
        
    }
    public void displatCashFirst()
    {
        lbl_ddno.setVisible(false);
        lbl_chequeno.setVisible(false);
        lbl_bankno.setVisible(false);
        
        txt_dd_no.setVisible(false);
        txt_cheque_no.setVisible(false);
        txt_bank_no.setVisible(false);
    }
    
    public boolean validation(){
        if (txt_recievedfrom.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter User Name");
            return false;
        }
//        if (date_chooser.getDate()==null) {
//            JOptionPane.showMessageDialog(this, "Please Select Date");
//            return false;
//        }
        if (txt_Amount.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Amount (in Number)");
            return false;
        }
        if (combo_payment_mode.getSelectedItem().toString().equalsIgnoreCase("cheque")) {
           if(txt_cheque_no.getText().equals(""))
           {
               JOptionPane.showMessageDialog(this, "Please Enter cheque");
                return false;
           }
           if(txt_bank_no.getText().equals(""))
           {
               JOptionPane.showMessageDialog(this, "Please Enter Bank No");
                return false;
           }
        }
         if (combo_payment_mode.getSelectedItem().toString().equalsIgnoreCase("dd")) {
             if(txt_dd_no.getText().equals(""))
           {
               JOptionPane.showMessageDialog(this, "Please Enter DD No");
                return false;
           }
              if(txt_bank_no.getText().equals(""))
           {
               JOptionPane.showMessageDialog(this, "Please Enter Bank No");
                return false;
           }
         }
         if (combo_payment_mode.getSelectedItem().toString().equalsIgnoreCase("card"))
         {
             if(txt_bank_no.getText().equals(""))
           {
               JOptionPane.showMessageDialog(this, "Please Enter Bank No");
                return false;
           }
         }
        return true;
    }
    public void fillComboBox()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management","root","");
            PreparedStatement pst=Con.prepareStatement("select cname from course");
            ResultSet rs =  pst.executeQuery();
            while (rs.next()) {
                combo_course.addItem(rs.getString("cname"));
                
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getRecipitNo()
    {
        int recepitNo = 0;
        try {
            Connection con = DBConnetion.getConnection();
            PreparedStatement pst = con.prepareStatement("select max(recepit_no) from Fees_Details");
            ResultSet rs = pst.executeQuery();
            if (rs.next() == true) {
                recepitNo = rs.getInt(1);
                
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recepitNo + 1;
    }
    public String UpdateData()
    {
        String Status = "";
        int reciept_no = Integer.parseInt(txt_Recieptno.getText());
        String student_name = txt_recievedfrom.getText();
        int roll_no = Integer.parseInt(txt_rollno.getText());
        String payment_mode = combo_payment_mode.getSelectedItem().toString();
        String cheuqe_no = txt_cheque_no.getText();
        String bank_name = txt_bank_no.getText();
        String dd_no = txt_dd_no.getText();
        String coursename = txt_coursename.getText();
        String gstno = txt_gstno.getText();
        Float total_amount = Float.parseFloat(txt_total.getText());
        String Date = txt_date.getText();
        int initialAmount = Integer.parseInt(txt_Amount.getText());
        float cgst = Float.parseFloat(txt_cgst.getText());
        float sgst = Float.parseFloat(txt_sgst.getText());
        String totalInWord = txt_totak_words.getText();
        String remark = txt_Remark.getText();
        int year1 = Integer.parseInt(txt_year1.getText());
        int year2 = Integer.parseInt(txt_year2.getText());
        
        try {
            Connection con =  DBConnetion.getConnection();
            PreparedStatement pst = con.prepareStatement("update fees_details set student_name=?,payment_mode=?,cheque_no=?,bank_name=?dd_no=?,course_name=?,gstin=?,total_amount=?,date=?,amount=?,cgst=?,sgst=?,total_in_word=?,remarks=?,year1=?,year2=? where reciept_no=?");
            
            pst.setString(1,student_name);
            pst.setInt(2, roll_no);
            pst.setString(3, payment_mode);
            pst.setString(4, cheuqe_no);
            pst.setString(5, bank_name);
            pst.setString(6, dd_no);
            pst.setString(7, coursename);
            pst.setString(8, gstno);
            pst.setFloat(9, total_amount);
            pst.setString(10, Date);
            pst.setInt(11, initialAmount);
            pst.setFloat(12, cgst);
            pst.setFloat(13, sgst);
            pst.setString(14, totalInWord);
            pst.setString(15, remark);
            pst.setInt(16, year1);
            pst.setInt(17, year2);
            pst.setInt(18, reciept_no);
            int rowCount = pst.executeUpdate();
            if(rowCount == 1)
            {
                Status = "success";
            }
            else
            {
                Status = "Failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Status;
    }
    
    public void setRecords()
    {
        try {
            Connection con =  DBConnetion.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from fees_details order by receipt_no desc limit 1");
            ResultSet rs = pst.executeQuery();
            rs.next();
            txt_Recieptno.setText(rs.getString("receipt_no"));
            combo_payment_mode.setSelectedItem(rs.getString("payment_mode"));
            txt_cheque_no.setText(rs.getString("cheque_no"));
            txt_dd_no.setText(rs.getString("dd_no"));
            txt_bank_no.setText(rs.getString("bank_name"));
            txt_recievedfrom.setText(rs.getString("student_name"));
            txt_date.setText(rs.getString("date"));
            txt_year1.setText(rs.getString("year1"));
            txt_year2.setText(rs.getString("year2"));
            txt_rollno.setText(rs.getString("roll_no"));
            combo_course.setSelectedItem(rs.getString("course_name"));
            txt_Amount.setText(rs.getString("amount"));
            txt_coursename.setText(rs.getString("receipt_no"));
            txt_sgst.setText(rs.getString("sgst"));
            txt_cgst.setText(rs.getString("cgst"));
            txt_Amount.setText(rs.getString("total_amount"));
            txt_totak_words.setText(rs.getString("total_in_word"));
            txt_Remark.setText(rs.getString("remark"));
            
            
            
            
            
            
                   
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelparent = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_bank_no = new javax.swing.JTextField();
        txt_date = new javax.swing.JLabel();
        combo_payment_mode = new javax.swing.JComboBox<>();
        lbl_bankno = new javax.swing.JLabel();
        txt_Recieptno = new javax.swing.JTextField();
        lbl_chequeno = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_dd_no = new javax.swing.JTextField();
        panelChild = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        txt_totak_words = new javax.swing.JTextField();
        txt_coursename = new javax.swing.JTextField();
        txt_cgst = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        txt_sgst = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        btn_Print = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_Remark = new javax.swing.JTextArea();
        combo_course = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_year1 = new javax.swing.JTextField();
        txt_recievedfrom = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txt_Amount = new javax.swing.JTextField();
        txt_year2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lbl_ddno = new javax.swing.JLabel();
        txt_cheque_no = new javax.swing.JTextField();
        txt_rollno = new javax.swing.JTextField();
        txt_gstno = new javax.swing.JLabel();
        panelSidebar1 = new javax.swing.JPanel();
        btnHome1 = new javax.swing.JPanel();
        lblHome1 = new javax.swing.JLabel();
        panelSearchRecord1 = new javax.swing.JPanel();
        btnSearchRecord1 = new javax.swing.JLabel();
        panelEditCourse1 = new javax.swing.JPanel();
        btnEditCourse1 = new javax.swing.JLabel();
        panelCourseList1 = new javax.swing.JPanel();
        btnCourseList1 = new javax.swing.JLabel();
        panelViewAllRecord1 = new javax.swing.JPanel();
        btnViewAllRecord1 = new javax.swing.JLabel();
        panelLogout1 = new javax.swing.JPanel();
        btnLogout1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelparent.setBackground(new java.awt.Color(0, 153, 153));
        panelparent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Reciept no : SOC -");
        panelparent.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 160, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Mode of Payment :");
        panelparent.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 160, 30));

        txt_bank_no.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        panelparent.add(txt_bank_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 290, 30));

        txt_date.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        panelparent.add(txt_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 210, 30));

        combo_payment_mode.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        combo_payment_mode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "Cheque", "Cash", "Card" }));
        combo_payment_mode.setSelectedIndex(2);
        combo_payment_mode.setToolTipText("Cash\nChaque\nNet Banking");
        combo_payment_mode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_payment_modeActionPerformed(evt);
            }
        });
        panelparent.add(combo_payment_mode, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 170, 30));

        lbl_bankno.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_bankno.setText("Bank Name :");
        panelparent.add(lbl_bankno, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 130, 30));

        txt_Recieptno.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        panelparent.add(txt_Recieptno, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 290, 30));

        lbl_chequeno.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_chequeno.setText("Cheque No :");
        panelparent.add(lbl_chequeno, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 110, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Date :");
        panelparent.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, 120, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("GSTIN :");
        panelparent.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 170, 60));

        txt_dd_no.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        panelparent.add(txt_dd_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 290, 30));

        panelChild.setBackground(new java.awt.Color(0, 153, 153));
        panelChild.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelChild.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 480, 250, 10));
        panelChild.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 1450, 10));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("Reciever Signature");
        panelChild.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 490, 160, 30));

        txt_totak_words.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        panelChild.add(txt_totak_words, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 520, 40));

        txt_coursename.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        panelChild.add(txt_coursename, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 410, 30));

        txt_cgst.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        panelChild.add(txt_cgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, 210, 30));
        panelChild.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1450, 10));

        txt_sgst.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        panelChild.add(txt_sgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 280, 210, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Sr. No");
        panelChild.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 160, 40));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setText("CGST 9%");
        panelChild.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 100, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setText("SGST 9%");
        panelChild.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, 80, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setText("Total in Words :");
        panelChild.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 150, 30));

        txt_total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        panelChild.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 390, 210, 30));
        panelChild.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 360, 250, 10));

        btn_Print.setBackground(new java.awt.Color(0, 51, 51));
        btn_Print.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btn_Print.setForeground(new java.awt.Color(255, 255, 255));
        btn_Print.setText("Print");
        btn_Print.setToolTipText("");
        btn_Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PrintActionPerformed(evt);
            }
        });
        panelChild.add(btn_Print, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 540, 140, 40));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setText("Remark :");
        panelChild.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 470, 90, 60));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Amount (Rs)");
        panelChild.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, 210, 40));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Head");
        panelChild.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 390, 40));

        txt_Remark.setColumns(20);
        txt_Remark.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txt_Remark.setRows(5);
        jScrollPane1.setViewportView(txt_Remark);

        panelChild.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 470, 520, -1));

        combo_course.setToolTipText("Cash\nChaque\nNet Banking");
        combo_course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_courseActionPerformed(evt);
            }
        });
        panelChild.add(combo_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 460, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("Course :");
        panelChild.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 110, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("The Following Payment in The College Office for The Year                               To");
        panelChild.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 700, 30));

        txt_year1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        panelChild.add(txt_year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 110, 30));

        txt_recievedfrom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        panelChild.add(txt_recievedfrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 460, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Recieved From :");
        panelChild.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 180, 30));

        txt_Amount.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_Amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_AmountActionPerformed(evt);
            }
        });
        panelChild.add(txt_Amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 180, 210, 30));

        txt_year2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        panelChild.add(txt_year2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 40, 110, 30));

        panelparent.add(panelChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 1590, 710));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Roll No :");
        panelparent.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, 110, 30));

        lbl_ddno.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_ddno.setText("DD no :");
        panelparent.add(lbl_ddno, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 90, 30));
        panelparent.add(txt_cheque_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 290, 30));

        txt_rollno.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        panelparent.add(txt_rollno, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 180, 30));

        txt_gstno.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_gstno.setText("KSCBDS15165");
        panelparent.add(txt_gstno, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 40, 170, 60));

        getContentPane().add(panelparent, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 1590, 880));

        panelSidebar1.setBackground(new java.awt.Color(0, 102, 102));
        panelSidebar1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome1.setBackground(new java.awt.Color(0, 102, 102));
        btnHome1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 255), null, null));
        btnHome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHome1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHome1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHome1MouseExited(evt);
            }
        });
        btnHome1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHome1.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        lblHome1.setForeground(new java.awt.Color(255, 255, 255));
        lblHome1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHome1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/home.png"))); // NOI18N
        lblHome1.setText("  Home");
        lblHome1.setToolTipText("");
        lblHome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHome1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHome1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblHome1MouseExited(evt);
            }
        });
        btnHome1.add(lblHome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 70));

        panelSidebar1.add(btnHome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 250, 70));

        panelSearchRecord1.setBackground(new java.awt.Color(0, 102, 102));
        panelSearchRecord1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 255), null, null));
        panelSearchRecord1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelSearchRecord1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelSearchRecord1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelSearchRecord1MouseExited(evt);
            }
        });
        panelSearchRecord1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSearchRecord1.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        btnSearchRecord1.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchRecord1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSearchRecord1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/search2.png"))); // NOI18N
        btnSearchRecord1.setText("Search Record");
        btnSearchRecord1.setToolTipText("");
        btnSearchRecord1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchRecord1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSearchRecord1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSearchRecord1MouseExited(evt);
            }
        });
        panelSearchRecord1.add(btnSearchRecord1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 70));

        panelSidebar1.add(panelSearchRecord1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 250, 70));

        panelEditCourse1.setBackground(new java.awt.Color(0, 102, 102));
        panelEditCourse1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 255), null, null));
        panelEditCourse1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelEditCourse1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelEditCourse1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelEditCourse1MouseExited(evt);
            }
        });
        panelEditCourse1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEditCourse1.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        btnEditCourse1.setForeground(new java.awt.Color(255, 255, 255));
        btnEditCourse1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEditCourse1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/edit2.png"))); // NOI18N
        btnEditCourse1.setText("  Edit Course");
        btnEditCourse1.setToolTipText("");
        btnEditCourse1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditCourse1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditCourse1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditCourse1MouseExited(evt);
            }
        });
        panelEditCourse1.add(btnEditCourse1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 70));

        panelSidebar1.add(panelEditCourse1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 250, 70));

        panelCourseList1.setBackground(new java.awt.Color(0, 102, 102));
        panelCourseList1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 255), null, null));
        panelCourseList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelCourseList1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelCourseList1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelCourseList1MouseExited(evt);
            }
        });
        panelCourseList1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCourseList1.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        btnCourseList1.setForeground(new java.awt.Color(255, 255, 255));
        btnCourseList1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCourseList1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/list.png"))); // NOI18N
        btnCourseList1.setText("Course List");
        btnCourseList1.setToolTipText("");
        btnCourseList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCourseList1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCourseList1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCourseList1MouseExited(evt);
            }
        });
        panelCourseList1.add(btnCourseList1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 70));

        panelSidebar1.add(panelCourseList1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 250, 70));

        panelViewAllRecord1.setBackground(new java.awt.Color(0, 102, 102));
        panelViewAllRecord1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 255), null, null));
        panelViewAllRecord1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelViewAllRecord1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelViewAllRecord1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelViewAllRecord1MouseExited(evt);
            }
        });
        panelViewAllRecord1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnViewAllRecord1.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        btnViewAllRecord1.setForeground(new java.awt.Color(255, 255, 255));
        btnViewAllRecord1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnViewAllRecord1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/view all record.png"))); // NOI18N
        btnViewAllRecord1.setText("View All Record");
        btnViewAllRecord1.setToolTipText("");
        btnViewAllRecord1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnViewAllRecord1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnViewAllRecord1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnViewAllRecord1MouseExited(evt);
            }
        });
        panelViewAllRecord1.add(btnViewAllRecord1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 70));

        panelSidebar1.add(panelViewAllRecord1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 500, 250, 70));

        panelLogout1.setBackground(new java.awt.Color(0, 102, 102));
        panelLogout1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 255), null, null));
        panelLogout1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelLogout1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelLogout1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelLogout1MouseExited(evt);
            }
        });
        panelLogout1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogout1.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        btnLogout1.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLogout1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/logout.png"))); // NOI18N
        btnLogout1.setText("  Log Out");
        btnLogout1.setToolTipText("");
        btnLogout1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogout1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogout1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogout1MouseExited(evt);
            }
        });
        panelLogout1.add(btnLogout1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 70));

        panelSidebar1.add(panelLogout1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 610, 250, 70));

        getContentPane().add(panelSidebar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 880));

        setSize(new java.awt.Dimension(2035, 915));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void combo_courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_courseActionPerformed
        txt_coursename.setText(combo_course.getSelectedItem().toString());
    }//GEN-LAST:event_combo_courseActionPerformed

    private void combo_payment_modeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_payment_modeActionPerformed
        if (combo_payment_mode.getSelectedIndex() == 0) {
            lbl_ddno.setVisible(true);
            txt_dd_no.setVisible(true);
            lbl_chequeno.setVisible(false);
            txt_cheque_no.setVisible(false);
            lbl_bankno.setVisible(true);
            txt_bank_no.setVisible(true);
        }
        if (combo_payment_mode.getSelectedIndex() == 1) {
            lbl_ddno.setVisible(false);
            txt_dd_no.setVisible(false);
            lbl_chequeno.setVisible(true);
            txt_cheque_no.setVisible(true);
            lbl_bankno.setVisible(true);
            txt_bank_no.setVisible(true);
        }
        if (combo_payment_mode.getSelectedIndex() == 2) {
            lbl_ddno.setVisible(false);
            txt_dd_no.setVisible(false);
            lbl_chequeno.setVisible(false);
            txt_cheque_no.setVisible(false);
            lbl_bankno.setVisible(false);
            txt_bank_no.setVisible(false);
        }
        if (combo_payment_mode.getSelectedIndex() == 3) {
            lbl_ddno.setVisible(false);
            txt_dd_no.setVisible(false);
            lbl_chequeno.setVisible(false);
            txt_cheque_no.setVisible(false);
            lbl_bankno.setVisible(true);
            txt_bank_no.setVisible(true);
        }
    }//GEN-LAST:event_combo_payment_modeActionPerformed

    private void btn_PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PrintActionPerformed
        if(validation()==true) 
        {
            String result = UpdateData();
            if (result.equals("success")) 
            {
                JOptionPane.showMessageDialog(this, "Record Inserted Successfuly");
                PrintReciept_Page PrintReciept = new PrintReciept_Page();
                PrintReciept.setVisible(true);
                this.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Record Insertion Falied");
            }
        }
    }//GEN-LAST:event_btn_PrintActionPerformed

    private void txt_AmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_AmountActionPerformed
       Float amnt = Float.parseFloat(txt_Amount.getText());
       Float cgst = (float)(amnt * 0.09);
       Float sgst = (float) (amnt * 0.09);
       
       txt_cgst.setText(cgst.toString());
       txt_sgst.setText(sgst.toString());
       
       float total = amnt + cgst + sgst;
       txt_total.setText(Float.toString(total));
       txt_totak_words.setText(NumberToWordsConverter.convert((int)total) + " Only");
       
    }//GEN-LAST:event_txt_AmountActionPerformed

    private void lblHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeMouseClicked
        home home = new home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblHomeMouseClicked

    private void lblHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeMouseEntered
        Color clr=new Color(0,51,51);
        btnHome.setBackground(clr);
    }//GEN-LAST:event_lblHomeMouseEntered

    private void lblHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeMouseExited
        Color clr=new Color(0,102,102);
        btnHome.setBackground(clr);
    }//GEN-LAST:event_lblHomeMouseExited

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        home home = new home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseEntered
        Color clr=new Color(0,51,51);
        btnHome.setBackground(clr);
    }//GEN-LAST:event_btnHomeMouseEntered

    private void btnHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseExited

    }//GEN-LAST:event_btnHomeMouseExited

    private void btnSearchRecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchRecordMouseClicked
        search_record_page search = new search_record_page();
        search.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSearchRecordMouseClicked

    private void btnSearchRecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchRecordMouseEntered
        Color clr=new Color(0,51,51);
        panelSearchRecord.setBackground(clr);
    }//GEN-LAST:event_btnSearchRecordMouseEntered

    private void btnSearchRecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchRecordMouseExited
        Color clr=new Color(0,102,102);
        panelSearchRecord.setBackground(clr);
    }//GEN-LAST:event_btnSearchRecordMouseExited

    private void panelSearchRecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchRecordMouseClicked
        search_record_page search = new search_record_page();
        search.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelSearchRecordMouseClicked

    private void panelSearchRecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchRecordMouseEntered
        Color clr=new Color(0,51,51);
        panelSearchRecord.setBackground(clr);
    }//GEN-LAST:event_panelSearchRecordMouseEntered

    private void panelSearchRecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchRecordMouseExited
        Color clr=new Color(0,102,102);
        panelSearchRecord.setBackground(clr);
    }//GEN-LAST:event_panelSearchRecordMouseExited

    private void btnEditCourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCourseMouseClicked
        update_course_page updateCourse = new update_course_page();
        updateCourse.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEditCourseMouseClicked

    private void btnEditCourseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCourseMouseEntered
        Color clr=new Color(0,51,51);
        panelEditCourse.setBackground(clr);
    }//GEN-LAST:event_btnEditCourseMouseEntered

    private void btnEditCourseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCourseMouseExited
        Color clr=new Color(0,102,102);
        panelEditCourse.setBackground(clr);
    }//GEN-LAST:event_btnEditCourseMouseExited

    private void panelEditCourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditCourseMouseClicked
        update_course_page updateCourse = new update_course_page();
        updateCourse.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelEditCourseMouseClicked

    private void panelEditCourseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditCourseMouseEntered
        Color clr=new Color(0,51,51);
        panelEditCourse.setBackground(clr);
    }//GEN-LAST:event_panelEditCourseMouseEntered

    private void panelEditCourseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditCourseMouseExited
        Color clr=new Color(0,102,102);
        panelEditCourse.setBackground(clr);
    }//GEN-LAST:event_panelEditCourseMouseExited

    private void btnCourseListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseListMouseClicked
        View_Course_Page viewCourse = new View_Course_Page();
        viewCourse.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCourseListMouseClicked

    private void btnCourseListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseListMouseEntered
        Color clr=new Color(0,51,51);
        panelCourseList.setBackground(clr);
    }//GEN-LAST:event_btnCourseListMouseEntered

    private void btnCourseListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseListMouseExited
        Color clr=new Color(0,102,102);
        panelCourseList.setBackground(clr);
    }//GEN-LAST:event_btnCourseListMouseExited

    private void panelCourseListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCourseListMouseClicked
        View_Course_Page viewCourse = new View_Course_Page();
        viewCourse.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelCourseListMouseClicked

    private void panelCourseListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCourseListMouseEntered
        Color clr=new Color(0,51,51);
        panelCourseList.setBackground(clr);
    }//GEN-LAST:event_panelCourseListMouseEntered

    private void panelCourseListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCourseListMouseExited
        Color clr=new Color(0,102,102);
        panelCourseList.setBackground(clr);
    }//GEN-LAST:event_panelCourseListMouseExited

    private void btnViewAllRecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecordMouseClicked
        View_All_Record_Page allRecord = new View_All_Record_Page();
        allRecord.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnViewAllRecordMouseClicked

    private void btnViewAllRecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecordMouseEntered
        Color clr=new Color(0,51,51);
        panelViewAllRecord.setBackground(clr);
    }//GEN-LAST:event_btnViewAllRecordMouseEntered

    private void btnViewAllRecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecordMouseExited
        Color clr=new Color(0,102,102);
        panelViewAllRecord.setBackground(clr);
    }//GEN-LAST:event_btnViewAllRecordMouseExited

    private void panelViewAllRecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViewAllRecordMouseClicked
        View_All_Record_Page allRecord = new View_All_Record_Page();
        allRecord.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelViewAllRecordMouseClicked

    private void panelViewAllRecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViewAllRecordMouseEntered
        Color clr=new Color(0,51,51);
        panelViewAllRecord.setBackground(clr);
    }//GEN-LAST:event_panelViewAllRecordMouseEntered

    private void panelViewAllRecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViewAllRecordMouseExited
        Color clr=new Color(0,102,102);
        panelViewAllRecord.setBackground(clr);
    }//GEN-LAST:event_panelViewAllRecordMouseExited

    private void btnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseClicked
        Login_Page login = new Login_Page();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutMouseClicked

    private void btnLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseEntered
        Color clr=new Color(0,51,51);
        panelLogout.setBackground(clr);
    }//GEN-LAST:event_btnLogoutMouseEntered

    private void btnLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseExited
        Color clr=new Color(0,102,102);
        panelLogout.setBackground(clr);
    }//GEN-LAST:event_btnLogoutMouseExited

    private void panelLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogoutMouseClicked
        Login_Page login = new Login_Page();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelLogoutMouseClicked

    private void panelLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogoutMouseEntered

    }//GEN-LAST:event_panelLogoutMouseEntered

    private void panelLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogoutMouseExited

    }//GEN-LAST:event_panelLogoutMouseExited

    private void lblHome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHome1MouseClicked
        home home = new home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblHome1MouseClicked

    private void lblHome1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHome1MouseEntered
        Color clr=new Color(0,51,51);
        btnHome.setBackground(clr);
    }//GEN-LAST:event_lblHome1MouseEntered

    private void lblHome1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHome1MouseExited
        Color clr=new Color(0,102,102);
        btnHome.setBackground(clr);
    }//GEN-LAST:event_lblHome1MouseExited

    private void btnHome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHome1MouseClicked
        home home = new home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHome1MouseClicked

    private void btnHome1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHome1MouseEntered
        Color clr=new Color(0,51,51);
        btnHome.setBackground(clr);
    }//GEN-LAST:event_btnHome1MouseEntered

    private void btnHome1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHome1MouseExited

    }//GEN-LAST:event_btnHome1MouseExited

    private void btnSearchRecord1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchRecord1MouseClicked
        search_record_page search = new search_record_page();
        search.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSearchRecord1MouseClicked

    private void btnSearchRecord1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchRecord1MouseEntered
        Color clr=new Color(0,51,51);
        panelSearchRecord.setBackground(clr);
    }//GEN-LAST:event_btnSearchRecord1MouseEntered

    private void btnSearchRecord1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchRecord1MouseExited
        Color clr=new Color(0,102,102);
        panelSearchRecord.setBackground(clr);
    }//GEN-LAST:event_btnSearchRecord1MouseExited

    private void panelSearchRecord1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchRecord1MouseClicked
        search_record_page search = new search_record_page();
        search.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelSearchRecord1MouseClicked

    private void panelSearchRecord1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchRecord1MouseEntered
        Color clr=new Color(0,51,51);
        panelSearchRecord.setBackground(clr);
    }//GEN-LAST:event_panelSearchRecord1MouseEntered

    private void panelSearchRecord1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchRecord1MouseExited
        Color clr=new Color(0,102,102);
        panelSearchRecord.setBackground(clr);
    }//GEN-LAST:event_panelSearchRecord1MouseExited

    private void btnEditCourse1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCourse1MouseClicked
        update_course_page updateCourse = new update_course_page();
        updateCourse.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEditCourse1MouseClicked

    private void btnEditCourse1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCourse1MouseEntered
        Color clr=new Color(0,51,51);
        panelEditCourse.setBackground(clr);
    }//GEN-LAST:event_btnEditCourse1MouseEntered

    private void btnEditCourse1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCourse1MouseExited
        Color clr=new Color(0,102,102);
        panelEditCourse.setBackground(clr);
    }//GEN-LAST:event_btnEditCourse1MouseExited

    private void panelEditCourse1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditCourse1MouseClicked
        update_course_page updateCourse = new update_course_page();
        updateCourse.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelEditCourse1MouseClicked

    private void panelEditCourse1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditCourse1MouseEntered
        Color clr=new Color(0,51,51);
        panelEditCourse.setBackground(clr);
    }//GEN-LAST:event_panelEditCourse1MouseEntered

    private void panelEditCourse1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditCourse1MouseExited
        Color clr=new Color(0,102,102);
        panelEditCourse.setBackground(clr);
    }//GEN-LAST:event_panelEditCourse1MouseExited

    private void btnCourseList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseList1MouseClicked
        View_Course_Page viewCourse = new View_Course_Page();
        viewCourse.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCourseList1MouseClicked

    private void btnCourseList1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseList1MouseEntered
        Color clr=new Color(0,51,51);
        panelCourseList.setBackground(clr);
    }//GEN-LAST:event_btnCourseList1MouseEntered

    private void btnCourseList1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseList1MouseExited
        Color clr=new Color(0,102,102);
        panelCourseList.setBackground(clr);
    }//GEN-LAST:event_btnCourseList1MouseExited

    private void panelCourseList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCourseList1MouseClicked
        View_Course_Page viewCourse = new View_Course_Page();
        viewCourse.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelCourseList1MouseClicked

    private void panelCourseList1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCourseList1MouseEntered
        Color clr=new Color(0,51,51);
        panelCourseList.setBackground(clr);
    }//GEN-LAST:event_panelCourseList1MouseEntered

    private void panelCourseList1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCourseList1MouseExited
        Color clr=new Color(0,102,102);
        panelCourseList.setBackground(clr);
    }//GEN-LAST:event_panelCourseList1MouseExited

    private void btnViewAllRecord1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecord1MouseClicked
        View_All_Record_Page allRecord = new View_All_Record_Page();
        allRecord.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnViewAllRecord1MouseClicked

    private void btnViewAllRecord1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecord1MouseEntered
        Color clr=new Color(0,51,51);
        panelViewAllRecord.setBackground(clr);
    }//GEN-LAST:event_btnViewAllRecord1MouseEntered

    private void btnViewAllRecord1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecord1MouseExited
        Color clr=new Color(0,102,102);
        panelViewAllRecord.setBackground(clr);
    }//GEN-LAST:event_btnViewAllRecord1MouseExited

    private void panelViewAllRecord1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViewAllRecord1MouseClicked
        View_All_Record_Page allRecord = new View_All_Record_Page();
        allRecord.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelViewAllRecord1MouseClicked

    private void panelViewAllRecord1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViewAllRecord1MouseEntered
        Color clr=new Color(0,51,51);
        panelViewAllRecord.setBackground(clr);
    }//GEN-LAST:event_panelViewAllRecord1MouseEntered

    private void panelViewAllRecord1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViewAllRecord1MouseExited
        Color clr=new Color(0,102,102);
        panelViewAllRecord.setBackground(clr);
    }//GEN-LAST:event_panelViewAllRecord1MouseExited

    private void btnLogout1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogout1MouseClicked
        Login_Page login = new Login_Page();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogout1MouseClicked

    private void btnLogout1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogout1MouseEntered
        Color clr=new Color(0,51,51);
        panelLogout.setBackground(clr);
    }//GEN-LAST:event_btnLogout1MouseEntered

    private void btnLogout1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogout1MouseExited
        Color clr=new Color(0,102,102);
        panelLogout.setBackground(clr);
    }//GEN-LAST:event_btnLogout1MouseExited

    private void panelLogout1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogout1MouseClicked
        Login_Page login = new Login_Page();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelLogout1MouseClicked

    private void panelLogout1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogout1MouseEntered

    }//GEN-LAST:event_panelLogout1MouseEntered

    private void panelLogout1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogout1MouseExited

    }//GEN-LAST:event_panelLogout1MouseExited

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
            java.util.logging.Logger.getLogger(update_fees_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(update_fees_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(update_fees_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(update_fees_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new update_fees_page().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCourseList;
    private javax.swing.JLabel btnCourseList1;
    private javax.swing.JLabel btnEditCourse;
    private javax.swing.JLabel btnEditCourse1;
    private javax.swing.JPanel btnHome;
    private javax.swing.JPanel btnHome1;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnLogout1;
    private javax.swing.JLabel btnSearchRecord;
    private javax.swing.JLabel btnSearchRecord1;
    private javax.swing.JLabel btnViewAllRecord;
    private javax.swing.JLabel btnViewAllRecord1;
    private javax.swing.JButton btn_Print;
    private javax.swing.JComboBox<String> combo_course;
    private javax.swing.JComboBox<String> combo_payment_mode;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblHome;
    private javax.swing.JLabel lblHome1;
    private javax.swing.JLabel lbl_bankno;
    private javax.swing.JLabel lbl_chequeno;
    private javax.swing.JLabel lbl_ddno;
    private javax.swing.JPanel panelChild;
    private javax.swing.JPanel panelCourseList;
    private javax.swing.JPanel panelCourseList1;
    private javax.swing.JPanel panelEditCourse;
    private javax.swing.JPanel panelEditCourse1;
    private javax.swing.JPanel panelLogout;
    private javax.swing.JPanel panelLogout1;
    private javax.swing.JPanel panelSearchRecord;
    private javax.swing.JPanel panelSearchRecord1;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JPanel panelSidebar1;
    private javax.swing.JPanel panelViewAllRecord;
    private javax.swing.JPanel panelViewAllRecord1;
    private javax.swing.JPanel panelparent;
    private javax.swing.JTextField txt_Amount;
    private javax.swing.JTextField txt_Recieptno;
    private javax.swing.JTextArea txt_Remark;
    private javax.swing.JTextField txt_bank_no;
    private javax.swing.JTextField txt_cgst;
    private javax.swing.JTextField txt_cheque_no;
    private javax.swing.JTextField txt_coursename;
    private javax.swing.JLabel txt_date;
    private javax.swing.JTextField txt_dd_no;
    private javax.swing.JLabel txt_gstno;
    private javax.swing.JTextField txt_recievedfrom;
    private javax.swing.JTextField txt_rollno;
    private javax.swing.JTextField txt_sgst;
    private javax.swing.JTextField txt_totak_words;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_year1;
    private javax.swing.JTextField txt_year2;
    // End of variables declaration//GEN-END:variables

    
}
