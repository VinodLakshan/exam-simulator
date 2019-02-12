/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.controller;

import com.ijse.sss.model.Results;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author NEOx
 */
public interface ResultController extends Remote {

    public boolean writeResult(Results results) throws ClassNotFoundException, IOException, RemoteException, ParseException;

    public Results searchStudentResults(String eId, String stId) throws ClassNotFoundException, IOException, RemoteException, ParseException;

    public ArrayList<Results> searchStudentAllResults(String stId) throws ClassNotFoundException, IOException, RemoteException, ParseException;

    public ArrayList<Results> searchExamResults(String eId) throws ClassNotFoundException, IOException, RemoteException, ParseException;

    public ArrayList<Results> getAllExamResults() throws ClassNotFoundException, IOException, RemoteException, ParseException;

    public ArrayList<String> reportExams() throws ClassNotFoundException, IOException, RemoteException, ParseException;
}
