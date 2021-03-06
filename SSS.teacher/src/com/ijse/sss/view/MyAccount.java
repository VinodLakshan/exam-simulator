/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.view;

import com.ijse.sss.connector.ServerConnector;
import com.ijse.sss.controller.TeacherController;
import com.ijse.sss.model.Teacher;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author NEOx
 */
public class MyAccount extends javax.swing.JFrame {

    /**
     * Creates new form MyAccount
     */
    private String tId;
    private String userName;
    private String Name;
    private String gender;
    private String pass;
    private String conPass;
    private TeacherMain teacherMain;
    private String cuttentUserName;
    private int count = 1;

    public MyAccount(TeacherMain teacherMain, String currentUserName) {
        initComponents();
        this.teacherMain = teacherMain;
        this.cuttentUserName = currentUserName;
        autoDateTime();
        loadTeacher();
        lblCor.setVisible(false);
        lblCorUser.setVisible(false);
    }

    private void autoDateTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Date time = new Date();
                    Date date = new Date();
                    String curTime = new SimpleDateFormat("hh:mm aa").format(time);
                    String curDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    lblTime.setText(curTime);
                    lblDate.setText(curDate);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CreateNewTeacher.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }

    private void loadTeacher() {
        try {
            jLabel13.setVisible(false);
            jLabel14.setVisible(false);
            txtConPass.setVisible(false);
            TeacherController teacherController = ServerConnector.getServerConnector().getTeacherController();
            Teacher searchTeacherByUserName = teacherController.searchTeacherByUserName(cuttentUserName);
            txtUserName.setText(cuttentUserName);
            txtName.setText(searchTeacherByUserName.gettName());
            txtPass.setText(searchTeacherByUserName.getPass());
            txtConPass.setText(searchTeacherByUserName.getPass());
            if (searchTeacherByUserName.getGender().equalsIgnoreCase("Male")) {
                JrbMale.setSelected(true);
            } else {
                JrbFemale.setSelected(true);
            }

        } catch (RemoteException ex) {
            Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int checkFields() {
        tId = "t1";
        userName = txtUserName.getText();
        Name = txtName.getText();

        if (JrbMale.isSelected()) {
            gender = "Male";
        } else {
            gender = "Female";
        }
        pass = txtPass.getText();
        conPass = txtConPass.getText();

        if (userName.equals("") | Name.equals("") | pass.equals("") | conPass.equals("")) {
            if (!pass.equals(conPass)) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (!pass.equals(conPass)) {
                return 2;
            } else {
                return 3;
            }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btCreateNewAccount1 = new javax.swing.JButton();
        lblCorUser = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblCor = new javax.swing.JLabel();
        btCancel = new javax.swing.JButton();
        btCreateNewAccount = new javax.swing.JButton();
        txtConPass = new javax.swing.JPasswordField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        JrbFemale = new javax.swing.JRadioButton();
        JrbMale = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblBackgrond = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btCreateNewAccount1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/deleteAccount.jpg"))); // NOI18N
        btCreateNewAccount1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCreateNewAccount1ActionPerformed(evt);
            }
        });
        jPanel1.add(btCreateNewAccount1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 900, 370, 100));

        lblCorUser.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        lblCorUser.setForeground(new java.awt.Color(0, 51, 153));
        lblCorUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/1-33-512.png"))); // NOI18N
        jPanel1.add(lblCorUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 280, 60, 60));

        lblDate.setFont(new java.awt.Font("Microsoft Himalaya", 1, 80)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setText("08:00");
        jPanel1.add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 120, 310, 90));

        lblTime.setFont(new java.awt.Font("Microsoft Himalaya", 1, 100)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        lblTime.setText("08:00");
        jPanel1.add(lblTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 60, 330, 90));

        lblCor.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        lblCor.setForeground(new java.awt.Color(0, 51, 153));
        lblCor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/1-33-512.png"))); // NOI18N
        jPanel1.add(lblCor, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 640, 60, 60));

        btCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/cancelbt.jpg"))); // NOI18N
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });
        jPanel1.add(btCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 900, 220, 100));

        btCreateNewAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/changeDetails.jpg"))); // NOI18N
        btCreateNewAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCreateNewAccountActionPerformed(evt);
            }
        });
        jPanel1.add(btCreateNewAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 900, 370, 100));

        txtConPass.setFont(new java.awt.Font("Tahoma", 0, 50)); // NOI18N
        txtConPass.setEnabled(false);
        txtConPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConPassKeyReleased(evt);
            }
        });
        jPanel1.add(txtConPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 690, 540, 50));

        jLabel13.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText(":");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 670, 30, 100));

        jLabel14.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Confirm Password");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 670, 370, 100));

        txtPass.setFont(new java.awt.Font("Tahoma", 0, 50)); // NOI18N
        txtPass.setEnabled(false);
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPassKeyReleased(evt);
            }
        });
        jPanel1.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 590, 540, 50));

        jLabel11.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Password");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 570, 300, 100));

        jLabel12.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText(":");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 570, 30, 100));

        buttonGroup1.add(JrbFemale);
        JrbFemale.setFont(new java.awt.Font("Microsoft Himalaya", 0, 50)); // NOI18N
        JrbFemale.setForeground(new java.awt.Color(255, 255, 255));
        JrbFemale.setText("  Female");
        JrbFemale.setOpaque(false);
        jPanel1.add(JrbFemale, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 500, 240, 50));

        buttonGroup1.add(JrbMale);
        JrbMale.setFont(new java.awt.Font("Microsoft Himalaya", 0, 50)); // NOI18N
        JrbMale.setForeground(new java.awt.Color(255, 255, 255));
        JrbMale.setSelected(true);
        JrbMale.setText("  Male");
        JrbMale.setOpaque(false);
        jPanel1.add(JrbMale, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 500, 240, 50));

        jLabel9.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText(":");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 480, 30, 100));

        jLabel10.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Gender");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 480, 300, 100));

        jLabel1.setFont(new java.awt.Font("Microsoft Himalaya", 3, 80)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MY ACCOOUNT");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 800, 160));

        txtUserName.setFont(new java.awt.Font("Microsoft Himalaya", 0, 50)); // NOI18N
        txtUserName.setEnabled(false);
        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });
        txtUserName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUserNameKeyReleased(evt);
            }
        });
        jPanel1.add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 290, 540, 50));

        jLabel5.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("User Name");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, 260, 100));

        jLabel6.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText(":");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 270, 30, 100));

        jLabel4.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText(":");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 380, 30, 100));

        txtName.setFont(new java.awt.Font("Microsoft Himalaya", 0, 50)); // NOI18N
        txtName.setEnabled(false);
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 400, 540, 50));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1860, 160));

        jLabel3.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Full Name");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 380, 270, 100));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 1860, 830));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 1860, 830));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1860, 160));

        jLabel2.setFont(new java.awt.Font("Microsoft Himalaya", 3, 80)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/technology-1106438_960_720darky.jpg"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255), 4));
        jLabel2.setPreferredSize(new java.awt.Dimension(1920, 1000));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1880, 1020));

        lblBackgrond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/technology-1106438_960_720darky.jpg"))); // NOI18N
        jPanel1.add(lblBackgrond, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        jRadioButton1.setText("jRadioButton1");
        jPanel1.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 510, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        teacherMain.show();
        teacherMain.txtUserName.setText(cuttentUserName);
        teacherMain.setTitle(cuttentUserName);
        this.dispose();

    }//GEN-LAST:event_btCancelActionPerformed

    private void txtConPassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConPassKeyReleased
        if (!txtPass.getText().equals(txtConPass.getText())) {
            lblCor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/Delete_remove_close_exit_trash_cancel_cross.png")));
        } else {
            lblCor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/1-33-512.png")));
        }
        lblCor.setVisible(true);
    }//GEN-LAST:event_txtConPassKeyReleased

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        txtConPass.requestFocus();
    }//GEN-LAST:event_txtPassActionPerformed

    private void txtPassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyReleased
        if (!txtPass.getText().equals(txtConPass.getText())) {
            lblCor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/Delete_remove_close_exit_trash_cancel_cross.png")));
        } else {
            lblCor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/1-33-512.png")));
        }
        lblCor.setVisible(true);
    }//GEN-LAST:event_txtPassKeyReleased

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        txtName.requestFocus();
    }//GEN-LAST:event_txtUserNameActionPerformed

    private void txtUserNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserNameKeyReleased
        try {
            TeacherController teacherController = ServerConnector.getServerConnector().getTeacherController();
            if (teacherController.checkUserName(txtUserName.getText())) {
                lblCorUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/Delete_remove_close_exit_trash_cancel_cross.png")));
            } else {
                lblCorUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/1-33-512.png")));
            }

            lblCorUser.setVisible(true);
        } catch (RemoteException ex) {
            Logger.getLogger(CreateNewTeacher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CreateNewTeacher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateNewTeacher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtUserNameKeyReleased

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed

    }//GEN-LAST:event_txtNameActionPerformed

    private void btCreateNewAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCreateNewAccountActionPerformed
        if (count == 1) {
            txtUserName.setEnabled(true);
            txtName.setEnabled(true);
            txtPass.setEnabled(true);
            txtConPass.setEnabled(true);
            jLabel11.setText("New Password");
            btCreateNewAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/saveDetails.jpg")));
            jLabel13.setVisible(true);
            jLabel14.setVisible(true);
            txtConPass.setVisible(true);
            btCreateNewAccount1.setEnabled(false);
            count = 2;
        } else {
            try {
                TeacherController teacherController = ServerConnector.getServerConnector().getTeacherController();
                String showInputDialog = JOptionPane.showInputDialog("Input Current password....");
                if (teacherController.checkPass(showInputDialog)) {
                    int res = checkFields();
                    if (res == 0) {
                        JOptionPane.showMessageDialog(this, "Passwords didn't Match....");
                    } else if (res == 1) {
                        JOptionPane.showMessageDialog(this, "All Fields must be Filled....");
                    } else if (res == 2) {
                        JOptionPane.showMessageDialog(this, "Passwords didn't Match....");
                    } else if (teacherController.checkUserName(userName) && (!cuttentUserName.equalsIgnoreCase(txtUserName.getText()))) {
                        JOptionPane.showMessageDialog(this, "User Name is Already Taken....");
                    } else {
                        Teacher currentTeacher = teacherController.searchTeacherByUserName(cuttentUserName);
                        Teacher teacher = new Teacher(currentTeacher.gettId(), userName, Name, gender, pass);
                        boolean isUpdated = teacherController.updateTeacher(teacher);

                        if (isUpdated) {
                            JOptionPane.showMessageDialog(this, "Update Successfull....");
                            jLabel11.setText("Password");
                            txtUserName.setEnabled(false);
                            txtName.setEnabled(false);
                            txtPass.setEnabled(false);
                            txtConPass.setEnabled(false);
                            jLabel13.setVisible(false);
                            jLabel14.setVisible(false);
                            txtConPass.setVisible(false);
                            lblCor.setVisible(false);
                            lblCorUser.setVisible(false);
                            btCreateNewAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/changeDetails.jpg")));
                            this.cuttentUserName = txtUserName.getText();
                            count = 1;
                            btCreateNewAccount1.setEnabled(true);
                        } else {
                            JOptionPane.showMessageDialog(this, "Update Failed....");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Password is Incorrect....");
                }

            } catch (RemoteException ex) {
                Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_btCreateNewAccountActionPerformed

    private void btCreateNewAccount1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCreateNewAccount1ActionPerformed
        try {
            TeacherController teacherController = ServerConnector.getServerConnector().getTeacherController();
            String showInputDialog = JOptionPane.showInputDialog("Enter Current Password....");
            if (teacherController.checkPass(showInputDialog)) {
                boolean deleteTeacher = teacherController.deleteTeacher(cuttentUserName);
                if (deleteTeacher) {
                    JOptionPane.showMessageDialog(this, "Deletion Failed....");
                } else {
                    JOptionPane.showMessageDialog(this, "Account Deleted Successfully....");
                    new LoginTeacher().setVisible(true);
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Password is Incorrect....");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btCreateNewAccount1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton JrbFemale;
    private javax.swing.JRadioButton JrbMale;
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btCreateNewAccount;
    private javax.swing.JButton btCreateNewAccount1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JLabel lblBackgrond;
    private javax.swing.JLabel lblCor;
    private javax.swing.JLabel lblCorUser;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTime;
    private javax.swing.JPasswordField txtConPass;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
