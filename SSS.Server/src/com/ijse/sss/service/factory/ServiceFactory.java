/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.service.factory;

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
public interface ServiceFactory {
    public StudentService getStudentService();
    public TeacherService getTeacherService();
    public NoteService getNoteService();
    public QuestionService getQuestionService();
    public ExamService getExamService();
    public MyNoteService getMyNoteService();
    public ResultsService getResultsService();
}
