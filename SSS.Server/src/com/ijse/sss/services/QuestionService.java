/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.services;

import com.ijse.sss.fileAccessFactory.FileAccessFactory;
import com.ijse.sss.fileAccessFactoryImpl.FileAccessFactoryImpl;
import com.ijse.sss.fileReaderWriter.QuestionReaderWriter;
import com.ijse.sss.model.Note;
import com.ijse.sss.model.Question;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author NEOx
 */
public class QuestionService {

    private FileAccessFactory fileAccessFactory = new FileAccessFactoryImpl();

    public boolean addQuestion(Question question) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getQuestionReaderWriter().writeQuestion(question);
    }

    public Question searchQuestion(String qid) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getQuestionReaderWriter().searchQuestion(qid);
    }

    public boolean updateQuestion(Question question) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getQuestionReaderWriter().updateQuestion(question);
    }

    public boolean deleteQuestion(String qid) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getQuestionReaderWriter().deleteQuestion(qid);
    }

    public ArrayList<Question> readQuestionsModule(String module) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getQuestionReaderWriter().readQuestionsModule(module);
    }

    public ArrayList<Question> readAllQuestion() throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getQuestionReaderWriter().readAllQuestions();
    }

    public ArrayList<Question> realQuestionsQtyModel(int qty, String module) throws IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        ArrayList<Question> readModuledQuestions = fileAccessFactory.getQuestionReaderWriter().readQuestionsModule(module);
        ArrayList<Question> matchingQuestions = new ArrayList<>();
        int i = 0;
        if (readModuledQuestions.size() >= qty) {
            Collections.shuffle(readModuledQuestions);
            while (matchingQuestions.size() < qty) {
                if (!matchingQuestions.contains(readModuledQuestions.get(i))) {
                    matchingQuestions.add(readModuledQuestions.get(i));
                }
                i++;
            }
        }
        return matchingQuestions;
    }

    public ArrayList<Question> searchGroupQuestions(String[] qids) throws IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        ArrayList<Question> readAllQuestions = fileAccessFactory.getQuestionReaderWriter().readAllQuestions();
        ArrayList<Question> AllMatchingQuestions = new ArrayList<>();
        for (Question readQuestion : readAllQuestions) {
            for (int i = 0; i < qids.length; i++) {
                if (readQuestion.getQid().equals(qids[i])) {
                    AllMatchingQuestions.add(readQuestion);
                }
            }
        }
        return AllMatchingQuestions;
    }

    public ArrayList<String> getAllQuestionModule() throws IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        ArrayList<Question> readAllQuestions = fileAccessFactory.getQuestionReaderWriter().readAllQuestions();
        ArrayList<String> allModules = new ArrayList<>();
        for (Question readQuestion : readAllQuestions) {
            if (!allModules.contains(readQuestion.getModule())) {
                allModules.add(readQuestion.getModule());
            }
        }
        return allModules;
    }
}
