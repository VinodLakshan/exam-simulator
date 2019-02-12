/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.controller;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import com.ijse.sss.model.Exam;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author NEOx
 */
public interface ExamController extends Remote {

    public boolean writeExam(Exam exam) throws ClassNotFoundException, IOException, RemoteException, ParseException;

    public Exam readExam(String eid) throws ClassNotFoundException, IOException, RemoteException, ParseException;
    
    public ArrayList<Exam> getAllExam() throws ClassNotFoundException, IOException, RemoteException, ParseException;

    public boolean updateExam(Exam exam) throws ClassNotFoundException, IOException, RemoteException, ParseException;

    public boolean deleteExam(String eid) throws ClassNotFoundException, IOException, RemoteException, ParseException;

    public ArrayList<Exam> getModuledExam(String module) throws ClassNotFoundException, IOException, RemoteException, ParseException;
    
    public ArrayList<String> getAllExamModules() throws ClassNotFoundException, IOException, RemoteException, ParseException;
}
