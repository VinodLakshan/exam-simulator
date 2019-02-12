/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.fileAccessFactoryImpl;

import com.ijse.sss.fileAccessFactory.FileAccessFactory;
import com.ijse.sss.fileReaderWriter.ExamReaderWriter;
import com.ijse.sss.fileReaderWriter.MyNoteReaderWriter;
import com.ijse.sss.fileReaderWriter.NoteReaderWriter;
import com.ijse.sss.fileReaderWriter.QuestionReaderWriter;
import com.ijse.sss.fileReaderWriter.ResultsReaderWriter;
import com.ijse.sss.fileReaderWriter.StudentReaderWriter;
import com.ijse.sss.fileReaderWriter.TeacherReaderWriter;

/**
 *
 * @author NEOx
 */
public class FileAccessFactoryImpl implements FileAccessFactory{

    @Override
    public StudentReaderWriter getStudentReaderWriter() {
        return new StudentReaderWriter();
    }

    @Override
    public TeacherReaderWriter getTeacherReaderWriter() {
        return new TeacherReaderWriter();
    }

    @Override
    public NoteReaderWriter getNoteReaderWriter() {
        return new NoteReaderWriter();
    }

    @Override
    public QuestionReaderWriter getQuestionReaderWriter() {
        return new QuestionReaderWriter();
    }

    @Override
    public ExamReaderWriter getExamReaderWriter() {
        return new ExamReaderWriter();
    }

    @Override
    public MyNoteReaderWriter getMyNoteReaderWriter() {
        return new MyNoteReaderWriter();
    }

    @Override
    public ResultsReaderWriter getResultsReaderWriter() {
        return new ResultsReaderWriter();
    }
    
}
