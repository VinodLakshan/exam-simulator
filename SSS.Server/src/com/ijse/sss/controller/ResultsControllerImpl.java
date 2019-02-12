/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.controller;

import com.ijse.sss.model.Results;
import com.ijse.sss.service.factory.ServiceFactory;
import com.ijse.sss.service.factoryImpl.ServiceFactoryImpl;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author NEOx
 */
public class ResultsControllerImpl extends UnicastRemoteObject implements ResultController {

    ServiceFactory serviceFactory = new ServiceFactoryImpl();

    ResultsControllerImpl() throws RemoteException {

    }

    @Override
    public boolean writeResult(Results results) throws ClassNotFoundException, IOException, RemoteException, ParseException {
        return serviceFactory.getResultsService().writeResult(results);
    }

    @Override
    public Results searchStudentResults(String eId, String stId) throws ClassNotFoundException, IOException, RemoteException, ParseException {
        return serviceFactory.getResultsService().searchStudentResults(eId, stId);
    }

    @Override
    public ArrayList<Results> searchExamResults(String eId) throws ClassNotFoundException, IOException, RemoteException, ParseException {
        return serviceFactory.getResultsService().searchExamResults(eId);
    }

    @Override
    public ArrayList<Results> getAllExamResults() throws ClassNotFoundException, IOException, RemoteException, ParseException {
        return serviceFactory.getResultsService().getAllResults();
    }

    @Override
    public ArrayList<Results> searchStudentAllResults(String stId) throws ClassNotFoundException, IOException, RemoteException, ParseException {
        return serviceFactory.getResultsService().searchStudentAllResult(stId);
    }

    @Override
    public ArrayList<String> reportExams() throws ClassNotFoundException, IOException, RemoteException, ParseException {
        return serviceFactory.getResultsService().reportExams();
    }

}
