package com.ijse.sss.fileReaderWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.ijse.sss.encryption.EncryptionDecryption;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import com.ijse.sss.model.Student;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 *
 * @author NEOx
 */
public class StudentReaderWriter {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static File studentFile = new File("./src/com/ijse/sss/files/model details/StudentFile.txt");

    public StudentReaderWriter() {

    }

    public static boolean writeStudent(Student student) throws ClassNotFoundException, IOException, ParseException {
        BufferedWriter bufferedWriter = null;
        try {
            readWriteLock.writeLock().lock();
            if (!studentFile.exists()) {
                studentFile.createNewFile();
            }

            ArrayList<Student> allStudents = readAllStudents();
            int OldIdNo = 0;
            
            if (!allStudents.isEmpty()) {
                String[] oldId = allStudents.get(allStudents.size() - 1).getStId().split("s");
                OldIdNo = Integer.parseInt(oldId[1]);
            }
            int newIdNo = OldIdNo + 1;
            String newSid = "s" + Integer.toString(newIdNo);
            String studentData = newSid + "#";
            studentData += student.getUserName() + "#";
            studentData += student.getStName() + "#";
            studentData += student.getDob() + "#";
            studentData += student.getGender() + "#";
            studentData += student.getPass() + "#";

            String encryptedStudent = EncryptionDecryption.encrypt(studentData);

            FileWriter fileWriter = new FileWriter(studentFile, true);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(encryptedStudent);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            return studentExist(newSid);
        } finally {

            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            readWriteLock.writeLock().unlock();
        }
    }

    public static Student searchStudent(String sidUserName) throws ClassNotFoundException, IOException, ParseException {
        BufferedReader bufferedReader = null;
        try {
            readWriteLock.readLock().lock();
            Student student = null;
            FileReader fileReader = new FileReader(studentFile);
            bufferedReader = new BufferedReader(fileReader);
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] studentData = decryptedData.split("#");
                if (studentData[0].equals(sidUserName) || studentData[1].equalsIgnoreCase(sidUserName)) {
                    student = new Student(studentData[0], studentData[1], studentData[2], studentData[3], studentData[4], studentData[5]);
                }
            }
            return student;

        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.readLock().unlock();
        }
    }

    private static boolean studentExist(String sid) throws ClassNotFoundException, IOException, ParseException {
        Student student = searchStudent(sid);
        return student != null;
    }

    public static boolean updateStudent(Student student) throws FileNotFoundException, IOException, ClassNotFoundException, ParseException {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            readWriteLock.writeLock().lock();
            FileReader fileReader = new FileReader(studentFile);
            bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> studentList = new ArrayList<>();
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] studentData = decryptedData.split("#");
                if (studentData[0].equals(student.getStId())) {
                    String newStudentData = student.getStId()+ "#";
                    newStudentData += student.getUserName()+"#";
                    newStudentData += student.getStName()+"#";
                    newStudentData += student.getDob()+"#";
                    newStudentData += student.getGender()+"#";
                    newStudentData += student.getPass()+"#";
                    studentList.add(newStudentData);
                    
                } else {
                    studentList.add(decryptedData);
                }
            }
            FileWriter fileWriter = new FileWriter(studentFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (String string : studentList) {
                String encrypted=EncryptionDecryption.encrypt(string);
                bufferedWriter.write(encrypted);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            
            return studentExist(student.getStId());
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.writeLock().unlock();
        }
    }

    public static boolean deleteStudent(String sId) throws FileNotFoundException, IOException, ClassNotFoundException, ParseException {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            readWriteLock.writeLock().lock();
            FileReader fileReader = new FileReader(studentFile);
            bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> studentList = new ArrayList<>();
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] studentData = decryptedData.split("#");
                if (!studentData[0].equals(sId) && !studentData[1].equalsIgnoreCase(sId)) {
                    studentList.add(decryptedData);
                }
            }

            FileWriter fileWriter = new FileWriter(studentFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (String string : studentList) {
                String encrypted = EncryptionDecryption.encrypt(string);
                bufferedWriter.write(encrypted);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            
            return studentExist(sId);

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

    public static ArrayList<Student> readAllStudents() throws FileNotFoundException, IOException {
        BufferedReader bufferedReader = null;
        try {
            readWriteLock.readLock().lock();
            FileReader fileReader = new FileReader(studentFile);
            bufferedReader = new BufferedReader(fileReader);
            ArrayList<Student> students = new ArrayList<>();
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] studentData = decryptedData.split("#");

                Student student = new Student(studentData[0], studentData[1], studentData[2], studentData[3], studentData[4], studentData[5]);
                students.add(student);
            }
            return students;

        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.readLock().unlock();
        }
    }
}
