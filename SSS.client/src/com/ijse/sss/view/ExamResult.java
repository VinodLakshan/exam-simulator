/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.view;

import com.ijse.sss.connector.ServerConnector;
import com.ijse.sss.controller.ExamController;
import com.ijse.sss.controller.ResultController;
import com.ijse.sss.controller.StudentController;
import com.ijse.sss.model.Exam;
import com.ijse.sss.model.Question;
import com.ijse.sss.model.Results;
import com.ijse.sss.model.Student;
import java.awt.Color;
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
import javax.swing.table.DefaultTableModel;
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
public class ExamResult extends javax.swing.JFrame {

    /**
     * Creates new form ExamResult
     */
    private MainForm mainForm;
    private ArrayList<Question> questions = new ArrayList();
    private String[] studentAnswers;
    private String examName;
    private String stId;
    private String eId;
    private String stName;
    private int noOfQ = 0;
    private int answered = 0;
    private int notAnswered = 0;
    private int correctAnswered = 0;
    private int inCorrectAnswered = 0;
    int finalMarksInt =0;

    public ExamResult(MainForm mainForm, ArrayList<Question> questions, String[] studentAnswers, String examName, int answered, String stName, String eId,String stId) {
        initComponents();
        autoDateTime();
        this.eId = eId;
        this.stId = stId;
        this.mainForm = mainForm;
        this.questions = questions;
        this.studentAnswers = studentAnswers;
        this.examName = examName;
        this.noOfQ = questions.size();
        this.answered = answered;
        this.stName = stName;
        setDetails();
        checkAnswers();
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
                        Logger.getLogger(ExaminationForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }

    private void setDetails() {
        txtExamName.setText(examName);
        txtDate.setText(lblDate.getText());
        txtStName.setText(stName);
        String AllModules = "";
        ArrayList<String> modules = new ArrayList<>();
        for (Question question : questions) {
            if (!modules.contains(question.getModule())) {
                modules.add(question.getModule());
            }
        }

        for (String module : modules) {
            AllModules += module + ",";
        }
        AllModules = AllModules + "\b";

        txtModule.setText(AllModules);
        txtNoOfQ.setText(Integer.toString(noOfQ));
        ansedQ.setText(Integer.toString(answered));
        notAnsedQ.setText(Integer.toString(noOfQ - answered));
    }

    private void checkAnswers() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
        defaultTableModel.setRowCount(0);
        for (int i = 0; i < noOfQ; i++) {
            if (questions.get(i).getCorrectAnswer().equalsIgnoreCase(studentAnswers[i])) {
                Object[] row = {questions.get(i).getQid(), questions.get(i).getQuestion(), questions.get(i).getModule(), "Correct"};
                defaultTableModel.addRow(row);
                correctAnswered++;
            } else {
                if (studentAnswers[i] == null) {
                    Object[] row = {questions.get(i).getQid(), questions.get(i).getQuestion(), questions.get(i).getModule(), "Not Answered"};
                    defaultTableModel.addRow(row);
                    inCorrectAnswered++;
                } else {
                    Object[] row = {questions.get(i).getQid(), questions.get(i).getQuestion(), questions.get(i).getModule(), "Incorrect"};
                    defaultTableModel.addRow(row);
                    inCorrectAnswered++;
                }
            }
        }
        corAnsedQ.setText(Integer.toString(correctAnswered));
        incorAnsrdQ.setText(Integer.toString(inCorrectAnswered));
        finalMarksInt = (correctAnswered * 100) / noOfQ;
        String finalMarkPrecentage = Integer.toString(finalMarksInt);
        finalMarks.setText(finalMarkPrecentage + " %");
        
        if (finalMarksInt >= 35) {
            lblStatus.setForeground(Color.BLUE);
            lblStatus.setText("Exam Passed....");
        }else{
            lblStatus.setForeground(Color.RED);
            lblStatus.setText("Exam Failed....");
        }
        
        if (!examName.equalsIgnoreCase("Quick Exam")) {
            try {
                StudentController studentController = ServerConnector.getServerConnector().getStudentController();
                Student student = studentController.searchStudent(mainForm.getTitle());
            
                ResultController resultController = ServerConnector.getServerConnector().getResultController();
                Results results = new Results(eId, student.getStId(), finalMarksInt);
                resultController.writeResult(results);
            } catch (RemoteException ex) {
                Logger.getLogger(ExamResult.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(ExamResult.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ExamResult.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ExamResult.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ExamResult.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ExamResult.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();
        finalMarks = new javax.swing.JLabel();
        corAnsedQ = new javax.swing.JLabel();
        incorAnsrdQ = new javax.swing.JLabel();
        notAnsedQ = new javax.swing.JLabel();
        ansedQ = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtNoOfQ = new javax.swing.JTextField();
        txtModule = new javax.swing.JTextField();
        txtStName = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtDate = new datechooser.beans.DateChooserCombo();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtExamName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblBackgrond = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblStatus.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lblStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 950, 560, 90));

        finalMarks.setFont(new java.awt.Font("Microsoft Himalaya", 1, 70)); // NOI18N
        finalMarks.setForeground(new java.awt.Color(255, 255, 255));
        finalMarks.setText(":");
        jPanel1.add(finalMarks, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 900, 190, 90));

        corAnsedQ.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        corAnsedQ.setForeground(new java.awt.Color(255, 255, 255));
        corAnsedQ.setText(":");
        jPanel1.add(corAnsedQ, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 780, 100, 70));

        incorAnsrdQ.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        incorAnsrdQ.setForeground(new java.awt.Color(255, 255, 255));
        incorAnsrdQ.setText(":");
        jPanel1.add(incorAnsrdQ, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 840, 100, 70));

        notAnsedQ.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        notAnsedQ.setForeground(new java.awt.Color(255, 255, 255));
        notAnsedQ.setText(":");
        jPanel1.add(notAnsedQ, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 710, 100, 70));

        ansedQ.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        ansedQ.setForeground(new java.awt.Color(255, 255, 255));
        ansedQ.setText(":");
        jPanel1.add(ansedQ, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 640, 100, 70));

        jLabel38.setFont(new java.awt.Font("Microsoft Himalaya", 1, 70)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Final marks precentage");
        jPanel1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 900, 540, 90));

        jLabel39.setFont(new java.awt.Font("Microsoft Himalaya", 1, 70)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText(":");
        jPanel1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 900, 30, 90));

        jLabel36.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Incorrectly Answered");
        jPanel1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 830, 420, 90));

        jLabel37.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText(":");
        jPanel1.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 830, 30, 90));

        jLabel34.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Correctly Answered");
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 770, 370, 90));

        jLabel35.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText(":");
        jPanel1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 770, 30, 90));

        jLabel27.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Not Answered Questions");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 700, 440, 90));

        jLabel32.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText(":");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 700, 30, 90));

        jLabel25.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Answered Questions");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 630, 370, 90));

        jLabel26.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText(":");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 630, 30, 90));

        txtNoOfQ.setEditable(false);
        txtNoOfQ.setFont(new java.awt.Font("Microsoft Himalaya", 0, 40)); // NOI18N
        txtNoOfQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoOfQActionPerformed(evt);
            }
        });
        txtNoOfQ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoOfQKeyReleased(evt);
            }
        });
        jPanel1.add(txtNoOfQ, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 530, 130, 40));

        txtModule.setEditable(false);
        txtModule.setFont(new java.awt.Font("Microsoft Himalaya", 0, 40)); // NOI18N
        txtModule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModuleActionPerformed(evt);
            }
        });
        txtModule.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtModuleKeyReleased(evt);
            }
        });
        jPanel1.add(txtModule, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 450, 540, 40));

        txtStName.setEditable(false);
        txtStName.setFont(new java.awt.Font("Microsoft Himalaya", 0, 40)); // NOI18N
        txtStName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStNameActionPerformed(evt);
            }
        });
        txtStName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStNameKeyReleased(evt);
            }
        });
        jPanel1.add(txtStName, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, 540, 40));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/printbt.jpg"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 960, 140, 60));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/finishbt.jpg"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1720, 960, 140, 60));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Question ID", "Question", "Module", "Correct / Incorrect"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, 240, 90));

        jLabel24.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText(":");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, 30, 90));

        jLabel20.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText(":");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 420, 30, 110));

        jLabel19.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Module");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 240, 110));

        jLabel17.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Student Name");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 270, 70));

        jLabel18.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText(":");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, 30, 90));

        txtDate.setCurrentView(new datechooser.view.appearance.AppearancesList("Light",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 13),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 13),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 13),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 13),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 13),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 13),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    txtDate.setCalendarPreferredSize(new java.awt.Dimension(361, 209));
    txtDate.setWeekStyle(datechooser.view.WeekDaysStyle.FULL);
    txtDate.setFieldFont(new java.awt.Font("Microsoft Himalaya", java.awt.Font.PLAIN, 40));
    jPanel1.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, 540, 40));

    jLabel15.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
    jLabel15.setForeground(new java.awt.Color(255, 255, 255));
    jLabel15.setText("Date");
    jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 270, 100));

    jLabel16.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
    jLabel16.setForeground(new java.awt.Color(255, 255, 255));
    jLabel16.setText(":");
    jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, 30, 100));

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
    jLabel1.setText("RESULTS");
    jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 800, 110));

    jLabel4.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
    jLabel4.setForeground(new java.awt.Color(255, 255, 255));
    jLabel4.setText(":");
    jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 30, 100));

    txtExamName.setEditable(false);
    txtExamName.setFont(new java.awt.Font("Microsoft Himalaya", 0, 40)); // NOI18N
    txtExamName.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtExamNameActionPerformed(evt);
        }
    });
    txtExamName.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtExamNameKeyReleased(evt);
        }
    });
    jPanel1.add(txtExamName, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 540, 40));

    jLabel3.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
    jLabel3.setForeground(new java.awt.Color(255, 255, 255));
    jLabel3.setText("Exam Name");
    jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 270, 100));

    jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
    jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 950, 950, 80));

    jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
    jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 610, 900, 420));

    jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
    jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 610, 900, 420));

    jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
    jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 190, 950, 750));

    jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
    jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 190, 950, 750));

    jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
    jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 900, 410));

    jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
    jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 900, 410));

    jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
    jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 950, 950, 80));

    jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
    jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1860, 150));

    jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
    jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1860, 150));

    jLabel2.setFont(new java.awt.Font("Microsoft Himalaya", 3, 80)); // NOI18N
    jLabel2.setForeground(new java.awt.Color(204, 204, 255));
    jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/technology-1106438_960_720darky.jpg"))); // NOI18N
    jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255), 4));
    jLabel2.setPreferredSize(new java.awt.Dimension(1920, 1000));
    jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1880, 1020));

    lblBackgrond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/technology-1106438_960_720darky.jpg"))); // NOI18N
    jPanel1.add(lblBackgrond, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

    jLabel13.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
    jLabel13.setForeground(new java.awt.Color(255, 255, 255));
    jLabel13.setText("Exam Name");
    jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 270, 100));

    jLabel14.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
    jLabel14.setForeground(new java.awt.Color(255, 255, 255));
    jLabel14.setText(":");
    jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 190, 30, 100));

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

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
            String passFail;
            String module=txtModule.getText();
            if (examName.equalsIgnoreCase("Quick Exam")) {
                eId = "QE";
            }
            try {
                DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
                InputStream is = getClass().getResourceAsStream("../jasper/aStudentResults.jrxml");
                JasperReport jr = JasperCompileManager.compileReport(is);
                HashMap hm = new HashMap();
                hm.put("stId", stId);
                hm.put("stName", stName);
                hm.put("Exam ID", eId);
                hm.put("Exam Name", examName);
                hm.put("Module", module);
                hm.put("Marks",finalMarks.getText() );
                
                if (finalMarksInt > 40) {
                    passFail ="Pass";
                }else{
                    passFail = "Fail";
                }
                hm.put("passFail", passFail);
                
                JasperPrint jp = JasperFillManager.fillReport(jr, hm, new JRTableModelDataSource(defaultTableModel));
                JasperViewer.viewReport(jp, false);
            } catch (JRException ex) {
                Logger.getLogger(ExamResult.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        mainForm.show();
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void txtExamNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExamNameActionPerformed

    }//GEN-LAST:event_txtExamNameActionPerformed

    private void txtExamNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExamNameKeyReleased

    }//GEN-LAST:event_txtExamNameKeyReleased

    private void txtStNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStNameActionPerformed

    private void txtStNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStNameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStNameKeyReleased

    private void txtModuleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModuleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModuleActionPerformed

    private void txtModuleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModuleKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModuleKeyReleased

    private void txtNoOfQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoOfQActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoOfQActionPerformed

    private void txtNoOfQKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoOfQKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoOfQKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ansedQ;
    private javax.swing.JLabel corAnsedQ;
    private javax.swing.JLabel finalMarks;
    private javax.swing.JLabel incorAnsrdQ;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblBackgrond;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel notAnsedQ;
    private datechooser.beans.DateChooserCombo txtDate;
    private javax.swing.JTextField txtExamName;
    private javax.swing.JTextField txtModule;
    private javax.swing.JTextField txtNoOfQ;
    private javax.swing.JTextField txtStName;
    // End of variables declaration//GEN-END:variables
}
