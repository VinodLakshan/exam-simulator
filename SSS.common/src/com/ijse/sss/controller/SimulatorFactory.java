/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.controller;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author NEOx
 */
public interface SimulatorFactory extends Remote {

    public ExamController getExamController() throws RemoteException, IOException;

    public NoteController getNoteController() throws RemoteException, IOException;

    public QuestionController getQuestionController() throws RemoteException, IOException;

    public StudentController getStudentController() throws RemoteException, IOException;
    
    public TeacherController getTeacherController() throws RemoteException,IOException;
    
    public MyNoteController getMyNoteController() throws RemoteException,IOException;
    
    public ResultController getResultController() throws RemoteException,IOException;
}
