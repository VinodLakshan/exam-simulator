/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author NEOx
 */
public class Exam implements Serializable{
    private String eid;
    private String examName;
    private String[] modules;
    private String[] questions;
    private String date;
    private String time;
    private String duration;
    private String examCode;
    

    public Exam() {
    }

    public Exam(String eid, String examName, String[] modules, String[] questions, String date, String time, String duration, String examCode) {
        this.eid = eid;
        this.examName = examName;
        this.modules = modules;
        this.questions = questions;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.examCode = examCode;
    }

    /**
     * @return the eid
     */
    public String getEid() {
        return eid;
    }

    /**
     * @param eid the eid to set
     */
    public void setEid(String eid) {
        this.eid = eid;
    }

    /**
     * @return the module
     */
    

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * @return the examName
     */
    public String getExamName() {
        return examName;
    }

    /**
     * @param examName the examName to set
     */
    public void setExamName(String examName) {
        this.examName = examName;
    }

    /**
     * @return the modules
     */
    public String[] getModules() {
        return modules;
    }

    /**
     * @param modules the modules to set
     */
    public void setModules(String[] modules) {
        this.modules = modules;
    }

    /**
     * @return the questions
     */
    public String[] getQuestions() {
        return questions;
    }

    /**
     * @param questions the questions to set
     */
    public void setQuestions(String[] questions) {
        this.questions = questions;
    }

    /**
     * @return the examCode
     */
    public String getExamCode() {
        return examCode;
    }

    /**
     * @param examCode the examCode to set
     */
    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    /**
     * @return the questions
     */
   


}


