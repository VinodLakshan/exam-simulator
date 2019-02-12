/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.controller;

import com.ijse.sss.controller.NoteController;
import com.ijse.sss.controller.StudentController;
import com.ijse.sss.controller.QuestionController;
import com.ijse.sss.controller.ExamController;
import com.ijse.sss.controller.TeacherController;
import com.ijse.sss.controller.SimulatorFactory;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author NEOx
 */
public class SimulatorFactoryImpl extends UnicastRemoteObject implements SimulatorFactory {

    public SimulatorFactoryImpl() throws RemoteException {

    }

    @Override
    public ExamController getExamController() throws RemoteException, IOException {
        return new ExamControllerImpl();
        
    }

    @Override
    public NoteController getNoteController() throws RemoteException, IOException {
        return new NoteControllerImpl();
    }

    @Override
    public QuestionController getQuestionController() throws RemoteException, IOException {
        return new QuestionControllerImpl();
    }

    @Override
    public StudentController getStudentController() throws RemoteException, IOException {
        return new StudentControllerImpl();
    }

    @Override
    public TeacherController getTeacherController() throws RemoteException, IOException {
        return new TeacherControllerImpl();
    }

    @Override
    public MyNoteController getMyNoteController() throws RemoteException, IOException {
        return new MyNoteControllerImpl();
    }

    @Override
    public ResultController getResultController() throws RemoteException, IOException {
        return new ResultsControllerImpl();
    }

}
