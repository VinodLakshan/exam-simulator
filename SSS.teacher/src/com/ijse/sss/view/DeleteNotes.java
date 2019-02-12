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
import java.util.HashMap;
import java.util.Map;
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
public class DeleteNotes extends javax.swing.JFrame {

    /**
     * Creates new form DeleteNotes
     */
    HashMap<String, String> topics;
    private TeacherMain teacherMain;
    private int count = 1;

    public DeleteNotes(TeacherMain teacherMain) {
        initComponents();
        autoDateTime();
        this.teacherMain = teacherMain;
        addModules();
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
                        Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }

    private void addModules() {
        try {
            DefaultListModel<String> defaultListModel = new DefaultListModel<>();
            lstModule.setModel(defaultListModel);
            defaultListModel.removeAllElements();
            NoteController noteController = ServerConnector.getServerConnector().getNoteController();
            ArrayList<String> module = noteController.getModule();

            for (String string : module) {
                defaultListModel.addElement(string);
            }

            btBack.setVisible(false);

        } catch (RemoteException ex) {
            Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel7 = new javax.swing.JPanel();
        deletebt = new javax.swing.JButton();
        lblTopic = new javax.swing.JLabel();
        lblNoteDate = new javax.swing.JLabel();
        lblTeacher = new javax.swing.JLabel();
        lblModule1 = new javax.swing.JLabel();
        btBackToNote = new javax.swing.JButton();
        btBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();
        lblModule = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstModule = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblBackgrond = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        deletebt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/deletebt.jpg"))); // NOI18N
        deletebt.setEnabled(false);
        deletebt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                deletebtMousePressed(evt);
            }
        });
        deletebt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtActionPerformed(evt);
            }
        });
        jPanel7.add(deletebt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1660, 960, 190, 60));

        lblTopic.setFont(new java.awt.Font("Microsoft Himalaya", 1, 70)); // NOI18N
        lblTopic.setForeground(new java.awt.Color(255, 255, 255));
        lblTopic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTopic.setText("topic");
        jPanel7.add(lblTopic, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, 1160, 60));

        lblNoteDate.setFont(new java.awt.Font("Microsoft Himalaya", 1, 50)); // NOI18N
        lblNoteDate.setForeground(new java.awt.Color(255, 255, 255));
        lblNoteDate.setText("nov 26 2016");
        jPanel7.add(lblNoteDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 960, 240, 70));

        lblTeacher.setFont(new java.awt.Font("Microsoft Himalaya", 1, 50)); // NOI18N
        lblTeacher.setForeground(new java.awt.Color(255, 255, 255));
        lblTeacher.setText("aassddff");
        jPanel7.add(lblTeacher, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 960, 290, 70));

        lblModule1.setFont(new java.awt.Font("Microsoft Himalaya", 1, 50)); // NOI18N
        lblModule1.setForeground(new java.awt.Color(255, 255, 255));
        lblModule1.setText("Teacher  :");
        jPanel7.add(lblModule1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 960, 180, 70));

        btBackToNote.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/btBackToNotes.jpg"))); // NOI18N
        btBackToNote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btBackToNoteMousePressed(evt);
            }
        });
        btBackToNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBackToNoteActionPerformed(evt);
            }
        });
        jPanel7.add(btBackToNote, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 960, 290, 60));

        btBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/backbt.jpg"))); // NOI18N
        btBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btBackMousePressed(evt);
            }
        });
        btBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBackActionPerformed(evt);
            }
        });
        jPanel7.add(btBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 950, 160, 70));

        txtNote.setEditable(false);
        txtNote.setColumns(20);
        txtNote.setFont(new java.awt.Font("Monospaced", 1, 20)); // NOI18N
        txtNote.setRows(5);
        jScrollPane1.setViewportView(txtNote);

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 300, 1200, 620));

        lblModule.setFont(new java.awt.Font("Microsoft Himalaya", 1, 70)); // NOI18N
        lblModule.setForeground(new java.awt.Color(255, 255, 255));
        lblModule.setText("Modules");
        jPanel7.add(lblModule, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 240, 60));

        lstModule.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        lstModule.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstModule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstModuleMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lstModuleMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lstModuleMousePressed(evt);
            }
        });
        lstModule.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstModuleValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(lstModule);

        jPanel7.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 550, 640));

        jLabel3.setFont(new java.awt.Font("Microsoft Himalaya", 1, 100)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("DELETE NOTES");
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 680, 110));

        lblTime.setFont(new java.awt.Font("Microsoft Himalaya", 1, 100)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        lblTime.setText("08:00");
        jPanel7.add(lblTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 50, 330, 90));

        lblDate.setFont(new java.awt.Font("Microsoft Himalaya", 1, 80)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setText("08:00");
        jPanel7.add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 120, 310, 90));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 950, 1260, 80));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 950, 1260, 80));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 590, 820));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 590, 820));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, 1260, 730));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, 1260, 730));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1860, 170));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1860, 170));

        jLabel2.setFont(new java.awt.Font("Microsoft Himalaya", 3, 80)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/white transparent.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255), 4));
        jLabel2.setPreferredSize(new java.awt.Dimension(1920, 1000));
        jPanel7.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1880, 1020));

        lblBackgrond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/technology-1106438_960_720.jpg"))); // NOI18N
        jPanel7.add(lblBackgrond, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btBackToNoteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btBackToNoteMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btBackToNoteMousePressed

    private void btBackToNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBackToNoteActionPerformed
        new NotesForm(teacherMain).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btBackToNoteActionPerformed

    private void btBackMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btBackMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btBackMousePressed

    private void btBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBackActionPerformed
        addModules();
        lblModule.setText("Modules");
        count = 1;
        btBack.setVisible(false);
    }//GEN-LAST:event_btBackActionPerformed

    private void lstModuleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstModuleMouseClicked
        if (count == 1) {
            String module = lstModule.getSelectedValue();
            addTopics(module);
            btBack.setVisible(true);
            lblModule.setText("Topics");
            count = 2;
        } else if (count == 2) {
            if (lstModule.getSelectedValue() != null) {
                previewNote();
            }
        }
    }//GEN-LAST:event_lstModuleMouseClicked

    private void lstModuleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstModuleMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lstModuleMouseEntered

    private void lstModuleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstModuleMousePressed

    }//GEN-LAST:event_lstModuleMousePressed

    private void lstModuleValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstModuleValueChanged

    }//GEN-LAST:event_lstModuleValueChanged

    private void deletebtMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebtMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_deletebtMousePressed

    private void deletebtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtActionPerformed
        int res = JOptionPane.showConfirmDialog(this, "Are You Sure You want to Delete..??");
        if (res == 0) {

            String aTopic = lblTopic.getText();
            String aNid = null;
            for (Map.Entry<String, String> entry : topics.entrySet()) {
                if (entry.getValue().equals(aTopic)) {
                    aNid = entry.getKey();
                }
            }

            try {
                NoteController noteController = ServerConnector.getServerConnector().getNoteController();
                boolean isDeleted = noteController.deleteNote(aNid);

                if (isDeleted) {
                    JOptionPane.showMessageDialog(this, "Note Deletion Failed....");
                } else {
                    JOptionPane.showMessageDialog(this, "Note Deleted Successfully....");
                    StudentController studentController = ServerConnector.getServerConnector().getStudentController();
                    String prevTopic = "\"" + aTopic + "\"";
                    studentController.previewNotification("A Note " + prevTopic + " Deleted....");
                    this.dispose();
                    new NotesForm(teacherMain).setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_deletebtActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBack;
    private javax.swing.JButton btBackToNote;
    private javax.swing.JButton deletebt;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblBackgrond;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblModule;
    private javax.swing.JLabel lblModule1;
    private javax.swing.JLabel lblNoteDate;
    private javax.swing.JLabel lblTeacher;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTopic;
    private javax.swing.JList<String> lstModule;
    private javax.swing.JTextArea txtNote;
    // End of variables declaration//GEN-END:variables
    private void addTopics(String module) {
        try {
            DefaultListModel<String> defaultListModel = new DefaultListModel<>();
            lstModule.setModel(defaultListModel);
            defaultListModel.removeAllElements();
            NoteController noteController = ServerConnector.getServerConnector().getNoteController();
            topics = noteController.getTopics(module);

            for (Map.Entry<String, String> entry : topics.entrySet()) {
                defaultListModel.addElement(entry.getValue());
            }

        } catch (RemoteException ex) {
            Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException | MalformedURLException | ClassNotFoundException ex) {
            Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void previewNote() {
        String searchTopic = lstModule.getSelectedValue();
        String searchNid = null;
        for (Map.Entry<String, String> entry : topics.entrySet()) {
            if (entry.getValue().equals(searchTopic)) {
                searchNid = entry.getKey();
            }
        }

        try {
            NoteController noteController = ServerConnector.getServerConnector().getNoteController();
            Note note = noteController.searchNote(searchNid);
            lblTopic.setText(note.getTopic());
            String teacherUserName = note.getUserName();
            TeacherController teacherController = ServerConnector.getServerConnector().getTeacherController();
            Teacher teacher = teacherController.searchTeacherByUserName(teacherUserName);

            String teacherName = teacher.gettName();
            String date = note.getDate();
            txtNote.setText(note.getDescription());
            lblTeacher.setText(teacherName);
            lblNoteDate.setText(date);
            deletebt.setEnabled(true);
        } catch (IOException ex) {
            Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DeleteNotes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
