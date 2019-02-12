/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.controller;

import com.ijse.sss.controller.QuestionController;
import com.ijse.sss.fileReaderWriter.QuestionReaderWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import com.ijse.sss.model.Question;
import com.ijse.sss.reservation.QuestionReserver;
import com.ijse.sss.service.factory.ServiceFactory;
import com.ijse.sss.service.factoryImpl.ServiceFactoryImpl;
import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 *
 * @author NEOx
 */
public class QuestionControllerImpl extends UnicastRemoteObject implements QuestionController {

    private ServiceFactory serviceFactory = new ServiceFactoryImpl();
    private static final QuestionReserver questionReserver = new QuestionReserver();

    public QuestionControllerImpl() throws RemoteException {

    }

    @Override
    public boolean writeQuestion(Question question) throws ClassNotFoundException, IOException, RemoteException, FileNotFoundException, ParseException {
        return serviceFactory.getQuestionService().addQuestion(question);
    }

    @Override
    public boolean updateQuestion(Question question) throws RemoteException, IOException, ClassNotFoundException, ParseException {
        return serviceFactory.getQuestionService().updateQuestion(question);
    }

    @Override
    public boolean deleteQuestion(String qid) throws RemoteException, IOException, ClassNotFoundException, ParseException {
        return serviceFactory.getQuestionService().deleteQuestion(qid);
    }

    @Override
    public ArrayList<Question> readQuestionsModule(String module) throws ClassNotFoundException, IOException, RemoteException, ParseException {
        return serviceFactory.getQuestionService().readQuestionsModule(module);
    }

    @Override
    public ArrayList<Question> readAllQuestions() throws ClassNotFoundException, IOException, RemoteException, ParseException {
        return serviceFactory.getQuestionService().readAllQuestion();
    }

    @Override
    public boolean reserveQuestion(String qid) throws RemoteException {
        return questionReserver.reserveQuestion(qid, this);
    }

    @Override
    public boolean releaseQuestion(String qid) throws RemoteException {
        return questionReserver.releaseQuestion(qid, this);
    }

    @Override
    public Question searchQuestion(String qid) throws RemoteException, IOException, ClassNotFoundException, ParseException {
        return serviceFactory.getQuestionService().searchQuestion(qid);
    }

    @Override
    public ArrayList<Question> readQuestionQtyModel(int qty, String module) throws RemoteException, IOException, ClassNotFoundException, ParseException {
       return serviceFactory.getQuestionService().realQuestionsQtyModel(qty,module);
    }

    @Override
    public ArrayList<Question> searchGroupQuestions(String[] qids) throws RemoteException, IOException, ClassNotFoundException, ParseException {
        return serviceFactory.getQuestionService().searchGroupQuestions(qids);
    }

    @Override
    public ArrayList<String> getAllQuestionModules() throws ClassNotFoundException, IOException, RemoteException, ParseException {
        return serviceFactory.getQuestionService().getAllQuestionModule();
    }

}
