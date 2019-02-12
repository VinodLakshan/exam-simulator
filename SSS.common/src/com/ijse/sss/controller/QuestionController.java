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
import com.ijse.sss.model.Question;
import java.text.ParseException;

/**
 *
 * @author NEOx
 */
public interface QuestionController extends Remote {

    public boolean writeQuestion(Question question) throws ClassNotFoundException, IOException, RemoteException, ParseException;

    public Question searchQuestion(String qid) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public ArrayList<Question> searchGroupQuestions(String[] qids) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public boolean updateQuestion(Question question) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public boolean deleteQuestion(String qid) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public ArrayList<Question> readQuestionsModule(String module) throws ClassNotFoundException, IOException, RemoteException, ParseException;
    
    public ArrayList<String> getAllQuestionModules()throws ClassNotFoundException, IOException, RemoteException, ParseException;

    public ArrayList<Question> readQuestionQtyModel(int qty, String module) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public ArrayList<Question> readAllQuestions() throws ClassNotFoundException, IOException, RemoteException, ParseException;

    public boolean reserveQuestion(String qid) throws RemoteException;

    public boolean releaseQuestion(String qid) throws RemoteException;
}
