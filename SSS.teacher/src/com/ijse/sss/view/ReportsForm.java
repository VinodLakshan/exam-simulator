/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.view;

import com.ijse.sss.connector.ServerConnector;
import com.ijse.sss.controller.ExamController;
import com.ijse.sss.controller.NoteController;
import com.ijse.sss.controller.ResultController;
import com.ijse.sss.controller.StudentController;
import com.ijse.sss.model.Exam;
import com.ijse.sss.model.Results;
import com.ijse.sss.model.Student;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author NEOx
 */
public class ReportsForm extends javax.swing.JFrame {

    /**
     * Creates new form ReportsForm
     */
    private TeacherMain teacherMain;
    private ArrayList<Exam> moduledExam;
    private String stId;
    private String studentName;

    private String exmId;
    private String exmName;
    private String exmModule;
    private String exmDate;
    private int noOfExams =0;

    public ReportsForm(TeacherMain teacherMain) {
        initComponents();
        autoDateTime();
        addModules();
        setColomns();
        this.teacherMain = teacherMain;
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

    private void addModules() {
        try {
            NoteController noteController = ServerConnector.getServerConnector().getNoteController();
            ArrayList<String> module = noteController.getModule();

            for (String string : module) {
                txtModule.addItem(string);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel6 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btPrint = new javax.swing.JButton();
        btPrintAll = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtModule = new javax.swing.JComboBox<>();
        txtExamName = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btPrintExamResullt = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        btRefresh = new javax.swing.JButton();
        btPrintProgress = new javax.swing.JButton();
        btBackToNote = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblBackgrond = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(51, 51, 51));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("User Name");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 270, 90));

        jLabel7.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText(":");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 30, 90));

        txtUserName.setFont(new java.awt.Font("Microsoft Himalaya", 0, 40)); // NOI18N
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
        jPanel4.add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 540, 40));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Exam ID", "Exam Name", "Module", "Marks", "Pass Fail"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 1560, 500));

        btPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/printbt.jpg"))); // NOI18N
        btPrint.setEnabled(false);
        btPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrintActionPerformed(evt);
            }
        });
        jPanel4.add(btPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(1470, 610, 140, 60));

        btPrintAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/printAll.jpg"))); // NOI18N
        btPrintAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrintAllActionPerformed(evt);
            }
        });
        jPanel4.add(btPrintAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 610, 220, 60));

        jTabbedPane1.addTab("STUDENT RESULT", jPanel4);

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Exam Name");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 270, 110));

        jLabel11.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText(":");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 30, 110));

        txtModule.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        txtModule.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtModuleItemStateChanged(evt);
            }
        });
        jPanel5.add(txtModule, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 190, 40));

        txtExamName.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        txtExamName.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtExamNameItemStateChanged(evt);
            }
        });
        jPanel5.add(txtExamName, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 350, 40));

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student Name", "Marks", "Pass Fail"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 1560, 500));

        btPrintExamResullt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/printbt.jpg"))); // NOI18N
        btPrintExamResullt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrintExamResulltActionPerformed(evt);
            }
        });
        jPanel5.add(btPrintExamResullt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1470, 610, 140, 60));

        jTabbedPane1.addTab("  EXAM RESULTS", jPanel5);

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "null", "null", "null", "null", "null", "Title 6", "null"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jPanel7.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 1610, 570));

        btRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/refresh.jpg"))); // NOI18N
        btRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRefreshActionPerformed(evt);
            }
        });
        jPanel7.add(btRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 610, 140, 60));

        btPrintProgress.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/printbt.jpg"))); // NOI18N
        btPrintProgress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrintProgressActionPerformed(evt);
            }
        });
        jPanel7.add(btPrintProgress, new org.netbeans.lib.awtextra.AbsoluteConstraints(1470, 610, 140, 60));

        jTabbedPane1.addTab("  PROGRESS REPORT", jPanel7);

        jPanel6.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 1690, 720));

        btBackToNote.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/btBackToHome.jpg"))); // NOI18N
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
        jPanel6.add(btBackToNote, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 960, 290, 60));

        jLabel3.setFont(new java.awt.Font("Microsoft Himalaya", 1, 80)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("REPORTS");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 80, 290, 90));

        lblTime.setFont(new java.awt.Font("Microsoft Himalaya", 1, 100)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        lblTime.setText("08:00");
        jPanel6.add(lblTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 50, 330, 90));

        lblDate.setFont(new java.awt.Font("Microsoft Himalaya", 1, 80)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setText("08:00");
        jPanel6.add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 120, 310, 90));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 1860, 820));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1860, 170));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1860, 170));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 1860, 820));

        jLabel2.setFont(new java.awt.Font("Microsoft Himalaya", 3, 80)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/white transparent.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255), 4));
        jLabel2.setPreferredSize(new java.awt.Dimension(1920, 1000));
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1880, 1020));

        lblBackgrond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/technology-1106438_960_720darky.jpg"))); // NOI18N
        jPanel6.add(lblBackgrond, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1920, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btBackToNoteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btBackToNoteMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btBackToNoteMousePressed

    private void btBackToNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBackToNoteActionPerformed
        teacherMain.show();
        this.dispose();
    }//GEN-LAST:event_btBackToNoteActionPerformed

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
        defaultTableModel.setRowCount(0);
        try {
            String userName = txtUserName.getText();
            StudentController studentController = ServerConnector.getServerConnector().getStudentController();
            Student searchStudent = studentController.searchStudent(userName);
            if (searchStudent != null) {
                this.stId = searchStudent.getStId();
                this.studentName = searchStudent.getStName();
                ResultController resultController = ServerConnector.getServerConnector().getResultController();
                ArrayList<Results> searchStudentAllResults = resultController.searchStudentAllResults(searchStudent.getStId());

                ExamController examController = ServerConnector.getServerConnector().getExamController();
                for (Results searchStudentResult : searchStudentAllResults) {
                    Exam readExam = examController.readExam(searchStudentResult.geteId());
                    String modules = "";
                    if (readExam != null) {
                        for (int i = 0; i < readExam.getModules().length; i++) {
                            modules += readExam.getModules()[i] + ",";
                        }

                        modules = modules + "\b";
                        String passFail = "Fail";
                        if (searchStudentResult.getMarks() > 40) {
                            passFail = "Pass";
                        }
                        Object[] row = {searchStudentResult.geteId(), readExam.getExamName(), modules, searchStudentResult.getMarks(), passFail};
                        defaultTableModel.addRow(row);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Student not Foound....");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtUserNameActionPerformed

    private void txtUserNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserNameKeyReleased

    }//GEN-LAST:event_txtUserNameKeyReleased

    private void btPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrintActionPerformed
        String examId = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
        String examName = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 1);
        String module = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 2);
        String passFail = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 4);
        try {
            DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
            InputStream is = getClass().getResourceAsStream("../jasper/aStudentResults.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);
            HashMap hm = new HashMap();
            hm.put("stId", stId);
            hm.put("stName", studentName);
            hm.put("Exam ID", examId);
            hm.put("Exam Name", examName);
            hm.put("Module", module);
            hm.put("Marks", jTable1.getValueAt(jTable1.getSelectedRow(), 3));
            hm.put("passFail", passFail);

            JasperPrint jp = JasperFillManager.fillReport(jr, hm, new JRTableModelDataSource(defaultTableModel));
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btPrintActionPerformed

    private void btPrintAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrintAllActionPerformed
        try {
            DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
            InputStream is = getClass().getResourceAsStream("../jasper/studentResults.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);
            HashMap hm = new HashMap();
            hm.put("stId", stId);
            hm.put("stName", studentName);

            JasperPrint jp = JasperFillManager.fillReport(jr, hm, new JRTableModelDataSource(defaultTableModel));
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btPrintAllActionPerformed

    private void txtModuleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtModuleItemStateChanged
        String selModule = (String) txtModule.getSelectedItem();
        txtExamName.removeAllItems();
        try {
            ExamController examController = ServerConnector.getServerConnector().getExamController();
            moduledExam = examController.getModuledExam(selModule);

            for (Exam exam : moduledExam) {
                txtExamName.addItem(exam.getExamName());
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtModuleItemStateChanged

    private void txtExamNameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtExamNameItemStateChanged
        DefaultTableModel defaultTableModel = (DefaultTableModel) jTable2.getModel();
        defaultTableModel.setRowCount(0);
        String selExamName = (String) txtExamName.getSelectedItem();
        String selEid = null;
        for (Exam exam : moduledExam) {
            if (exam.getExamName().equalsIgnoreCase(selExamName)) {
                selEid = exam.getEid();
            }
        }

        try {
            ExamController examController = ServerConnector.getServerConnector().getExamController();
            Exam readExam = examController.readExam(selEid);
            if (readExam != null) {
                exmId = selEid;
                exmName = readExam.getExamName();
                String md = "";
                for (String module : readExam.getModules()) {
                    md += module;
                }
                md = md + "\b";
                exmModule = md;
                exmDate = readExam.getDate();
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultController resultController = ServerConnector.getServerConnector().getResultController();
            ArrayList<Results> searchExamResults = resultController.searchExamResults(selEid);

            StudentController studentController = ServerConnector.getServerConnector().getStudentController();
            for (Results searchExamResult : searchExamResults) {
                if (searchExamResult != null) {
                    Student searchStudent = studentController.searchStudent(searchExamResult.getStId());
                    String passFail = "Fail";
                    if (searchExamResult.getMarks() >= 35) {
                        passFail = "Pass";
                    }
                    Object[] row = {searchStudent.getStId(), searchStudent.getStName(), searchExamResult.getMarks(), passFail};
                    defaultTableModel.addRow(row);
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtExamNameItemStateChanged

    private void btPrintExamResulltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrintExamResulltActionPerformed
        try {
            DefaultTableModel defaultTableModel = (DefaultTableModel) jTable2.getModel();
            InputStream is = getClass().getResourceAsStream("../jasper/examResult.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);
            HashMap hm = new HashMap();
            hm.put("exmId", exmId);
            hm.put("exmName", exmName);
            hm.put("exmModule", exmModule);
            hm.put("exmDate", exmDate);

            JasperPrint jp = JasperFillManager.fillReport(jr, hm, new JRTableModelDataSource(defaultTableModel));
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btPrintExamResulltActionPerformed

    private void btPrintProgressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrintProgressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btPrintProgressActionPerformed

    private void btRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRefreshActionPerformed
        setDetails();
    }//GEN-LAST:event_btRefreshActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        btPrint.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBackToNote;
    private javax.swing.JButton btPrint;
    private javax.swing.JButton btPrintAll;
    private javax.swing.JButton btPrintExamResullt;
    private javax.swing.JButton btPrintProgress;
    private javax.swing.JButton btRefresh;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel lblBackgrond;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTime;
    private javax.swing.JComboBox<String> txtExamName;
    private javax.swing.JComboBox<String> txtModule;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
    private ArrayList<String> reportExamsId;
    private ArrayList<String> colNames = new ArrayList<>();

    private void setColomns() {
        try {
            ResultController resultController = ServerConnector.getServerConnector().getResultController();
            reportExamsId = resultController.reportExams();
            colNames.add("Student Id");
            colNames.add("Student Name");
            ExamController examController = ServerConnector.getServerConnector().getExamController();
            for (String reportExam : reportExamsId) {
                Exam readExam = examController.readExam(reportExam);
                if (readExam != null) {
                    colNames.add(readExam.getExamName());
                    noOfExams++;
                }
            }
            colNames.add("Average");
            colNames.add("Status");

            String[] columns = new String[colNames.size()];
            for (int i = 0; i < colNames.size(); i++) {
                columns[i] = colNames.get(i);
            }

            DefaultTableModel model = new DefaultTableModel(columns, 0);
            jTable3.setModel(model);

        } catch (RemoteException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException | MalformedURLException | ClassNotFoundException | ParseException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setDetails() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) jTable3.getModel();
        defaultTableModel.setRowCount(0);

        try {
            StudentController studentController = ServerConnector.getServerConnector().getStudentController();
            ArrayList<Student> allStudent = studentController.getAllStudent();

            ResultController resultController = ServerConnector.getServerConnector().getResultController();
            ArrayList<String> allExam = resultController.reportExams();
            for (Student student : allStudent) {
                ArrayList<String> studentMarks = new ArrayList<>();
                String stId = student.getStId();
                String stName = student.getStName();
                studentMarks.add(stId);
                studentMarks.add(stName);
                int total = 0;
                for (String eid : allExam) {
                    Results studentResults = resultController.searchStudentResults(eid, stId);
                    if (studentResults != null) {
                        studentMarks.add(Integer.toString(studentResults.getMarks()));
                        total += studentResults.getMarks();
                    } else {
                        studentMarks.add(Integer.toString(0));
                    }
                }

                double avg = total / noOfExams;
                String status = "";
                if (avg >= 75) {
                    status = "Excellent";
                } else if (avg >= 60) {
                    status = "Good";
                } else if (avg >= 40) {
                    status = "Average";
                } else {
                    status = "Weak";
                }
                studentMarks.add(Double.toString(avg));
                studentMarks.add(status);
                Object[] row = new Object[studentMarks.size()];
                row = studentMarks.toArray();

                defaultTableModel.addRow(row);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
