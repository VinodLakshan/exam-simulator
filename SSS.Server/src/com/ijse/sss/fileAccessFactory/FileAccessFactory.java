/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.fileAccessFactory;

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
public interface FileAccessFactory {
    public StudentReaderWriter getStudentReaderWriter();
    public TeacherReaderWriter getTeacherReaderWriter();
    public NoteReaderWriter getNoteReaderWriter();
    public QuestionReaderWriter getQuestionReaderWriter();
    public ExamReaderWriter getExamReaderWriter();
    public MyNoteReaderWriter getMyNoteReaderWriter();
    public ResultsReaderWriter getResultsReaderWriter();
}
