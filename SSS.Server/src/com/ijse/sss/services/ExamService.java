/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.services;

import com.ijse.sss.fileAccessFactory.FileAccessFactory;
import com.ijse.sss.fileAccessFactoryImpl.FileAccessFactoryImpl;
import com.ijse.sss.model.Exam;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author NEOx
 */
public class ExamService {

    private FileAccessFactory fileAccessFactory = new FileAccessFactoryImpl();

    public boolean writeExam(Exam exam) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException, ParseException {
        return fileAccessFactory.getExamReaderWriter().writeExam(exam);
    }

    public boolean updateExam(Exam exam) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException, ParseException {
        return fileAccessFactory.getExamReaderWriter().updateExam(exam);
    }

    public boolean deleteExam(String eid) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException, ParseException {
        return fileAccessFactory.getExamReaderWriter().deleteExam(eid);
    }
    
    public ArrayList<Exam> getAllExam() throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException, ParseException {
        return fileAccessFactory.getExamReaderWriter().readAllExams();
    }
    
    public Exam readExam(String eid) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException, ParseException {
        return fileAccessFactory.getExamReaderWriter().searchExam(eid);
    }
    
    public ArrayList<Exam> readExamBymodule(String module) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException, ParseException {
        ArrayList<Exam> moduledExam = new ArrayList<>();
        ArrayList<Exam> readAllExams = fileAccessFactory.getExamReaderWriter().readAllExams();
        for (Exam readExam : readAllExams) {
            for (int i = 0; i < readExam.getModules().length; i++) {
                if (readExam.getModules()[i].equals(module)) {
                    moduledExam.add(readExam);
                }
            }
        }
        return moduledExam;
    }
    
    public ArrayList<String> getAllExamModules()throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException, ParseException {
        ArrayList<String> examModules = new ArrayList<>();
        ArrayList<Exam> readAllExams = fileAccessFactory.getExamReaderWriter().readAllExams();
        
        for (Exam readExam : readAllExams) {
            for (int i = 0; i < readExam.getModules().length; i++) {
                if (!examModules.contains(readExam.getModules()[i])) {
                    examModules.add(readExam.getModules()[i]);
                }
            }
        }
        return examModules;
    }
    
}
