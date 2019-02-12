/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.controller;

import com.ijse.sss.controller.TeacherController;
import com.ijse.sss.fileReaderWriter.TeacherReaderWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import com.ijse.sss.model.Teacher;
import com.ijse.sss.service.factory.ServiceFactory;
import com.ijse.sss.service.factoryImpl.ServiceFactoryImpl;
import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 *
 * @author NEOx
 */
public class TeacherControllerImpl extends UnicastRemoteObject implements TeacherController {

    private ServiceFactory serviceFactory = new ServiceFactoryImpl();

    public TeacherControllerImpl() throws RemoteException, IOException {

    }

    @Override
    public boolean writeTeacher(Teacher teacher) throws RemoteException, IOException, ClassNotFoundException, ParseException {
        return serviceFactory.getTeacherService().addTeaacher(teacher);
    }

    @Override
    public boolean checkUserName(String userName) throws RemoteException, IOException, ClassNotFoundException, ParseException {
        return serviceFactory.getTeacherService().checkUserName(userName);
    }

    @Override
    public boolean checkLogin(String userName, String pass) throws RemoteException, IOException, ClassNotFoundException, ParseException {
        return serviceFactory.getTeacherService().checkLogIn(userName, pass);
    }

    @Override
    public boolean updateTeacher(Teacher teacher) throws RemoteException, IOException, ClassNotFoundException, FileNotFoundException, ParseException {
        return serviceFactory.getTeacherService().updateTeaacher(teacher);
    }

    @Override
    public boolean deleteTeacher(String tid) throws RemoteException, IOException, ClassNotFoundException, FileNotFoundException, ParseException {
        return serviceFactory.getTeacherService().deleteTeaacher(tid);
    }

    @Override
    public ArrayList<Teacher> getAllTeachers() throws RemoteException, IOException, ClassNotFoundException, FileNotFoundException, ParseException {
        return serviceFactory.getTeacherService().getTeachers();
    }

    @Override
    public Teacher searchTeacherByUserName(String userName) throws RemoteException, IOException, ClassNotFoundException, FileNotFoundException, ParseException {
        return serviceFactory.getTeacherService().searchTeacherByUserName(userName);
    }

    @Override
    public boolean checkPass(String pass) throws RemoteException, IOException, ClassNotFoundException, ParseException {
        return serviceFactory.getTeacherService().checkPass(pass);
    }

}
