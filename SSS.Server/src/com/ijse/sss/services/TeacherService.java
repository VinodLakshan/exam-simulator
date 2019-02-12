/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.services;

import com.ijse.sss.fileAccessFactory.FileAccessFactory;
import com.ijse.sss.fileAccessFactoryImpl.FileAccessFactoryImpl;
import com.ijse.sss.model.Student;
import com.ijse.sss.model.Teacher;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author NEOx
 */
public class TeacherService {

    private FileAccessFactory fileAccessFactory = new FileAccessFactoryImpl();

    public boolean addTeaacher(Teacher teacher) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getTeacherReaderWriter().writeTeacher(teacher);
    }

    public ArrayList<Teacher> getTeachers() throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getTeacherReaderWriter().readAllTeachers();
    }

    public boolean checkUserName(String userName) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        ArrayList<Teacher> allTeachers = fileAccessFactory.getTeacherReaderWriter().readAllTeachers();

        for (Teacher teacher : allTeachers) {
            if (teacher.getUserName().equalsIgnoreCase(userName)) {
                return true;
            }
        }

        return false;
    }

    public boolean checkPass(String pass) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        ArrayList<Teacher> allTeachers = fileAccessFactory.getTeacherReaderWriter().readAllTeachers();

        for (Teacher teacher : allTeachers) {
            if (teacher.getPass().equals(pass)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLogIn(String userName, String pass) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        ArrayList<Teacher> allTeachers = fileAccessFactory.getTeacherReaderWriter().readAllTeachers();

        for (Teacher teacher : allTeachers) {
            if (teacher.getUserName().equalsIgnoreCase(userName) && teacher.getPass().equals(pass)) {
                return true;
            }
        }

        return false;
    }

    public Teacher searchTeacherByUserName(String userName) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getTeacherReaderWriter().searchTeacher(userName);
    }

    public boolean updateTeaacher(Teacher teacher) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getTeacherReaderWriter().updateTeacher(teacher);
    }

    public boolean deleteTeaacher(String tid) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getTeacherReaderWriter().deleteTeacher(tid);
    }
}
