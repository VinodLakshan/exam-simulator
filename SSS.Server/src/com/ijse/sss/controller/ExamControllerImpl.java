/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.controller;

import com.ijse.sss.controller.ExamController;
import com.ijse.sss.fileReaderWriter.ExamReaderWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import com.ijse.sss.model.Exam;
import com.ijse.sss.service.factory.ServiceFactory;
import com.ijse.sss.service.factoryImpl.ServiceFactoryImpl;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author NEOx
 */
public class ExamControllerImpl extends UnicastRemoteObject implements ExamController {

    private ServiceFactory serviceFactory = new ServiceFactoryImpl();

    public ExamControllerImpl() throws RemoteException {

    }

    @Override
    public boolean writeExam(Exam exam) throws ClassNotFoundException, IOException, RemoteException, FileNotFoundException,ParseException{
        return serviceFactory.getExamService().writeExam(exam);
    }

    @Override
    public Exam readExam(String eid) throws ClassNotFoundException, IOException, RemoteException, FileNotFoundException, ParseException {
        return serviceFactory.getExamService().readExam(eid);
    }

    @Override
    public boolean updateExam(Exam exam) throws ClassNotFoundException, IOException, RemoteException, FileNotFoundException, ParseException {
        return serviceFactory.getExamService().updateExam(exam);
    }

    @Override
    public boolean deleteExam(String eid) throws ClassNotFoundException, IOException, RemoteException, FileNotFoundException, ParseException {
        return serviceFactory.getExamService().deleteExam(eid);
    }

    @Override
    public ArrayList<Exam> getAllExam() throws ClassNotFoundException, IOException, RemoteException, ParseException {
        return serviceFactory.getExamService().getAllExam();
    }

    @Override
    public ArrayList<Exam> getModuledExam(String module) throws ClassNotFoundException, IOException, RemoteException, ParseException {
        return serviceFactory.getExamService().readExamBymodule(module);
    }

    @Override
    public ArrayList<String> getAllExamModules() throws ClassNotFoundException, IOException, RemoteException, ParseException {
        return serviceFactory.getExamService().getAllExamModules();
    }

}
