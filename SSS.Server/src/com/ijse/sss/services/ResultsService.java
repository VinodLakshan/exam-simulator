/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.services;

import com.ijse.sss.fileAccessFactory.FileAccessFactory;
import com.ijse.sss.fileAccessFactoryImpl.FileAccessFactoryImpl;
import com.ijse.sss.model.Results;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author NEOx
 */
public class ResultsService {

    FileAccessFactory fileAccessFactory = new FileAccessFactoryImpl();

    public boolean writeResult(Results results) throws RemoteException, IOException {
        return fileAccessFactory.getResultsReaderWriter().writeResult(results);
    }

    public Results searchStudentResults(String eId, String stId) throws RemoteException, IOException {
        return fileAccessFactory.getResultsReaderWriter().searchStudentResults(eId, stId);
    }

    public ArrayList<Results> searchExamResults(String eId) throws RemoteException, IOException {
        ArrayList<Results> allExamResults = fileAccessFactory.getResultsReaderWriter().getAllExamResults();
        ArrayList<Results> examResults = new ArrayList<>();

        for (Results examResult : allExamResults) {
            if (examResult.geteId().equals(eId)) {
                examResults.add(examResult);
            }
        }
        return examResults;
    }

    public ArrayList<Results> getAllResults() throws RemoteException, IOException {
        return fileAccessFactory.getResultsReaderWriter().getAllExamResults();
    }

    public ArrayList<Results> searchStudentAllResult(String stId) throws RemoteException, IOException {
        ArrayList<Results> allExamResults = fileAccessFactory.getResultsReaderWriter().getAllExamResults();
        ArrayList<Results> studentAllResults = new ArrayList<>();

        for (Results examResult : allExamResults) {
            if (examResult.getStId().equals(stId)) {
                studentAllResults.add(examResult);
            }
        }
        return studentAllResults;
    }

    public ArrayList<String> reportExams() throws RemoteException, IOException {
        return fileAccessFactory.getResultsReaderWriter().reportExams();
    }
}
