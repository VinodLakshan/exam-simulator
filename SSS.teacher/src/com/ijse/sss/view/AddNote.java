/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.view;

import com.ijse.sss.connector.ServerConnector;
import com.ijse.sss.controller.NoteController;
import com.ijse.sss.controller.StudentController;
import com.ijse.sss.controller.TeacherController;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import com.ijse.sss.model.Note;
import com.ijse.sss.model.Teacher;

/**
 *
 * @author NEOx
 */
public class AddNote extends javax.swing.JFrame {

    /**
     * Creates new form AddNote
     */
    private String nid = "n1";
    private String moduleName;
    private String topic;
    private String description;
    private String userName;
    private String date;
    private TeacherMain teacherMain;

    public AddNote(TeacherMain teacherMain) {
        initComponents();
        this.teacherMain=teacherMain;
        setExtendedState(MAXIMIZED_BOTH);
        autoDateTime();
        addTeacherList();
        addModules();
        txtModule.requestFocus();
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
                        Logger.getLogger(AddNote.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }

    private void addTeacherList() {
        try {
            TeacherController teacherController = ServerConnector.getServerConnector().getTeacherController();
            ArrayList<Teacher> allTeachers = teacherController.getAllTeachers();
            
            for (Teacher allTeacher : allTeachers) {
                cbTeacher.addItem(allTeacher.getUserName());
            }
            cbTeacher.setSelectedItem(teacherMain.getTitle());
        } catch (RemoteException ex) {
            Logger.getLogger(AddNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(AddNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AddNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AddNote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean checkFields() {
        moduleName = (String) txtModule.getSelectedItem();
        topic = txtTopic.getText();
        description = txtDescription.getText();
        date = lblDate.getText();
        userName = (String) cbTeacher.getSelectedItem();
        if (topic.equals("") || description.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private void addModules() {
        try {
            DefaultListModel<String> defaultListModel = new DefaultListModel<>();
            NoteController noteController = ServerConnector.getServerConnector().getNoteController();
            ArrayList<String> modules = noteController.getModule();

            for (String module : modules) {
                txtModule.addItem(module);
            }

        } catch (RemoteException ex) {
            Logger.getLogger(AddNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(AddNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AddNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AddNote.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        btCreateNewAccount = new javax.swing.JButton();
        txtModule = new javax.swing.JComboBox<>();
        txtDescriptions = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        cbTeacher = new javax.swing.JComboBox<>();
        lblDate = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        btCancel = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTopic = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblBackgrond = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btCreateNewAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/createNotebt.jpg"))); // NOI18N
        btCreateNewAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCreateNewAccountActionPerformed(evt);
            }
        });
        jPanel1.add(btCreateNewAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(1490, 900, 370, 100));

        txtModule.setEditable(true);
        txtModule.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jPanel1.add(txtModule, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, 540, 50));

        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Microsoft Himalaya", 0, 30)); // NOI18N
        txtDescription.setRows(5);
        txtDescriptions.setViewportView(txtDescription);

        jPanel1.add(txtDescriptions, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 380, 1080, 440));

        cbTeacher.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jPanel1.add(cbTeacher, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 850, 540, 50));

        lblDate.setFont(new java.awt.Font("Microsoft Himalaya", 1, 80)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setText("08:00");
        jPanel1.add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1570, 100, 310, 90));

        lblTime.setFont(new java.awt.Font("Microsoft Himalaya", 1, 100)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        lblTime.setText("08:00");
        jPanel1.add(lblTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(1550, 40, 330, 90));

        btCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/cancelbt.jpg"))); // NOI18N
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });
        jPanel1.add(btCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 900, 220, 100));

        jLabel11.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Teacher User Name");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 830, 390, 100));

        jLabel12.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText(":");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 830, 30, 100));

        jLabel9.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText(":");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 360, 30, 100));

        jLabel10.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Description");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 300, 100));

        jLabel1.setFont(new java.awt.Font("Microsoft Himalaya", 3, 100)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CREATE A NOTE");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 800, 110));

        jLabel5.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Module Name");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 300, 100));

        jLabel6.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText(":");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, 30, 100));

        jLabel4.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText(":");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 280, 30, 100));

        txtTopic.setFont(new java.awt.Font("Microsoft Himalaya", 0, 50)); // NOI18N
        txtTopic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTopicActionPerformed(evt);
            }
        });
        jPanel1.add(txtTopic, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, 770, 50));

        jLabel3.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Topic");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 270, 100));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 1860, 840));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 1860, 840));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1860, 150));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1860, 150));

        jLabel2.setFont(new java.awt.Font("Microsoft Himalaya", 3, 80)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/technology-1106438_960_720darky.jpg"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255), 4));
        jLabel2.setPreferredSize(new java.awt.Dimension(1920, 1000));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1880, 1020));

        lblBackgrond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/technology-1106438_960_720darky.jpg"))); // NOI18N
        jPanel1.add(lblBackgrond, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        new NotesForm(teacherMain).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btCancelActionPerformed

    private void txtTopicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTopicActionPerformed
        txtDescription.requestFocus();
    }//GEN-LAST:event_txtTopicActionPerformed

    private void btCreateNewAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCreateNewAccountActionPerformed
        if (checkFields()) {
            JOptionPane.showMessageDialog(this, "Fill all Fields to Create the Note....");
        } else {
            try {

                Note note = new Note(nid, topic, description, moduleName, userName, date);
                NoteController noteController = ServerConnector.getServerConnector().getNoteController();
                boolean writeNote = noteController.writeNote(note);

                if (writeNote) {
                    JOptionPane.showMessageDialog(this, "Note Created Successfully.....");
                    StudentController studentController = ServerConnector.getServerConnector().getStudentController();
                    String newTopic = "\""+topic +"\"";
                    studentController.previewNotification("New Note "+newTopic+" Added....");
                    this.dispose();
                    new NotesForm(teacherMain).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Note Creation Failed....");
                }
            } catch (IOException ex) {
                Logger.getLogger(AddNote.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(AddNote.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddNote.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(AddNote.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btCreateNewAccountActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        new NotesForm(teacherMain).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btCreateNewAccount;
    private javax.swing.JComboBox<String> cbTeacher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBackgrond;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTime;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JScrollPane txtDescriptions;
    private javax.swing.JComboBox<String> txtModule;
    private javax.swing.JTextField txtTopic;
    // End of variables declaration//GEN-END:variables

}
