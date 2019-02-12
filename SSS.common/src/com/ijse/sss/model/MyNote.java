/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.model;

import java.io.Serializable;

/**
 *
 * @author NEOx
 */
public class MyNote implements Serializable{
    private String mid;
    private String topic;
    private String desc;
    private String userName;
    private String date;
    private String teacherStudent;

    public MyNote() {
    }

    public MyNote(String mid, String topic, String desc, String userName, String date, String teacherStudent) {
        this.mid = mid;
        this.topic = topic;
        this.desc = desc;
        this.userName = userName;
        this.date = date;
        this.teacherStudent = teacherStudent;
    }

    /**
     * @return the mid
     */
    public String getMid() {
        return mid;
    }

    /**
     * @param mid the mid to set
     */
    public void setMid(String mid) {
        this.mid = mid;
    }

    /**
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @param topic the topic to set
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

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
     * @return the teacherStudent
     */
    public String getTeacherStudent() {
        return teacherStudent;
    }

    /**
     * @param teacherStudent the teacherStudent to set
     */
    public void setTeacherStudent(String teacherStudent) {
        this.teacherStudent = teacherStudent;
    }
    
    
}
