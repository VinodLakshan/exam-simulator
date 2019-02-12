/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.controller;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import com.ijse.sss.model.Student;
import com.ijse.sss.observer.StudentObserver;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author NEOx
 */
public interface StudentController extends Remote {

    public void addStudentObserver(StudentObserver studentObserver) throws RemoteException;

    public void removeStudentObserver(StudentObserver studentObserver) throws RemoteException;

    public void previewNotification(String msg) throws IOException, ClassNotFoundException, RemoteException;

    public boolean writeStudent(Student student) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public boolean checkUserName(String userName) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public boolean checkLogin(String userName, String pass) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public boolean updateStudent(Student student) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public boolean deleteStudent(String sId) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public Student searchStudent(String sId) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public boolean checkPass(String pass) throws RemoteException, IOException, ClassNotFoundException, ParseException;
    
    public ArrayList<Student> getAllStudent()throws RemoteException, IOException, ClassNotFoundException, ParseException;
}
