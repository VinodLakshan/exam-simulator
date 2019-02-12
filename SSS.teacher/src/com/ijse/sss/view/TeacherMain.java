/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.view;

import com.ijse.sss.connector.ServerConnector;
import com.ijse.sss.controller.StudentController;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author NEOx
 */
public class TeacherMain extends javax.swing.JFrame {

    /**
     * Creates new form TeacherMain
     */
    
    public TeacherMain(String userName) {
        initComponents();
        autoDateTime();

        setTitle(userName);
        txtUserName.setText(userName);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        btLogout4 = new javax.swing.JButton();
        btReports = new javax.swing.JButton();
        btMyNotes = new javax.swing.JButton();
        btQuestions = new javax.swing.JButton();
        btNotes = new javax.swing.JButton();
        btExams = new javax.swing.JButton();
        btExams2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblBackgrond = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1920, 1080));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btLogout4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/logout.jpg"))); // NOI18N
        btLogout4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btLogout4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btLogout4MouseExited(evt);
            }
        });
        btLogout4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogout4ActionPerformed(evt);
            }
        });
        jPanel5.add(btLogout4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 190, 70));

        btReports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/Reports.jpg"))); // NOI18N
        btReports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btReportsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btReportsMouseExited(evt);
            }
        });
        btReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReportsActionPerformed(evt);
            }
        });
        jPanel5.add(btReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 630, 390, 340));

        btMyNotes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/Mynotes.jpg"))); // NOI18N
        btMyNotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btMyNotesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btMyNotesMouseExited(evt);
            }
        });
        btMyNotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMyNotesActionPerformed(evt);
            }
        });
        jPanel5.add(btMyNotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 630, 390, 340));

        btQuestions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/questions.jpg"))); // NOI18N
        btQuestions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btQuestionsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btQuestionsMouseExited(evt);
            }
        });
        btQuestions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuestionsActionPerformed(evt);
            }
        });
        jPanel5.add(btQuestions, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 240, 390, 340));

        btNotes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/notes.jpg"))); // NOI18N
        btNotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btNotesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btNotesMouseExited(evt);
            }
        });
        btNotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNotesActionPerformed(evt);
            }
        });
        jPanel5.add(btNotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 240, 390, 340));

        btExams.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/Exams.jpg"))); // NOI18N
        btExams.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btExamsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btExamsMouseExited(evt);
            }
        });
        btExams.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExamsActionPerformed(evt);
            }
        });
        jPanel5.add(btExams, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 240, 390, 340));

        btExams2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/myAccount.jpg"))); // NOI18N
        btExams2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btExams2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btExams2MouseExited(evt);
            }
        });
        btExams2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExams2ActionPerformed(evt);
            }
        });
        jPanel5.add(btExams2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 630, 390, 340));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 1860, 820));

        jLabel3.setFont(new java.awt.Font("Microsoft Himalaya", 1, 80)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("HOME");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 80, 220, 90));

        txtUserName.setFont(new java.awt.Font("Times New Roman", 1, 60)); // NOI18N
        txtUserName.setForeground(new java.awt.Color(255, 255, 255));
        txtUserName.setText("ssssss");
        jPanel5.add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 540, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/user-xxl.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 4));
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 100, 100));

        lblTime.setFont(new java.awt.Font("Microsoft Himalaya", 1, 100)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        lblTime.setText("08:00");
        jPanel5.add(lblTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 50, 330, 90));

        lblDate.setFont(new java.awt.Font("Microsoft Himalaya", 1, 80)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setText("08:00");
        jPanel5.add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 120, 310, 90));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1860, 170));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 1860, 820));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/blackTransparent.png"))); // NOI18N
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1860, 170));

        jLabel2.setFont(new java.awt.Font("Microsoft Himalaya", 3, 80)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/white transparent.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255), 4));
        jLabel2.setPreferredSize(new java.awt.Dimension(1920, 1000));
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1880, 1020));

        lblBackgrond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/technology-1106438_960_720darky.jpg"))); // NOI18N
        jPanel5.add(lblBackgrond, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btLogout4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLogout4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btLogout4MouseEntered

    private void btLogout4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLogout4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btLogout4MouseExited

    private void btLogout4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogout4ActionPerformed
        int res = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout..??");
        if (res == 0) {
            new LoginTeacher().setVisible(true);
            this.dispose();
        }

    }//GEN-LAST:event_btLogout4ActionPerformed

    private void btReportsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btReportsMouseEntered
        btReports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/ReportsSel.jpg")));
    }//GEN-LAST:event_btReportsMouseEntered

    private void btReportsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btReportsMouseExited
        btReports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/Reports.jpg")));
    }//GEN-LAST:event_btReportsMouseExited

    private void btMyNotesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btMyNotesMouseEntered
        btMyNotes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/MynotesSel.jpg")));
    }//GEN-LAST:event_btMyNotesMouseEntered

    private void btMyNotesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btMyNotesMouseExited
        btMyNotes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/Mynotes.jpg")));
    }//GEN-LAST:event_btMyNotesMouseExited

    private void btQuestionsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btQuestionsMouseEntered
        btQuestions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/questionsSel.jpg")));
    }//GEN-LAST:event_btQuestionsMouseEntered

    private void btQuestionsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btQuestionsMouseExited
        btQuestions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/questions.jpg")));
    }//GEN-LAST:event_btQuestionsMouseExited

    private void btNotesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btNotesMouseEntered
        btNotes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/notesSel.jpg")));
    }//GEN-LAST:event_btNotesMouseEntered

    private void btNotesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btNotesMouseExited
        btNotes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/notes.jpg")));
    }//GEN-LAST:event_btNotesMouseExited

    private void btNotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNotesActionPerformed
        new NotesForm(this).setVisible(true);
        this.hide();
    }//GEN-LAST:event_btNotesActionPerformed

    private void btExamsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExamsMouseEntered
        btExams.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/ExamsSel.jpg")));
    }//GEN-LAST:event_btExamsMouseEntered

    private void btExamsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExamsMouseExited
        btExams.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/Exams.jpg")));
    }//GEN-LAST:event_btExamsMouseExited

    private void btExams2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExams2MouseEntered
        btExams2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/myAccountSel.jpg")));
    }//GEN-LAST:event_btExams2MouseEntered

    private void btExams2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExams2MouseExited
        btExams2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijse/sss/images/myAccount.jpg")));
    }//GEN-LAST:event_btExams2MouseExited

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int res = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout..??");
        if (res == 0) {
            new LoginTeacher().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    private void btQuestionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuestionsActionPerformed
        new QuestionsForm(this).setVisible(true);
        this.hide();
    }//GEN-LAST:event_btQuestionsActionPerformed

    private void btExamsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExamsActionPerformed
        new ExamForm(this).setVisible(true);
        this.hide();
    }//GEN-LAST:event_btExamsActionPerformed

    private void btExams2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExams2ActionPerformed
        new MyAccount(this, this.getTitle()).setVisible(true);
        this.hide();
    }//GEN-LAST:event_btExams2ActionPerformed

    private void btReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportsActionPerformed
        new ReportsForm(this).setVisible(true);
        this.hide();
    }//GEN-LAST:event_btReportsActionPerformed

    private void btMyNotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMyNotesActionPerformed
        new MyNotes(this).setVisible(true);
        this.hide();
    }//GEN-LAST:event_btMyNotesActionPerformed

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
            java.util.logging.Logger.getLogger(TeacherMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeacherMain("sssss").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btExams;
    private javax.swing.JButton btExams2;
    private javax.swing.JButton btLogout4;
    private javax.swing.JButton btMyNotes;
    private javax.swing.JButton btNotes;
    private javax.swing.JButton btQuestions;
    private javax.swing.JButton btReports;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblBackgrond;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTime;
    public static javax.swing.JLabel txtUserName;
    // End of variables declaration//GEN-END:variables
}
