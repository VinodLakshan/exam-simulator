/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.view;

import com.ijse.sss.connector.ServerConnector;
import com.ijse.sss.controller.ExamController;
import com.ijse.sss.controller.NoteController;
import com.ijse.sss.controller.QuestionController;
import com.ijse.sss.model.Exam;
import com.ijse.sss.model.Question;
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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NEOx
 */
public class ViewExams extends javax.swing.JFrame {

    /**
     * Creates new form ViewExams
     */
    private String exName;
    private String date;
    private String time;
    private String duration;
    private String[] modules;
    private String[] qIds;
    private String selectedQid;
    private TeacherMain teacherMain;
    private ArrayList<Question> moduledQuestions;
    ArrayList<Exam> moduledExam;
    private DefaultTableModel defaultTableModel;
    private ArrayList<Exam> allQs = new ArrayList<>();

    public ViewExams(TeacherMain teacherMain) {
        initComponents();
        autoDateTime();
        addModules();
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
                        Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

    }

    private void addModules() {
        try {
            jRadioButton1.setVisible(false);
            jRadioButton2.setVisible(false);
            jRadioButton3.setVisible(false);
            jRadioButton4.setVisible(false);
            jLabel27.setVisible(false);

            ExamController examController = ServerConnector.getServerConnector().getExamController();
            ArrayList<String> modules = examController.getAllExamModules();

            for (String module : modules) {
                txtModule1.addItem(module);
            }

        } catch (RemoteException ex) {
            Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);

        } catch (NotBoundException ex) {
            Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);

        } catch (MalformedURLException ex) {
            Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ParseException ex) {
            Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lblExamCode = new javax.swing.JLabel();
        txtDate = new javax.swing.JLabel();
        cbHours = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        cbMinutes = new javax.swing.JLabel();
        cbAmPm = new javax.swing.JLabel();
        cbMinutes1 = new javax.swing.JLabel();
        cbHours1 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblCount = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtModule1 = new javax.swing.JComboBox<>();
        txtExamName = new javax.swing.JComboBox<>();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblBackgrond = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Exam Code");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 470, 240, 100));

        jLabel32.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText(":");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 470, 30, 100));

        lblExamCode.setFont(new java.awt.Font("Microsoft Himalaya", 1, 45)); // NOI18N
        lblExamCode.setForeground(new java.awt.Color(255, 255, 255));
        lblExamCode.setText("code");
        jPanel1.add(lblExamCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 490, 320, 60));

        txtDate.setFont(new java.awt.Font("Microsoft Himalaya", 1, 45)); // NOI18N
        txtDate.setForeground(new java.awt.Color(255, 255, 255));
        txtDate.setText("Date");
        jPanel1.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, 500, 60));

        cbHours.setFont(new java.awt.Font("Microsoft Himalaya", 1, 45)); // NOI18N
        cbHours.setForeground(new java.awt.Color(255, 255, 255));
        cbHours.setText("ho");
        jPanel1.add(cbHours, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, 40, 60));

        jLabel29.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText(":");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, 30, 120));

        cbMinutes.setFont(new java.awt.Font("Microsoft Himalaya", 1, 45)); // NOI18N
        cbMinutes.setForeground(new java.awt.Color(255, 255, 255));
        cbMinutes.setText("min");
        jPanel1.add(cbMinutes, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 330, 70, 60));

        cbAmPm.setFont(new java.awt.Font("Microsoft Himalaya", 1, 45)); // NOI18N
        cbAmPm.setForeground(new java.awt.Color(255, 255, 255));
        cbAmPm.setText("AmPm");
        jPanel1.add(cbAmPm, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 330, 120, 60));

        cbMinutes1.setFont(new java.awt.Font("Microsoft Himalaya", 1, 45)); // NOI18N
        cbMinutes1.setForeground(new java.awt.Color(255, 255, 255));
        cbMinutes1.setText("mi");
        jPanel1.add(cbMinutes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 390, 50, 50));

        cbHours1.setFont(new java.awt.Font("Microsoft Himalaya", 1, 45)); // NOI18N
        cbHours1.setForeground(new java.awt.Color(255, 255, 255));
        cbHours1.setText("ho");
        jPanel1.add(cbHours1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 390, 40, 50));

        jLabel21.setFont(new java.awt.Font("Microsoft Himalaya", 3, 40)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Hours");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, 90, 50));

        jLabel22.setFont(new java.awt.Font("Microsoft Himalaya", 3, 40)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Minutes");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 390, 120, 50));

        lblCount.setFont(new java.awt.Font("Microsoft Himalaya", 1, 45)); // NOI18N
        lblCount.setForeground(new java.awt.Color(255, 255, 255));
        lblCount.setText("hours");
        jPanel1.add(lblCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 440, 90, 60));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/btBackToExams.jpg"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 960, 290, 60));

        txtModule1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        txtModule1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtModule1ItemStateChanged(evt);
            }
        });
        jPanel1.add(txtModule1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 190, 40));

        txtExamName.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        txtExamName.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtExamNameItemStateChanged(evt);
            }
        });
        jPanel1.add(txtExamName, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, 350, 40));

        jRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton3.setText("Answer 4");
        jRadioButton3.setOpaque(false);
        jPanel1.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 910, 330, 40));

        jRadioButton4.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jRadioButton4.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton4.setText("Answer 3");
        jRadioButton4.setOpaque(false);
        jPanel1.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 910, 330, 40));

        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setText("Answer 2");
        jRadioButton2.setOpaque(false);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 830, 330, 40));

        jRadioButton1.setBackground(new java.awt.Color(51, 153, 0));
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Answer 1");
        jPanel1.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 830, 330, 40));

        jLabel27.setBackground(new java.awt.Color(51, 153, 0));
        jLabel27.setOpaque(true);
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 830, 330, 40));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 590, 860, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Question ID", "Question", "Module"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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
        jScrollPane2.setViewportView(jTable1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 210, 910, 710));

        jLabel23.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Questions");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 240, 100));

        jLabel24.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText(":");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 420, 30, 100));

        jLabel20.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText(":");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 30, 110));

        jLabel19.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Duration");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 240, 110));

        jLabel17.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Time");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 270, 80));

        jLabel18.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText(":");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, 30, 100));

        jLabel15.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Date");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 270, 100));

        jLabel16.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText(":");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, 30, 100));

        lblDate.setFont(new java.awt.Font("Microsoft Himalaya", 1, 80)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setText("08:00");
        jPanel1.add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1570, 100, 310, 90));

        lblTime.setFont(new java.awt.Font("Microsoft Himalaya", 1, 100)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        lblTime.setText("08:00");
        jPanel1.add(lblTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(1550, 40, 330, 90));

        jLabel1.setFont(new java.awt.Font("Microsoft Himalaya", 3, 100)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DELETE EXAM");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 800, 110));

        jLabel4.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText(":");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 30, 100));

        jLabel3.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Exam Name");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 270, 100));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 950, 950, 80));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 900, 460));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 190, 950, 750));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 900, 370));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1860, 150));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1860, 150));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 900, 370));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 190, 950, 750));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 900, 460));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 950, 950, 80));

        jLabel2.setFont(new java.awt.Font("Microsoft Himalaya", 3, 80)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/technology-1106438_960_720darky.jpg"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255), 4));
        jLabel2.setPreferredSize(new java.awt.Dimension(1920, 1000));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1880, 1020));

        lblBackgrond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/technology-1106438_960_720darky.jpg"))); // NOI18N
        jPanel1.add(lblBackgrond, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1920, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtModule1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtModule1ItemStateChanged
try {
            ExamController examController = ServerConnector.getServerConnector().getExamController();
            moduledExam = examController.getModuledExam((String) txtModule1.getSelectedItem());
            txtExamName.removeAllItems();
            for (Exam exam : moduledExam) {
                txtExamName.addItem(exam.getExamName());
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtModule1ItemStateChanged

    private void txtExamNameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtExamNameItemStateChanged
        for (Exam exam : moduledExam) {
            if (exam.getExamName().equals(txtExamName.getSelectedItem())) {
                try {
                    String[] time = exam.getTime().split(":");
                    String[] duration = exam.getDuration().split(":");
                    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                    dtm.setRowCount(0);
                    this.defaultTableModel = dtm;
                    QuestionController questionController = ServerConnector.getServerConnector().getQuestionController();
                    ArrayList<Question> readAllQuestions = questionController.readAllQuestions();
                    for (int i = 0; i < exam.getQuestions().length; i++) {
                        for (Question readQuestion : readAllQuestions) {
                            if (readQuestion.getQid().equals(exam.getQuestions()[i])) {
                                Object[] row = {readQuestion.getQid(), readQuestion.getQuestion(), readQuestion.getModule()};
                                dtm.addRow(row);
                            }
                        }
                    }
                    
                    txtDate.setText(exam.getDate());
                    txtDate.setText(exam.getDate());
                    cbHours.setText(time[0]);
                    cbMinutes.setText(time[1]);
                    cbAmPm.setText(time[2]);
                    cbHours1.setText(duration[0]);
                    cbMinutes1.setText(duration[1]);
                    lblCount.setText(Integer.toString(exam.getQuestions().length));
                    lblExamCode.setText(exam.getExamCode());
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NotBoundException ex) {
                    Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_txtExamNameItemStateChanged

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            String selQid = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            QuestionController questionController = ServerConnector.getServerConnector().getQuestionController();
            ArrayList<Question> readAllQuestions = questionController.readAllQuestions();
            for (Question readQuestion : readAllQuestions) {
                if (readQuestion.getQid().equals(selQid)) {
                    jTextArea1.setText(readQuestion.getQuestion());
                    jRadioButton1.setText(readQuestion.getCorrectAnswer());
                    jRadioButton2.setText(readQuestion.getAnswer1());
                    jRadioButton3.setText(readQuestion.getAnswer2());
                    jRadioButton4.setText(readQuestion.getAnswer3());

                    jRadioButton1.setVisible(true);
                    jRadioButton2.setVisible(true);
                    jRadioButton3.setVisible(true);
                    jRadioButton4.setVisible(true);
                    jLabel27.setVisible(true);
                }
            }

        } catch (RemoteException ex) {
            Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ViewExams.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new ExamForm(teacherMain).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cbAmPm;
    private javax.swing.JLabel cbHours;
    private javax.swing.JLabel cbHours1;
    private javax.swing.JLabel cbMinutes;
    private javax.swing.JLabel cbMinutes1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblBackgrond;
    private javax.swing.JLabel lblCount;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblExamCode;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel txtDate;
    private javax.swing.JComboBox<String> txtExamName;
    private javax.swing.JComboBox<String> txtModule1;
    // End of variables declaration//GEN-END:variables

    private void showQuestion(String question) {
        for (Question moduledQuestion : moduledQuestions) {
            if (moduledQuestion.getQuestion().equals(question)) {
                jTextArea1.setText(moduledQuestion.getQuestion());
                jRadioButton1.setText(moduledQuestion.getCorrectAnswer());
                jRadioButton2.setText(moduledQuestion.getAnswer1());
                jRadioButton3.setText(moduledQuestion.getAnswer2());
                jRadioButton4.setText(moduledQuestion.getAnswer3());

                jRadioButton1.setVisible(true);
                jRadioButton2.setVisible(true);
                jRadioButton3.setVisible(true);
                jRadioButton4.setVisible(true);

                selectedQid = moduledQuestion.getQid();
            }
        }
    }

    private ArrayList<String> removeDuplicates(ArrayList<String> allModules) {
        int count = allModules.size();
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                if (allModules.get(i).equals(allModules.get(j))) {
                    allModules.remove(j--);
                    count--;
                }
            }
        }
        return allModules;
    }
}
