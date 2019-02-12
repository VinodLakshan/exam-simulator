/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.view;

import com.ijse.sss.connector.ServerConnector;
import com.ijse.sss.controller.QuestionController;
import com.ijse.sss.controller.StudentController;
import com.ijse.sss.model.Exam;
import com.ijse.sss.model.Question;
import com.ijse.sss.model.Student;
import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

/**
 *
 * @author NEOx
 */
public class ExaminationForm extends javax.swing.JFrame {

    /**
     * Creates new form ExaminationForm
     */
    private MainForm mainForm;
    private String module;
    private String examName = "Quick Exam";
    private String stName;
    private String stId;
    private int durationH;
    private int durationM;
    private int noOfQ;
    private ArrayList<Question> questions = new ArrayList();
    private ArrayList<JButton> buttons = new ArrayList<>();
    private ArrayList<JRadioButton> answers = new ArrayList<>();
    private String[] studentAnswers;
    private int second = 1;
    private int nowQuestion = 1;
    private int answered = 0;
    private int notAnswered = 0;
    private int forceStop = 0;
    
    private Exam exam;
    private String eId;
    
    public ExaminationForm(MainForm mainForm, String module, int durationH, int durationM, int noOfQ) {
        initComponents();
        initLookandFeel();
        this.mainForm = mainForm;
        this.module = module;
        this.durationH = durationH;
        this.durationM = durationM;
        this.noOfQ = noOfQ;
        this.studentAnswers = new String[noOfQ];
        this.notAnswered = this.noOfQ;
        autoDateTime();
        setTimer();
        setDetails();
        getQuestions();
        addToggles();
        showNoButton();
        exactQuestion();
    }

    public ExaminationForm(MainForm mainForm, Exam exam) {
        initComponents();
        initLookandFeel();
        this.mainForm = mainForm;
        this.eId = exam.getEid();
        this.exam = exam;
        this.examName = exam.getExamName();
        autoDateTime();
        setDetailsFromExam();
        this.studentAnswers = new String[noOfQ];
        this.notAnswered = this.noOfQ;
        setTimer();
        setDetails();
        addToggles();
        showNoButton();
        exactQuestion();
    }

    private void setDetailsFromExam() {
        String allModules = "";
        for (int i = 0; i < exam.getModules().length; i++) {
            allModules += exam.getModules()[i] + ",";
        }
        this.module = allModules + "\b";
        String[] durations = exam.getDuration().split(":");
        this.durationH = Integer.parseInt(durations[0]);
        this.durationM = Integer.parseInt(durations[1]);
        this.noOfQ = exam.getQuestions().length;

        try {
            QuestionController questionController = ServerConnector.getServerConnector().getQuestionController();
            this.questions = questionController.searchGroupQuestions(exam.getQuestions());

        } catch (RemoteException ex) {
            Logger.getLogger(ExaminationForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ExaminationForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ExaminationForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExaminationForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExaminationForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ExaminationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void setTimer() {
        Timer timerLeft = new Timer();
        TimerTask timeTask = new TimerTask() {
            @Override
            public void run() {
                second--;
                if (second < 0) {
                    second = 59;
                    if (durationM > 0) {
                        durationM--;
                    } else {
                        durationM = 59;
                        durationH--;
                    }
                }
                String min = "";
                String hour = "";
                String sec = "";
                if (durationM < 10) {
                    min = "0" + durationM;
                } else {
                    min = durationM + "";
                }
                if (durationH < 10) {
                    hour = "0" + durationH;
                } else {
                    hour = durationH + "";
                }
                if (second < 10) {
                    sec = "0" + second;
                } else {
                    sec = second + "";
                }
                lblTimeLeft.setText(hour + " : " + min + " : " + sec);
                if (lblTimeLeft.getText().equalsIgnoreCase("00 : 00 : 00")) {
                    timerLeft.cancel();
                    forceStop = 1;
                    btEndExam.doClick();
                }
            }
        };
        timerLeft.scheduleAtFixedRate(timeTask, 1000, 1000);

    }

    private void getQuestions() {
        try {
            QuestionController questionController = ServerConnector.getServerConnector().getQuestionController();
            questions = questionController.readQuestionQtyModel(noOfQ, module);
        } catch (IOException ex) {
            Logger.getLogger(ExaminationForm.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (NotBoundException ex) {
            Logger.getLogger(ExaminationForm.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExaminationForm.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (ParseException ex) {
            Logger.getLogger(ExaminationForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setDetails() {
        try {
            StudentController studentController = ServerConnector.getServerConnector().getStudentController();
            Student searchStudent = studentController.searchStudent(mainForm.getTitle());
            lblCandidateName.setText(searchStudent.getStName());
            stName = searchStudent.getStName();
            stId = searchStudent.getStId();
            lblNoteAnswered.setText(Integer.toString(notAnswered));
            lblExamModule.setText(module);
            answers.add(answ1);
            answers.add(answ2);
            answers.add(answ3);
            answers.add(answ4);
        } catch (RemoteException ex) {
            Logger.getLogger(ExaminationForm.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (NotBoundException ex) {
            Logger.getLogger(ExaminationForm.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (MalformedURLException ex) {
            Logger.getLogger(ExaminationForm.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(ExaminationForm.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExaminationForm.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (ParseException ex) {
            Logger.getLogger(ExaminationForm.class
                    .getName()).log(Level.SEVERE, null, ex);
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
        jPanel7 = new javax.swing.JPanel();
        btClear = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        tb1 = new javax.swing.JButton();
        tb2 = new javax.swing.JButton();
        tb3 = new javax.swing.JButton();
        tb4 = new javax.swing.JButton();
        tb5 = new javax.swing.JButton();
        tb6 = new javax.swing.JButton();
        tb7 = new javax.swing.JButton();
        tb8 = new javax.swing.JButton();
        tb9 = new javax.swing.JButton();
        tb10 = new javax.swing.JButton();
        tb11 = new javax.swing.JButton();
        tb12 = new javax.swing.JButton();
        tb13 = new javax.swing.JButton();
        tb14 = new javax.swing.JButton();
        tb15 = new javax.swing.JButton();
        tb16 = new javax.swing.JButton();
        tb17 = new javax.swing.JButton();
        tb18 = new javax.swing.JButton();
        tb19 = new javax.swing.JButton();
        tb20 = new javax.swing.JButton();
        tb21 = new javax.swing.JButton();
        tb22 = new javax.swing.JButton();
        tb23 = new javax.swing.JButton();
        tb24 = new javax.swing.JButton();
        tb25 = new javax.swing.JButton();
        tb26 = new javax.swing.JButton();
        tb27 = new javax.swing.JButton();
        tb28 = new javax.swing.JButton();
        tb29 = new javax.swing.JButton();
        tb30 = new javax.swing.JButton();
        tb31 = new javax.swing.JButton();
        tb32 = new javax.swing.JButton();
        tb33 = new javax.swing.JButton();
        tb34 = new javax.swing.JButton();
        tb35 = new javax.swing.JButton();
        tb36 = new javax.swing.JButton();
        tb37 = new javax.swing.JButton();
        tb38 = new javax.swing.JButton();
        tb39 = new javax.swing.JButton();
        tb40 = new javax.swing.JButton();
        tb41 = new javax.swing.JButton();
        tb42 = new javax.swing.JButton();
        tb43 = new javax.swing.JButton();
        tb44 = new javax.swing.JButton();
        tb45 = new javax.swing.JButton();
        tb46 = new javax.swing.JButton();
        tb47 = new javax.swing.JButton();
        tb48 = new javax.swing.JButton();
        tb49 = new javax.swing.JButton();
        tb50 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        btEndExam = new javax.swing.JButton();
        btSubmit = new javax.swing.JButton();
        btBack = new javax.swing.JButton();
        btNext = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        qNo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();
        answ1 = new javax.swing.JRadioButton();
        answ2 = new javax.swing.JRadioButton();
        answ3 = new javax.swing.JRadioButton();
        answ4 = new javax.swing.JRadioButton();
        lblAnswered = new javax.swing.JLabel();
        lblNoteAnswered = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblQEDesc7 = new javax.swing.JLabel();
        lblQEDesc6 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblCandidateName = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTimeLeft1 = new javax.swing.JLabel();
        lblTimeLeft = new javax.swing.JLabel();
        lblExamModule = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblBackgrond = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/clear.jpg"))); // NOI18N
        btClear.setEnabled(false);
        btClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearActionPerformed(evt);
            }
        });
        jPanel7.add(btClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 950, 140, 60));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridLayout(10, 5, 4, 4));

        tb1.setBackground(new java.awt.Color(204, 204, 204));
        tb1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb1.setForeground(new java.awt.Color(255, 255, 255));
        tb1.setText("1");
        tb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb1ActionPerformed(evt);
            }
        });
        jPanel1.add(tb1);

        tb2.setBackground(new java.awt.Color(204, 204, 204));
        tb2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb2.setForeground(new java.awt.Color(255, 255, 255));
        tb2.setText("2");
        tb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb2ActionPerformed(evt);
            }
        });
        jPanel1.add(tb2);

        tb3.setBackground(new java.awt.Color(204, 204, 204));
        tb3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb3.setForeground(new java.awt.Color(255, 255, 255));
        tb3.setText("3");
        tb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb3ActionPerformed(evt);
            }
        });
        jPanel1.add(tb3);

        tb4.setBackground(new java.awt.Color(204, 204, 204));
        tb4.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb4.setForeground(new java.awt.Color(255, 255, 255));
        tb4.setText("4");
        tb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb4ActionPerformed(evt);
            }
        });
        jPanel1.add(tb4);

        tb5.setBackground(new java.awt.Color(204, 204, 204));
        tb5.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb5.setForeground(new java.awt.Color(255, 255, 255));
        tb5.setText("5");
        tb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb5ActionPerformed(evt);
            }
        });
        jPanel1.add(tb5);

        tb6.setBackground(new java.awt.Color(204, 204, 204));
        tb6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb6.setForeground(new java.awt.Color(255, 255, 255));
        tb6.setText("6");
        tb6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb6ActionPerformed(evt);
            }
        });
        jPanel1.add(tb6);

        tb7.setBackground(new java.awt.Color(204, 204, 204));
        tb7.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb7.setForeground(new java.awt.Color(255, 255, 255));
        tb7.setText("7");
        tb7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb7ActionPerformed(evt);
            }
        });
        jPanel1.add(tb7);

        tb8.setBackground(new java.awt.Color(204, 204, 204));
        tb8.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb8.setForeground(new java.awt.Color(255, 255, 255));
        tb8.setText("8");
        tb8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb8ActionPerformed(evt);
            }
        });
        jPanel1.add(tb8);

        tb9.setBackground(new java.awt.Color(204, 204, 204));
        tb9.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb9.setForeground(new java.awt.Color(255, 255, 255));
        tb9.setText("9");
        tb9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb9ActionPerformed(evt);
            }
        });
        jPanel1.add(tb9);

        tb10.setBackground(new java.awt.Color(204, 204, 204));
        tb10.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb10.setForeground(new java.awt.Color(255, 255, 255));
        tb10.setText("10");
        tb10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb10ActionPerformed(evt);
            }
        });
        jPanel1.add(tb10);

        tb11.setBackground(new java.awt.Color(204, 204, 204));
        tb11.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb11.setForeground(new java.awt.Color(255, 255, 255));
        tb11.setText("11");
        tb11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb11ActionPerformed(evt);
            }
        });
        jPanel1.add(tb11);

        tb12.setBackground(new java.awt.Color(204, 204, 204));
        tb12.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb12.setForeground(new java.awt.Color(255, 255, 255));
        tb12.setText("12");
        tb12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb12ActionPerformed(evt);
            }
        });
        jPanel1.add(tb12);

        tb13.setBackground(new java.awt.Color(204, 204, 204));
        tb13.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb13.setForeground(new java.awt.Color(255, 255, 255));
        tb13.setText("13");
        tb13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb13ActionPerformed(evt);
            }
        });
        jPanel1.add(tb13);

        tb14.setBackground(new java.awt.Color(204, 204, 204));
        tb14.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb14.setForeground(new java.awt.Color(255, 255, 255));
        tb14.setText("14");
        tb14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb14ActionPerformed(evt);
            }
        });
        jPanel1.add(tb14);

        tb15.setBackground(new java.awt.Color(204, 204, 204));
        tb15.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb15.setForeground(new java.awt.Color(255, 255, 255));
        tb15.setText("15");
        tb15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb15ActionPerformed(evt);
            }
        });
        jPanel1.add(tb15);

        tb16.setBackground(new java.awt.Color(204, 204, 204));
        tb16.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb16.setForeground(new java.awt.Color(255, 255, 255));
        tb16.setText("16");
        tb16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb16ActionPerformed(evt);
            }
        });
        jPanel1.add(tb16);

        tb17.setBackground(new java.awt.Color(204, 204, 204));
        tb17.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb17.setForeground(new java.awt.Color(255, 255, 255));
        tb17.setText("17");
        tb17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb17ActionPerformed(evt);
            }
        });
        jPanel1.add(tb17);

        tb18.setBackground(new java.awt.Color(204, 204, 204));
        tb18.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb18.setForeground(new java.awt.Color(255, 255, 255));
        tb18.setText("18");
        tb18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb18ActionPerformed(evt);
            }
        });
        jPanel1.add(tb18);

        tb19.setBackground(new java.awt.Color(204, 204, 204));
        tb19.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb19.setForeground(new java.awt.Color(255, 255, 255));
        tb19.setText("19");
        tb19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb19ActionPerformed(evt);
            }
        });
        jPanel1.add(tb19);

        tb20.setBackground(new java.awt.Color(204, 204, 204));
        tb20.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb20.setForeground(new java.awt.Color(255, 255, 255));
        tb20.setText("20");
        tb20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb20ActionPerformed(evt);
            }
        });
        jPanel1.add(tb20);

        tb21.setBackground(new java.awt.Color(204, 204, 204));
        tb21.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb21.setForeground(new java.awt.Color(255, 255, 255));
        tb21.setText("21");
        tb21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb21ActionPerformed(evt);
            }
        });
        jPanel1.add(tb21);

        tb22.setBackground(new java.awt.Color(204, 204, 204));
        tb22.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb22.setForeground(new java.awt.Color(255, 255, 255));
        tb22.setText("22");
        tb22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb22ActionPerformed(evt);
            }
        });
        jPanel1.add(tb22);

        tb23.setBackground(new java.awt.Color(204, 204, 204));
        tb23.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb23.setForeground(new java.awt.Color(255, 255, 255));
        tb23.setText("23");
        tb23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb23ActionPerformed(evt);
            }
        });
        jPanel1.add(tb23);

        tb24.setBackground(new java.awt.Color(204, 204, 204));
        tb24.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb24.setForeground(new java.awt.Color(255, 255, 255));
        tb24.setText("24");
        tb24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb24ActionPerformed(evt);
            }
        });
        jPanel1.add(tb24);

        tb25.setBackground(new java.awt.Color(204, 204, 204));
        tb25.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb25.setForeground(new java.awt.Color(255, 255, 255));
        tb25.setText("25");
        tb25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb25ActionPerformed(evt);
            }
        });
        jPanel1.add(tb25);

        tb26.setBackground(new java.awt.Color(204, 204, 204));
        tb26.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb26.setForeground(new java.awt.Color(255, 255, 255));
        tb26.setText("26");
        tb26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb26ActionPerformed(evt);
            }
        });
        jPanel1.add(tb26);

        tb27.setBackground(new java.awt.Color(204, 204, 204));
        tb27.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb27.setForeground(new java.awt.Color(255, 255, 255));
        tb27.setText("27");
        tb27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb27ActionPerformed(evt);
            }
        });
        jPanel1.add(tb27);

        tb28.setBackground(new java.awt.Color(204, 204, 204));
        tb28.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb28.setForeground(new java.awt.Color(255, 255, 255));
        tb28.setText("28");
        tb28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb28ActionPerformed(evt);
            }
        });
        jPanel1.add(tb28);

        tb29.setBackground(new java.awt.Color(204, 204, 204));
        tb29.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb29.setForeground(new java.awt.Color(255, 255, 255));
        tb29.setText("29");
        tb29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb29ActionPerformed(evt);
            }
        });
        jPanel1.add(tb29);

        tb30.setBackground(new java.awt.Color(204, 204, 204));
        tb30.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb30.setForeground(new java.awt.Color(255, 255, 255));
        tb30.setText("30");
        tb30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb30ActionPerformed(evt);
            }
        });
        jPanel1.add(tb30);

        tb31.setBackground(new java.awt.Color(204, 204, 204));
        tb31.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb31.setForeground(new java.awt.Color(255, 255, 255));
        tb31.setText("31");
        tb31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb31ActionPerformed(evt);
            }
        });
        jPanel1.add(tb31);

        tb32.setBackground(new java.awt.Color(204, 204, 204));
        tb32.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb32.setForeground(new java.awt.Color(255, 255, 255));
        tb32.setText("32");
        tb32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb32ActionPerformed(evt);
            }
        });
        jPanel1.add(tb32);

        tb33.setBackground(new java.awt.Color(204, 204, 204));
        tb33.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb33.setForeground(new java.awt.Color(255, 255, 255));
        tb33.setText("33");
        tb33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb33ActionPerformed(evt);
            }
        });
        jPanel1.add(tb33);

        tb34.setBackground(new java.awt.Color(204, 204, 204));
        tb34.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb34.setForeground(new java.awt.Color(255, 255, 255));
        tb34.setText("34");
        tb34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb34ActionPerformed(evt);
            }
        });
        jPanel1.add(tb34);

        tb35.setBackground(new java.awt.Color(204, 204, 204));
        tb35.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb35.setForeground(new java.awt.Color(255, 255, 255));
        tb35.setText("35");
        tb35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb35ActionPerformed(evt);
            }
        });
        jPanel1.add(tb35);

        tb36.setBackground(new java.awt.Color(204, 204, 204));
        tb36.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb36.setForeground(new java.awt.Color(255, 255, 255));
        tb36.setText("36");
        tb36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb36ActionPerformed(evt);
            }
        });
        jPanel1.add(tb36);

        tb37.setBackground(new java.awt.Color(204, 204, 204));
        tb37.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb37.setForeground(new java.awt.Color(255, 255, 255));
        tb37.setText("37");
        tb37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb37ActionPerformed(evt);
            }
        });
        jPanel1.add(tb37);

        tb38.setBackground(new java.awt.Color(204, 204, 204));
        tb38.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb38.setForeground(new java.awt.Color(255, 255, 255));
        tb38.setText("38");
        tb38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb38ActionPerformed(evt);
            }
        });
        jPanel1.add(tb38);

        tb39.setBackground(new java.awt.Color(204, 204, 204));
        tb39.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb39.setForeground(new java.awt.Color(255, 255, 255));
        tb39.setText("39");
        tb39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb39ActionPerformed(evt);
            }
        });
        jPanel1.add(tb39);

        tb40.setBackground(new java.awt.Color(204, 204, 204));
        tb40.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb40.setForeground(new java.awt.Color(255, 255, 255));
        tb40.setText("40");
        tb40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb40ActionPerformed(evt);
            }
        });
        jPanel1.add(tb40);

        tb41.setBackground(new java.awt.Color(204, 204, 204));
        tb41.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb41.setForeground(new java.awt.Color(255, 255, 255));
        tb41.setText("41");
        tb41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb41ActionPerformed(evt);
            }
        });
        jPanel1.add(tb41);

        tb42.setBackground(new java.awt.Color(204, 204, 204));
        tb42.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb42.setForeground(new java.awt.Color(255, 255, 255));
        tb42.setText("42");
        tb42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb42ActionPerformed(evt);
            }
        });
        jPanel1.add(tb42);

        tb43.setBackground(new java.awt.Color(204, 204, 204));
        tb43.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb43.setForeground(new java.awt.Color(255, 255, 255));
        tb43.setText("43");
        tb43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb43ActionPerformed(evt);
            }
        });
        jPanel1.add(tb43);

        tb44.setBackground(new java.awt.Color(204, 204, 204));
        tb44.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb44.setForeground(new java.awt.Color(255, 255, 255));
        tb44.setText("44");
        tb44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb44ActionPerformed(evt);
            }
        });
        jPanel1.add(tb44);

        tb45.setBackground(new java.awt.Color(204, 204, 204));
        tb45.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb45.setForeground(new java.awt.Color(255, 255, 255));
        tb45.setText("45");
        tb45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb45ActionPerformed(evt);
            }
        });
        jPanel1.add(tb45);

        tb46.setBackground(new java.awt.Color(204, 204, 204));
        tb46.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb46.setForeground(new java.awt.Color(255, 255, 255));
        tb46.setText("46");
        tb46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb46ActionPerformed(evt);
            }
        });
        jPanel1.add(tb46);

        tb47.setBackground(new java.awt.Color(204, 204, 204));
        tb47.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb47.setForeground(new java.awt.Color(255, 255, 255));
        tb47.setText("47");
        tb47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb47ActionPerformed(evt);
            }
        });
        jPanel1.add(tb47);

        tb48.setBackground(new java.awt.Color(204, 204, 204));
        tb48.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb48.setForeground(new java.awt.Color(255, 255, 255));
        tb48.setText("48");
        tb48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb48ActionPerformed(evt);
            }
        });
        jPanel1.add(tb48);

        tb49.setBackground(new java.awt.Color(204, 204, 204));
        tb49.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb49.setForeground(new java.awt.Color(255, 255, 255));
        tb49.setText("49");
        tb49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb49ActionPerformed(evt);
            }
        });
        jPanel1.add(tb49);

        tb50.setBackground(new java.awt.Color(204, 204, 204));
        tb50.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tb50.setForeground(new java.awt.Color(255, 255, 255));
        tb50.setText("50");
        tb50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb50ActionPerformed(evt);
            }
        });
        jPanel1.add(tb50);

        jPanel7.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 510, 500, 520));

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/mark.jpg"))); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 950, 140, 60));

        btEndExam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/endExam.jpg"))); // NOI18N
        btEndExam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEndExamActionPerformed(evt);
            }
        });
        jPanel7.add(btEndExam, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 950, 190, 60));

        btSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/submit.jpg"))); // NOI18N
        btSubmit.setEnabled(false);
        btSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSubmitActionPerformed(evt);
            }
        });
        jPanel7.add(btSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 950, 140, 60));

        btBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/back.jpg"))); // NOI18N
        btBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBackActionPerformed(evt);
            }
        });
        jPanel7.add(btBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 950, 140, 60));

        btNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/next.jpg"))); // NOI18N
        btNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNextActionPerformed(evt);
            }
        });
        jPanel7.add(btNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 950, 140, 60));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 920, 1350, 110));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 920, 1350, 110));

        qNo.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        qNo.setForeground(new java.awt.Color(255, 255, 255));
        qNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        qNo.setText("50.");
        jPanel7.add(qNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 100, 80));

        txtNote.setEditable(false);
        txtNote.setColumns(20);
        txtNote.setFont(new java.awt.Font("Monospaced", 1, 20)); // NOI18N
        txtNote.setRows(5);
        jScrollPane1.setViewportView(txtNote);

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 1230, 230));

        answ1.setBackground(new java.awt.Color(102, 102, 102));
        buttonGroup1.add(answ1);
        answ1.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        answ1.setForeground(new java.awt.Color(255, 255, 255));
        answ1.setText("  Answer 01");
        answ1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        answ1.setBorderPainted(true);
        answ1.setOpaque(false);
        answ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                answ1ActionPerformed(evt);
            }
        });
        jPanel7.add(answ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 610, 580, 60));

        answ2.setBackground(new java.awt.Color(102, 102, 102));
        buttonGroup1.add(answ2);
        answ2.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        answ2.setForeground(new java.awt.Color(255, 255, 255));
        answ2.setText("  Answer 02");
        answ2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        answ2.setBorderPainted(true);
        answ2.setOpaque(false);
        answ2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                answ2ActionPerformed(evt);
            }
        });
        jPanel7.add(answ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 610, 580, 60));

        answ3.setBackground(new java.awt.Color(102, 102, 102));
        buttonGroup1.add(answ3);
        answ3.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        answ3.setForeground(new java.awt.Color(255, 255, 255));
        answ3.setText("  Answer 03");
        answ3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        answ3.setBorderPainted(true);
        answ3.setOpaque(false);
        answ3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                answ3ActionPerformed(evt);
            }
        });
        jPanel7.add(answ3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 740, 580, 60));

        answ4.setBackground(new java.awt.Color(102, 102, 102));
        buttonGroup1.add(answ4);
        answ4.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        answ4.setForeground(new java.awt.Color(255, 255, 255));
        answ4.setText("  Answer 04");
        answ4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        answ4.setBorderPainted(true);
        answ4.setOpaque(false);
        answ4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                answ4ActionPerformed(evt);
            }
        });
        jPanel7.add(answ4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 740, 580, 60));

        lblAnswered.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        lblAnswered.setForeground(new java.awt.Color(255, 255, 255));
        lblAnswered.setText("0");
        jPanel7.add(lblAnswered, new org.netbeans.lib.awtextra.AbsoluteConstraints(1700, 320, 60, 70));

        lblNoteAnswered.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        lblNoteAnswered.setForeground(new java.awt.Color(255, 255, 255));
        lblNoteAnswered.setText("00");
        jPanel7.add(lblNoteAnswered, new org.netbeans.lib.awtextra.AbsoluteConstraints(1700, 400, 50, 70));

        jLabel21.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText(":");
        jPanel7.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1670, 320, 30, 70));

        jLabel20.setFont(new java.awt.Font("Microsoft Himalaya", 1, 55)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText(":");
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1670, 400, 30, 70));

        lblQEDesc7.setFont(new java.awt.Font("Microsoft Himalaya", 1, 50)); // NOI18N
        lblQEDesc7.setForeground(new java.awt.Color(255, 255, 255));
        lblQEDesc7.setText("Not Answered");
        jPanel7.add(lblQEDesc7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1420, 400, 250, 70));

        lblQEDesc6.setFont(new java.awt.Font("Microsoft Himalaya", 1, 50)); // NOI18N
        lblQEDesc6.setForeground(new java.awt.Color(255, 255, 255));
        lblQEDesc6.setText("Answered");
        jPanel7.add(lblQEDesc6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1420, 320, 180, 70));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 1350, 620));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 1350, 620));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 510, 500, 520));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 510, 500, 520));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 290, 500, 210));

        lblCandidateName.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        lblCandidateName.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName.setText("CANDIDATE NAME");
        jPanel7.add(lblCandidateName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 490, 80));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 470, 70));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 470, 70));

        lblTimeLeft1.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        lblTimeLeft1.setForeground(new java.awt.Color(255, 255, 255));
        lblTimeLeft1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTimeLeft1.setText("Time Left  :");
        jPanel7.add(lblTimeLeft1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 200, 260, 100));

        lblTimeLeft.setFont(new java.awt.Font("Microsoft Himalaya", 1, 65)); // NOI18N
        lblTimeLeft.setForeground(new java.awt.Color(255, 255, 255));
        lblTimeLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTimeLeft.setText("08 : 00");
        jPanel7.add(lblTimeLeft, new org.netbeans.lib.awtextra.AbsoluteConstraints(1620, 220, 270, 60));

        lblExamModule.setFont(new java.awt.Font("Microsoft Himalaya", 1, 60)); // NOI18N
        lblExamModule.setForeground(new java.awt.Color(255, 255, 255));
        lblExamModule.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExamModule.setText("MODULE OF EXAM");
        jPanel7.add(lblExamModule, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, 870, 100));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, 870, 70));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 290, 500, 210));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, 870, 70));

        jLabel3.setFont(new java.awt.Font("Microsoft Himalaya", 1, 100)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("QUICK EXAM");
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 510, 90));

        lblTime.setFont(new java.awt.Font("Microsoft Himalaya", 1, 100)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        lblTime.setText("08:00");
        jPanel7.add(lblTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(1550, 50, 330, 90));

        lblDate.setFont(new java.awt.Font("Microsoft Himalaya", 1, 80)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setText("08:00");
        jPanel7.add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1570, 120, 310, 90));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 210, 500, 70));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 1350, 620));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 210, 500, 70));

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

        lblBackgrond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/technology-1106438_960_720darky.jpg"))); // NOI18N
        jPanel7.add(lblBackgrond, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1920, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNextActionPerformed
        btSubmit.setEnabled(false);
        nowQuestion++;
        if (nowQuestion <= noOfQ) {
            exactQuestion();

        } else {
            btNext.setEnabled(false);
            nowQuestion--;
        }
    }//GEN-LAST:event_btNextActionPerformed

    private void btBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBackActionPerformed
        btSubmit.setEnabled(false);
        nowQuestion--;
        if (nowQuestion > 0) {
            exactQuestion();

        } else {
            btBack.setEnabled(false);
            nowQuestion++;
        }
    }//GEN-LAST:event_btBackActionPerformed

    private void btSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSubmitActionPerformed
        for (JRadioButton answer : answers) {
            if (answer.isSelected()) {
                studentAnswers[nowQuestion - 1] = answer.getText();
                buttons.get(nowQuestion - 1).setBackground(Color.blue);
                answer.setBackground(Color.blue);
                answered++;
                notAnswered--;
                lblAnswered.setText(Integer.toString(answered));
                lblNoteAnswered.setText(Integer.toString(notAnswered));
                answ1.setEnabled(false);
                answ2.setEnabled(false);
                answ3.setEnabled(false);
                answ4.setEnabled(false);
                btSubmit.setEnabled(false);
                btClear.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btSubmitActionPerformed

    private void btEndExamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEndExamActionPerformed
        if (forceStop == 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to End Exam..?");
            if (confirm == 0) {
                new ExamResult(mainForm, questions, studentAnswers, examName, answered, stName,eId,stId).setVisible(true);
                this.dispose();
            }

        } else {
            JOptionPane.showMessageDialog(this, "Times up..!!");
            new ExamResult(mainForm, questions, studentAnswers, examName, answered, stName,eId,stId).setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btEndExamActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        btClear.doClick();
        studentAnswers[nowQuestion - 1] = null;
        btSubmit.setEnabled(false);
        btClear.setEnabled(false);
        buttons.get(nowQuestion - 1).setBackground(Color.orange);

    }//GEN-LAST:event_jButton13ActionPerformed

    private void answ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_answ1ActionPerformed
        answ1.setOpaque(true);
        answ2.setOpaque(false);
        answ2.setBackground(Color.black);
        answ3.setOpaque(false);
        answ3.setBackground(Color.black);
        answ4.setOpaque(false);
        answ4.setBackground(Color.black);
        btSubmit.setEnabled(true);
    }//GEN-LAST:event_answ1ActionPerformed

    private void answ2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_answ2ActionPerformed
        answ1.setOpaque(false);
        answ1.setBackground(Color.black);
        answ2.setOpaque(true);
        answ3.setOpaque(false);
        answ3.setBackground(Color.black);
        answ4.setOpaque(false);
        answ4.setBackground(Color.black);
        btSubmit.setEnabled(true);
    }//GEN-LAST:event_answ2ActionPerformed

    private void answ3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_answ3ActionPerformed
        answ1.setOpaque(false);
        answ1.setBackground(Color.black);
        answ2.setOpaque(false);
        answ2.setBackground(Color.black);
        answ3.setOpaque(true);
        answ4.setOpaque(false);
        answ4.setBackground(Color.black);
        btSubmit.setEnabled(true);
    }//GEN-LAST:event_answ3ActionPerformed

    private void answ4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_answ4ActionPerformed
        answ1.setOpaque(false);
        answ1.setBackground(Color.black);
        answ2.setOpaque(false);
        answ2.setBackground(Color.black);
        answ3.setOpaque(false);
        answ3.setBackground(Color.black);
        answ4.setOpaque(true);
        btSubmit.setEnabled(true);
    }//GEN-LAST:event_answ4ActionPerformed

    private void tb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb3ActionPerformed
        nowQuestion = Integer.parseInt(tb3.getText());
        exactQuestion();
    }//GEN-LAST:event_tb3ActionPerformed

    private void tb4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb4ActionPerformed
        nowQuestion = Integer.parseInt(tb4.getText());
        exactQuestion();
    }//GEN-LAST:event_tb4ActionPerformed

    private void tb35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb35ActionPerformed
        nowQuestion = Integer.parseInt(tb35.getText());
        exactQuestion();
    }//GEN-LAST:event_tb35ActionPerformed

    private void tb7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb7ActionPerformed
        nowQuestion = Integer.parseInt(tb7.getText());
        exactQuestion();
    }//GEN-LAST:event_tb7ActionPerformed

    private void tb12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb12ActionPerformed
        nowQuestion = Integer.parseInt(tb12.getText());
        exactQuestion();
    }//GEN-LAST:event_tb12ActionPerformed

    private void tb17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb17ActionPerformed
        nowQuestion = Integer.parseInt(tb17.getText());
        exactQuestion();
    }//GEN-LAST:event_tb17ActionPerformed

    private void tb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb1ActionPerformed
        nowQuestion = Integer.parseInt(tb1.getText());
        exactQuestion();
    }//GEN-LAST:event_tb1ActionPerformed

    private void tb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb2ActionPerformed
        nowQuestion = Integer.parseInt(tb2.getText());
        exactQuestion();
    }//GEN-LAST:event_tb2ActionPerformed

    private void tb5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb5ActionPerformed
        nowQuestion = Integer.parseInt(tb5.getText());
        exactQuestion();
    }//GEN-LAST:event_tb5ActionPerformed

    private void tb6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb6ActionPerformed
        nowQuestion = Integer.parseInt(tb6.getText());
        exactQuestion();
    }//GEN-LAST:event_tb6ActionPerformed

    private void tb8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb8ActionPerformed
        nowQuestion = Integer.parseInt(tb8.getText());
        exactQuestion();
    }//GEN-LAST:event_tb8ActionPerformed

    private void tb9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb9ActionPerformed
        nowQuestion = Integer.parseInt(tb9.getText());
        exactQuestion();
    }//GEN-LAST:event_tb9ActionPerformed

    private void tb10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb10ActionPerformed
        nowQuestion = Integer.parseInt(tb10.getText());
        exactQuestion();
    }//GEN-LAST:event_tb10ActionPerformed

    private void tb11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb11ActionPerformed
        nowQuestion = Integer.parseInt(tb11.getText());
        exactQuestion();
    }//GEN-LAST:event_tb11ActionPerformed

    private void tb13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb13ActionPerformed
        nowQuestion = Integer.parseInt(tb13.getText());
        exactQuestion();
    }//GEN-LAST:event_tb13ActionPerformed

    private void tb14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb14ActionPerformed
        nowQuestion = Integer.parseInt(tb14.getText());
        exactQuestion();
    }//GEN-LAST:event_tb14ActionPerformed

    private void tb15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb15ActionPerformed
        nowQuestion = Integer.parseInt(tb16.getText());
        exactQuestion();
    }//GEN-LAST:event_tb15ActionPerformed

    private void tb16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb16ActionPerformed
        nowQuestion = Integer.parseInt(tb16.getText());
        exactQuestion();
    }//GEN-LAST:event_tb16ActionPerformed

    private void tb18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb18ActionPerformed
        nowQuestion = Integer.parseInt(tb18.getText());
        exactQuestion();
    }//GEN-LAST:event_tb18ActionPerformed

    private void tb19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb19ActionPerformed
        nowQuestion = Integer.parseInt(tb19.getText());
        exactQuestion();
    }//GEN-LAST:event_tb19ActionPerformed

    private void tb20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb20ActionPerformed
        nowQuestion = Integer.parseInt(tb20.getText());
        exactQuestion();
    }//GEN-LAST:event_tb20ActionPerformed

    private void tb21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb21ActionPerformed
        nowQuestion = Integer.parseInt(tb21.getText());
        exactQuestion();
    }//GEN-LAST:event_tb21ActionPerformed

    private void tb22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb22ActionPerformed
        nowQuestion = Integer.parseInt(tb22.getText());
        exactQuestion();
    }//GEN-LAST:event_tb22ActionPerformed

    private void tb23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb23ActionPerformed
        nowQuestion = Integer.parseInt(tb23.getText());
        exactQuestion();
    }//GEN-LAST:event_tb23ActionPerformed

    private void tb24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb24ActionPerformed
        nowQuestion = Integer.parseInt(tb24.getText());
        exactQuestion();
    }//GEN-LAST:event_tb24ActionPerformed

    private void tb25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb25ActionPerformed
        nowQuestion = Integer.parseInt(tb25.getText());
        exactQuestion();
    }//GEN-LAST:event_tb25ActionPerformed

    private void tb26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb26ActionPerformed
        nowQuestion = Integer.parseInt(tb26.getText());
        exactQuestion();
    }//GEN-LAST:event_tb26ActionPerformed

    private void tb27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb27ActionPerformed
        nowQuestion = Integer.parseInt(tb27.getText());
        exactQuestion();
    }//GEN-LAST:event_tb27ActionPerformed

    private void tb28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb28ActionPerformed
        nowQuestion = Integer.parseInt(tb28.getText());
        exactQuestion();
    }//GEN-LAST:event_tb28ActionPerformed

    private void tb29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb29ActionPerformed
        nowQuestion = Integer.parseInt(tb29.getText());
        exactQuestion();
    }//GEN-LAST:event_tb29ActionPerformed

    private void tb30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb30ActionPerformed
        nowQuestion = Integer.parseInt(tb30.getText());
        exactQuestion();
    }//GEN-LAST:event_tb30ActionPerformed

    private void tb31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb31ActionPerformed
        nowQuestion = Integer.parseInt(tb31.getText());
        exactQuestion();
    }//GEN-LAST:event_tb31ActionPerformed

    private void tb32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb32ActionPerformed
        nowQuestion = Integer.parseInt(tb32.getText());
        exactQuestion();
    }//GEN-LAST:event_tb32ActionPerformed

    private void tb33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb33ActionPerformed
        nowQuestion = Integer.parseInt(tb33.getText());
        exactQuestion();
    }//GEN-LAST:event_tb33ActionPerformed

    private void tb34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb34ActionPerformed
        nowQuestion = Integer.parseInt(tb34.getText());
        exactQuestion();
    }//GEN-LAST:event_tb34ActionPerformed

    private void tb36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb36ActionPerformed
        nowQuestion = Integer.parseInt(tb36.getText());
        exactQuestion();
    }//GEN-LAST:event_tb36ActionPerformed

    private void tb37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb37ActionPerformed
        nowQuestion = Integer.parseInt(tb37.getText());
        exactQuestion();
    }//GEN-LAST:event_tb37ActionPerformed

    private void tb38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb38ActionPerformed
        nowQuestion = Integer.parseInt(tb38.getText());
        exactQuestion();
    }//GEN-LAST:event_tb38ActionPerformed

    private void tb39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb39ActionPerformed
        nowQuestion = Integer.parseInt(tb39.getText());
        exactQuestion();
    }//GEN-LAST:event_tb39ActionPerformed

    private void tb40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb40ActionPerformed
        nowQuestion = Integer.parseInt(tb40.getText());
        exactQuestion();
    }//GEN-LAST:event_tb40ActionPerformed

    private void tb41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb41ActionPerformed
        nowQuestion = Integer.parseInt(tb41.getText());
        exactQuestion();
    }//GEN-LAST:event_tb41ActionPerformed

    private void tb42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb42ActionPerformed
        nowQuestion = Integer.parseInt(tb42.getText());
        exactQuestion();
    }//GEN-LAST:event_tb42ActionPerformed

    private void tb43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb43ActionPerformed
        nowQuestion = Integer.parseInt(tb43.getText());
        exactQuestion();
    }//GEN-LAST:event_tb43ActionPerformed

    private void tb44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb44ActionPerformed
        nowQuestion = Integer.parseInt(tb44.getText());
        exactQuestion();
    }//GEN-LAST:event_tb44ActionPerformed

    private void tb45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb45ActionPerformed
        nowQuestion = Integer.parseInt(tb45.getText());
        exactQuestion();
    }//GEN-LAST:event_tb45ActionPerformed

    private void tb46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb46ActionPerformed
        nowQuestion = Integer.parseInt(tb46.getText());
        exactQuestion();
    }//GEN-LAST:event_tb46ActionPerformed

    private void tb47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb47ActionPerformed
        nowQuestion = Integer.parseInt(tb47.getText());
        exactQuestion();
    }//GEN-LAST:event_tb47ActionPerformed

    private void tb48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb48ActionPerformed
        nowQuestion = Integer.parseInt(tb48.getText());
        exactQuestion();
    }//GEN-LAST:event_tb48ActionPerformed

    private void tb49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb49ActionPerformed
        nowQuestion = Integer.parseInt(tb49.getText());
        exactQuestion();
    }//GEN-LAST:event_tb49ActionPerformed

    private void tb50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb50ActionPerformed
        nowQuestion = Integer.parseInt(tb50.getText());
        exactQuestion();
    }//GEN-LAST:event_tb50ActionPerformed

    private void btClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearActionPerformed
        buttonGroup1.clearSelection();
        answered--;
        notAnswered++;
        lblAnswered.setText(Integer.toString(answered));
        lblNoteAnswered.setText(Integer.toString(notAnswered));
        answ1.setEnabled(true);
        answ2.setEnabled(true);
        answ3.setEnabled(true);
        answ4.setEnabled(true);
        buttons.get(nowQuestion - 1).setBackground(Color.lightGray);
        for (JRadioButton answer : answers) {
            answer.setOpaque(false);
            answer.setBackground(Color.black);
        }
        btClear.setEnabled(false);
    }//GEN-LAST:event_btClearActionPerformed

    private void initLookandFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            javax.swing.SwingUtilities.updateComponentTreeUI(this.jPanel1);
        } catch (Exception e) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton answ1;
    private javax.swing.JRadioButton answ2;
    private javax.swing.JRadioButton answ3;
    private javax.swing.JRadioButton answ4;
    private javax.swing.JButton btBack;
    private javax.swing.JButton btClear;
    private javax.swing.JButton btEndExam;
    private javax.swing.JButton btNext;
    private javax.swing.JButton btSubmit;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton13;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnswered;
    private javax.swing.JLabel lblBackgrond;
    private javax.swing.JLabel lblCandidateName;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblExamModule;
    private javax.swing.JLabel lblNoteAnswered;
    private javax.swing.JLabel lblQEDesc6;
    private javax.swing.JLabel lblQEDesc7;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTimeLeft;
    private javax.swing.JLabel lblTimeLeft1;
    private javax.swing.JLabel qNo;
    private javax.swing.JButton tb1;
    private javax.swing.JButton tb10;
    private javax.swing.JButton tb11;
    private javax.swing.JButton tb12;
    private javax.swing.JButton tb13;
    private javax.swing.JButton tb14;
    private javax.swing.JButton tb15;
    private javax.swing.JButton tb16;
    private javax.swing.JButton tb17;
    private javax.swing.JButton tb18;
    private javax.swing.JButton tb19;
    private javax.swing.JButton tb2;
    private javax.swing.JButton tb20;
    private javax.swing.JButton tb21;
    private javax.swing.JButton tb22;
    private javax.swing.JButton tb23;
    private javax.swing.JButton tb24;
    private javax.swing.JButton tb25;
    private javax.swing.JButton tb26;
    private javax.swing.JButton tb27;
    private javax.swing.JButton tb28;
    private javax.swing.JButton tb29;
    private javax.swing.JButton tb3;
    private javax.swing.JButton tb30;
    private javax.swing.JButton tb31;
    private javax.swing.JButton tb32;
    private javax.swing.JButton tb33;
    private javax.swing.JButton tb34;
    private javax.swing.JButton tb35;
    private javax.swing.JButton tb36;
    private javax.swing.JButton tb37;
    private javax.swing.JButton tb38;
    private javax.swing.JButton tb39;
    private javax.swing.JButton tb4;
    private javax.swing.JButton tb40;
    private javax.swing.JButton tb41;
    private javax.swing.JButton tb42;
    private javax.swing.JButton tb43;
    private javax.swing.JButton tb44;
    private javax.swing.JButton tb45;
    private javax.swing.JButton tb46;
    private javax.swing.JButton tb47;
    private javax.swing.JButton tb48;
    private javax.swing.JButton tb49;
    private javax.swing.JButton tb5;
    private javax.swing.JButton tb50;
    private javax.swing.JButton tb6;
    private javax.swing.JButton tb7;
    private javax.swing.JButton tb8;
    private javax.swing.JButton tb9;
    private javax.swing.JTextArea txtNote;
    // End of variables declaration//GEN-END:variables

    private void exactQuestion() {
        qNo.setText(Integer.toString(nowQuestion));
        buttonGroup1.clearSelection();
        btNext.setEnabled(true);
        btBack.setEnabled(true);
        Question curQuestion = questions.get(nowQuestion - 1);
        txtNote.setText(curQuestion.getQuestion());
        Collections.shuffle(answers);

        for (int i = 0; i < noOfQ; i++) {
            buttons.get(i).setBorder(null);
        }

        buttons.get(nowQuestion - 1).setBorder(new LineBorder(Color.black, 4));

        for (JRadioButton answer : answers) {
            answer.setOpaque(false);
            answer.setBackground(Color.black);
        }
        answers.get(0).setText(curQuestion.getCorrectAnswer());
        answers.get(1).setText(curQuestion.getAnswer1());
        answers.get(2).setText(curQuestion.getAnswer2());
        answers.get(3).setText(curQuestion.getAnswer3());
        answ1.setEnabled(true);
        answ2.setEnabled(true);
        answ3.setEnabled(true);
        answ4.setEnabled(true);
        btClear.setEnabled(false);
        if (studentAnswers[nowQuestion - 1] != null) {
            for (JRadioButton answer : answers) {
                if (answer.getText().equalsIgnoreCase(studentAnswers[nowQuestion - 1])) {
                    answer.setSelected(true);
                    answer.setBackground(Color.blue);
                    answer.setOpaque(true);
                    answ1.setEnabled(false);
                    answ2.setEnabled(false);
                    answ3.setEnabled(false);
                    answ4.setEnabled(false);
                    btSubmit.setEnabled(false);
                    btClear.setEnabled(true);
                }
            }
        }
    }

    private void addToggles() {
        buttons.add(tb1);
        buttons.add(tb2);
        buttons.add(tb3);
        buttons.add(tb4);
        buttons.add(tb5);
        buttons.add(tb6);
        buttons.add(tb7);
        buttons.add(tb8);
        buttons.add(tb9);
        buttons.add(tb10);
        buttons.add(tb11);
        buttons.add(tb12);
        buttons.add(tb13);
        buttons.add(tb14);
        buttons.add(tb15);
        buttons.add(tb16);
        buttons.add(tb17);
        buttons.add(tb18);
        buttons.add(tb19);
        buttons.add(tb20);
        buttons.add(tb21);
        buttons.add(tb22);
        buttons.add(tb23);
        buttons.add(tb24);
        buttons.add(tb25);
        buttons.add(tb26);
        buttons.add(tb27);
        buttons.add(tb28);
        buttons.add(tb29);
        buttons.add(tb30);
        buttons.add(tb31);
        buttons.add(tb32);
        buttons.add(tb33);
        buttons.add(tb34);
        buttons.add(tb35);
        buttons.add(tb36);
        buttons.add(tb37);
        buttons.add(tb38);
        buttons.add(tb39);
        buttons.add(tb40);
        buttons.add(tb41);
        buttons.add(tb42);
        buttons.add(tb43);
        buttons.add(tb44);
        buttons.add(tb45);
        buttons.add(tb46);
        buttons.add(tb47);
        buttons.add(tb48);
        buttons.add(tb49);
        buttons.add(tb50);

        for (int i = 0; i < 50; i++) {
            buttons.get(i).setVisible(false);
        }
    }

    private void showNoButton() {
        for (int i = 0; i < noOfQ; i++) {
            buttons.get(i).setVisible(true);
        }
    }
}
