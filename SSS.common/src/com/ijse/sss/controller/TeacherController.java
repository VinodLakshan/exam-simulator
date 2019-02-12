/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.controller;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import com.ijse.sss.model.Teacher;
import java.text.ParseException;

/**
 *
 * @author NEOx
 */
public interface TeacherController extends Remote {

    public boolean writeTeacher(Teacher teacher) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public boolean checkUserName(String userName) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public boolean checkLogin(String userName, String pass) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public boolean checkPass(String pass) throws RemoteException, IOException, ClassNotFoundException, ParseException;
    
    public Teacher searchTeacherByUserName(String userName) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public boolean updateTeacher(Teacher teacher) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public boolean deleteTeacher(String tid) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public ArrayList<Teacher> getAllTeachers() throws RemoteException, IOException, ClassNotFoundException, ParseException;
}
