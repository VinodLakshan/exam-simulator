/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.fileReaderWriter;

import com.ijse.sss.encryption.EncryptionDecryption;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import com.ijse.sss.model.Teacher;
import java.io.BufferedWriter;
import java.text.ParseException;

/**
 *
 * @author NEOx
 */
public class TeacherReaderWriter {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static File teacherFile = new File("./src/com/ijse/sss/files/model details/TeacherFile.txt");

    public TeacherReaderWriter() {

    }

    public static boolean writeTeacher(Teacher teacher) throws ClassNotFoundException, IOException, FileNotFoundException, ParseException {
        BufferedWriter bufferedWriter = null;
        try {
            readWriteLock.writeLock().lock();
            if (!teacherFile.exists()) {
                teacherFile.createNewFile();
            }

            ArrayList<Teacher> allTeachers = readAllTeachers();
            int OldIdNo = 0;
            
            if (!allTeachers.isEmpty()) {
                String[] oldId = allTeachers.get(allTeachers.size()-1).gettId().split("t");
                OldIdNo = Integer.parseInt(oldId[1]);
            }
            int newIdNo = OldIdNo + 1;
            String newTid = "t" + Integer.toString(newIdNo);
            String teacherData = newTid + "#";
            teacherData += teacher.getUserName() + "#";
            teacherData += teacher.gettName() + "#";
            teacherData += teacher.getGender() + "#";
            teacherData += teacher.getPass() + "#";

            String encryptedTeacher = EncryptionDecryption.encrypt(teacherData);

            FileWriter fileWriter = new FileWriter(teacherFile, true);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(encryptedTeacher);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            return teacherExist(newTid);
        } finally {

            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            readWriteLock.writeLock().unlock();
        }
    }

    public static Teacher searchTeacher(String tidUserName) throws ClassNotFoundException, IOException, ParseException {
        BufferedReader bufferedReader = null;
        try {
            readWriteLock.readLock().lock();
            Teacher teacher = null;
            FileReader fileReader = new FileReader(teacherFile);
            bufferedReader = new BufferedReader(fileReader);
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] teacherData = decryptedData.split("#");
                
                if (teacherData[0].equals(tidUserName) || teacherData[1].equalsIgnoreCase(tidUserName)) {
                    teacher = new Teacher(teacherData[0], teacherData[1], teacherData[2], teacherData[3], teacherData[4]);
                }
            }
            return teacher;

        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.readLock().unlock();
        }
    }

    private static boolean teacherExist(String tid) throws ClassNotFoundException, IOException, ParseException {
        Teacher teacher = searchTeacher(tid);
        return teacher != null;
    }

    public static boolean updateTeacher(Teacher teacher) throws ClassNotFoundException, IOException, ParseException {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            readWriteLock.writeLock().lock();
            FileReader fileReader = new FileReader(teacherFile);
            bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> teacherList = new ArrayList<>();
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] teacheData = decryptedData.split("#");
                if (teacheData[0].equals(teacher.gettId())) {
                    String newTeacherData = teacher.gettId() + "#";
                    newTeacherData += teacher.getUserName() + "#";
                    newTeacherData += teacher.gettName() + "#";
                    newTeacherData += teacher.getGender() + "#";
                    newTeacherData += teacher.getPass() + "#";
                    teacherList.add(newTeacherData);

                } else {
                    teacherList.add(decryptedData);
                }
            }
            FileWriter fileWriter = new FileWriter(teacherFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (String string : teacherList) {
                String encrypted = EncryptionDecryption.encrypt(string);
                bufferedWriter.write(encrypted);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
                
            return teacherExist(teacher.gettId());
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.writeLock().unlock();
        }
    }

    public boolean deleteTeacher(String tidUserName) throws ClassNotFoundException, IOException, ParseException {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            readWriteLock.writeLock().lock();
            FileReader fileReader = new FileReader(teacherFile);
            bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> teacherList = new ArrayList<>();
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] teacherData = decryptedData.split("#");
                System.out.println("tid Usernm  "+ tidUserName);
                System.out.println(teacherData[0]);
                System.out.println(teacherData[1]);
                if (!teacherData[0].equals(tidUserName) && !teacherData[1].equals(tidUserName)) {
                    teacherList.add(decryptedData);
                }
            }

            FileWriter fileWriter = new FileWriter(teacherFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (String string : teacherList) {
                String encrypted = EncryptionDecryption.encrypt(string);
                bufferedWriter.write(encrypted);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
                
            return teacherExist(tidUserName);

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

    public static ArrayList<Teacher> readAllTeachers() throws FileNotFoundException, IOException, ParseException {
        BufferedReader bufferedReader = null;
        try {
            readWriteLock.readLock().lock();
            FileReader fileReader = new FileReader(teacherFile);
            bufferedReader = new BufferedReader(fileReader);
            ArrayList<Teacher> teachers = new ArrayList<>();
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] teacherData = decryptedData.split("#");

                Teacher teacher = new Teacher(teacherData[0], teacherData[1], teacherData[2], teacherData[3], teacherData[4]);
                teachers.add(teacher);
            }
            return teachers;

        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.readLock().unlock();
        }
    }
}
