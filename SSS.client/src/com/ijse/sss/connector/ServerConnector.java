/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.connector;

import com.ijse.sss.controller.ExamController;
import com.ijse.sss.controller.MyNoteController;
import com.ijse.sss.controller.NoteController;
import com.ijse.sss.controller.QuestionController;
import com.ijse.sss.controller.ResultController;
import com.ijse.sss.controller.SimulatorFactory;
import com.ijse.sss.controller.StudentController;
import com.ijse.sss.controller.TeacherController;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author NEOx
 */
public class ServerConnector {

    private static ServerConnector serverConnector;
    private ExamController examController;
    private NoteController noteController;
    private QuestionController questionController;
    private StudentController studentController;
    private TeacherController teacherController;
    private MyNoteController myNoteController;
    private ResultController resultController;
    private SimulatorFactory simulatorFactory;

    private ServerConnector() throws RemoteException, NotBoundException, MalformedURLException {
        simulatorFactory = (SimulatorFactory) Naming.lookup("rmi://localhost:5050/SimulatorServer");
    }

    public static ServerConnector getServerConnector() throws RemoteException, NotBoundException, MalformedURLException {
        if (serverConnector == null) {
            serverConnector = new ServerConnector();
        }
        return serverConnector;
    }

    public ExamController getExamController() throws RemoteException, IOException {
        if (examController == null) {
            examController = simulatorFactory.getExamController();
        }
        return examController;
    }

    public NoteController getNoteController() throws RemoteException, IOException{
        if (noteController==null) {
            noteController=simulatorFactory.getNoteController();
        }
        return noteController;
    }
    
    public QuestionController getQuestionController() throws RemoteException,IOException{
        if (questionController==null) {
            questionController=simulatorFactory.getQuestionController();
        }
        return questionController;
    }
    
    public StudentController getStudentController() throws RemoteException,IOException{
        if (studentController==null) {
            studentController=simulatorFactory.getStudentController();
        }
        return studentController;
    }
    
    public TeacherController getTeacherController() throws RemoteException,IOException{
        if (teacherController==null) {
            teacherController=simulatorFactory.getTeacherController();
        }
        return teacherController;
    }
    
    public MyNoteController getMyNoteController()throws RemoteException,IOException{
        if (myNoteController == null) {
            myNoteController = simulatorFactory.getMyNoteController();
        }
        return myNoteController;
    }
    
    public ResultController getResultController()throws RemoteException,IOException{
        if (resultController == null) {
            resultController = simulatorFactory.getResultController();
        }
        return resultController;
    }
}
