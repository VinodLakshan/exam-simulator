/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.services;

import com.ijse.sss.fileAccessFactory.FileAccessFactory;
import com.ijse.sss.fileAccessFactoryImpl.FileAccessFactoryImpl;
import com.ijse.sss.model.Note;
import com.ijse.sss.model.Student;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author NEOx
 */
public class StudentService {

    private FileAccessFactory fileAccessFactory = new FileAccessFactoryImpl();

    public boolean addStudent(Student student) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getStudentReaderWriter().writeStudent(student);
    }

    public ArrayList<Student> getStudents() throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getStudentReaderWriter().readAllStudents();
    }

    public boolean checkUserName(String userName) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        ArrayList<Student> allStudents = fileAccessFactory.getStudentReaderWriter().readAllStudents();

        for (Student student : allStudents) {
            if (student.getUserName().equalsIgnoreCase(userName)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLogIn(String userName, String pass) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        ArrayList<Student> allStudents = fileAccessFactory.getStudentReaderWriter().readAllStudents();

        for (Student student : allStudents) {
            if (student.getUserName().equalsIgnoreCase(userName) && student.getPass().equals(pass)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkPass(String pass) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        ArrayList<Student> allTeachers = fileAccessFactory.getStudentReaderWriter().readAllStudents();

        for (Student teacher : allTeachers) {
            if (teacher.getPass().equals(pass)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean updateStudent(Student student) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getStudentReaderWriter().updateStudent(student);
    }
    
    public boolean deleteStudent(String sid) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getStudentReaderWriter().deleteStudent(sid);
    }
    
    public Student searchStudent(String sid) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getStudentReaderWriter().searchStudent(sid);
    }
}
