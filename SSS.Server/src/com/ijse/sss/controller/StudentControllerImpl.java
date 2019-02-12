/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.controller;

import com.ijse.sss.controller.StudentController;
import com.ijse.sss.fileReaderWriter.QuestionReaderWriter;
import com.ijse.sss.fileReaderWriter.StudentReaderWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import com.ijse.sss.model.Student;
import com.ijse.sss.observer.StudentObserver;
import com.ijse.sss.observerble.StudentObserverble;
import com.ijse.sss.service.factory.ServiceFactory;
import com.ijse.sss.service.factoryImpl.ServiceFactoryImpl;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author NEOx
 */
public class StudentControllerImpl extends UnicastRemoteObject implements StudentController {

    private ServiceFactory serviceFactory = new ServiceFactoryImpl();
    private final static StudentObserverble studentObserverble = new StudentObserverble();

    public StudentControllerImpl() throws RemoteException, IOException {

    }

    @Override
    public void addStudentObserver(StudentObserver studentObserver) throws RemoteException{
        studentObserverble.addStudentObserver(studentObserver);
    }

    @Override
    public void removeStudentObserver(StudentObserver studentObserver) throws RemoteException{
        studentObserverble.RemoveStudentObserver(studentObserver);
    }

    @Override
    public void previewNotification(String msg) throws IOException, ClassNotFoundException, RemoteException {
        studentObserverble.notifyObserversNotification(msg);
    }

    @Override
    public boolean writeStudent(Student student) throws RemoteException, IOException, ClassNotFoundException, FileNotFoundException, ParseException {
        return serviceFactory.getStudentService().addStudent(student);
    }

    @Override
    public boolean checkUserName(String userName) throws RemoteException, IOException, ClassNotFoundException, ParseException {
        return serviceFactory.getStudentService().checkUserName(userName);
    }

    @Override
    public boolean checkLogin(String userName, String pass) throws RemoteException, IOException, ClassNotFoundException, ParseException {
        return serviceFactory.getStudentService().checkLogIn(userName, pass);
    }

    @Override
    public boolean updateStudent(Student student) throws RemoteException, IOException, ClassNotFoundException, ParseException{
        return serviceFactory.getStudentService().updateStudent(student);
    }

    @Override
    public boolean deleteStudent(String sId) throws RemoteException, IOException, ClassNotFoundException, FileNotFoundException, ParseException {
        return serviceFactory.getStudentService().deleteStudent(sId);
    }

    @Override
    public Student searchStudent(String sId) throws RemoteException, IOException, ClassNotFoundException, ParseException {
        return serviceFactory.getStudentService().searchStudent(sId);
    }

    @Override
    public boolean checkPass(String pass) throws RemoteException, IOException, ClassNotFoundException, ParseException {
        return  serviceFactory.getStudentService().checkPass(pass);
    }

    @Override
    public ArrayList<Student> getAllStudent() throws RemoteException, IOException, ClassNotFoundException, ParseException {
        return serviceFactory.getStudentService().getStudents();
    }
}
