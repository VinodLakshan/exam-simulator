/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.service.factoryImpl;

import com.ijse.sss.service.factory.ServiceFactory;
import com.ijse.sss.services.ExamService;
import com.ijse.sss.services.MyNoteService;
import com.ijse.sss.services.NoteService;
import com.ijse.sss.services.QuestionService;
import com.ijse.sss.services.ResultsService;
import com.ijse.sss.services.StudentService;
import com.ijse.sss.services.TeacherService;

/**
 *
 * @author NEOx
 */
public class ServiceFactoryImpl implements ServiceFactory{

    @Override
    public StudentService getStudentService() {
        return new StudentService();
    }

    @Override
    public TeacherService getTeacherService() {
        return new TeacherService();
    }

    @Override
    public NoteService getNoteService() {
        return new NoteService();
    }

    @Override
    public QuestionService getQuestionService() {
        return new QuestionService();
    }

    @Override
    public ExamService getExamService() {
        return new ExamService();
    }

    @Override
    public MyNoteService getMyNoteService() {
        return new MyNoteService();
    }

    @Override
    public ResultsService getResultsService() {
        return new ResultsService();
    }
    
}
