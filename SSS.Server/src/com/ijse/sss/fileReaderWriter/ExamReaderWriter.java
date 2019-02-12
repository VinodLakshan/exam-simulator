/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.fileReaderWriter;

import com.ijse.sss.encryption.EncryptionDecryption;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import com.ijse.sss.model.Exam;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author NEOx
 */
public class ExamReaderWriter {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static File examFile = new File("./src/com/ijse/sss/files/model details/ExamFile.txt");

    public ExamReaderWriter() {

    }

    public boolean writeExam(Exam exam) throws ClassNotFoundException, IOException, ParseException {
        BufferedWriter bufferedWriter = null;
        try {
            readWriteLock.writeLock().lock();
            if (!examFile.exists()) {
                examFile.createNewFile();
            }

            ArrayList<Exam> allExams = readAllExams();
            int OldIdNo = 0;

            if (!allExams.isEmpty()) {
                String[] oldId = allExams.get(allExams.size() - 1).getEid().split("e");
                OldIdNo = Integer.parseInt(oldId[1]);
            }
            int newIdNo = OldIdNo + 1;
            String newEid = "e" + Integer.toString(newIdNo);
            String modules = "";
            String qIds = "";
            for (int i = 0; i < exam.getModules().length; i++) {
                modules += exam.getModules()[i] + "/";
            }

            for (int i = 0; i < exam.getQuestions().length; i++) {
                qIds += exam.getQuestions()[i] + "/";
            }

            String examData = newEid + "#";
            examData += exam.getExamName() + "#";
            examData += modules + "#";
            examData += qIds + "#";
            examData += exam.getDate() + "#";
            examData += exam.getTime() + "#";
            examData += exam.getDuration() + "#";
            examData += exam.getExamCode() + "#";

            String encryptedExam = EncryptionDecryption.encrypt(examData);

            FileWriter fileWriter = new FileWriter(examFile, true);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(encryptedExam);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            return examExist(newEid);
        } finally {

            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            readWriteLock.writeLock().unlock();
        }
    }

    public static Exam searchExam(String eid) throws ClassNotFoundException, IOException, ParseException {
        BufferedReader bufferedReader = null;
        try {
            readWriteLock.readLock().lock();
            Exam exam = null;
            FileReader fileReader = new FileReader(examFile);
            bufferedReader = new BufferedReader(fileReader);
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] examData = decryptedData.split("#");
                if (examData[0].equals(eid)) {
                    String[] modules = examData[2].split("/");
                    String[] questionIds = examData[4].split("/");

                    exam = new Exam(examData[0], examData[1], modules, questionIds, examData[4], examData[5], examData[6],examData[7]);
                }
            }
            return exam;

        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.readLock().unlock();
        }
    }

    public boolean updateExam(Exam exam) throws FileNotFoundException, IOException, ClassNotFoundException, ParseException {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            readWriteLock.writeLock().lock();
            FileReader fileReader = new FileReader(examFile);
            bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> examList = new ArrayList<>();
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] examData = decryptedData.split("#");
                if (examData[0].equals(exam.getEid())) {
                    String modules = "";
                    String qIds = "";

                    for (int i = 0; i < exam.getModules().length; i++) {
                        modules += exam.getModules()[i] + "/";
                    }

                    for (int i = 0; i < exam.getQuestions().length; i++) {
                        qIds += exam.getQuestions()[i] + "/";
                    }

                    String examLineData = exam.getEid() + "#";
                    examLineData += exam.getExamName() + "#";
                    examLineData += modules + "#";
                    examLineData += qIds + "#";
                    examLineData += exam.getDate() + "#";
                    examLineData += exam.getTime() + "#";
                    examLineData += exam.getDuration() + "#";
                    examLineData += exam.getExamCode() + "#";
                    examList.add(examLineData);
                } else {
                    examList.add(decryptedData);
                }
            }
            FileWriter fileWriter = new FileWriter(examFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (String string : examList) {
                String encrypted = EncryptionDecryption.encrypt(string);
                bufferedWriter.write(encrypted);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();

            return examExist(exam.getEid());
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.writeLock().unlock();
        }
    }

    public boolean deleteExam(String eid) throws FileNotFoundException, IOException, ClassNotFoundException, ParseException {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            readWriteLock.writeLock().lock();
            FileReader fileReader = new FileReader(examFile);
            bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> examList = new ArrayList<>();
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] examData = decryptedData.split("#");

                if (!examData[0].equals(eid)) {
                    examList.add(decryptedData);
                }
            }

            FileWriter fileWriter = new FileWriter(examFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (String string : examList) {
                String encrypted = EncryptionDecryption.encrypt(string);
                bufferedWriter.write(encrypted);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();

            return examExist(eid);
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            readWriteLock.writeLock().unlock();
        }
    }

    public ArrayList<Exam> readAllExams() throws FileNotFoundException, IOException {
        BufferedReader bufferedReader = null;
        try {
            readWriteLock.readLock().lock();
            FileReader fileReader = new FileReader(examFile);
            bufferedReader = new BufferedReader(fileReader);
            ArrayList<Exam> exams = new ArrayList<>();
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] examData = decryptedData.split("#");

                String[] modules = examData[2].split("/");
                String[] questionIds = examData[3].split("/");

                
                Exam exam = new Exam(examData[0], examData[1], modules, questionIds, examData[4], examData[5], examData[6],examData[7]);
                exams.add(exam);
            }
            return exams;

        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.readLock().unlock();
        }
    }

    private boolean examExist(String newEid) throws ClassNotFoundException, IOException, ParseException {
        Exam exam = searchExam(newEid);
        return exam != null;
    }

}
