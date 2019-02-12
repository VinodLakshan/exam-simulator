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
public class Student implements Serializable{
    private String stId;
    private String userName;
    private String stName;
    private String dob;
    private String gender;
    private String pass;

    public Student() {
    }

    public Student(String stId, String userName, String stName, String dob, String gender, String pass) {
        this.stId = stId;
        this.userName = userName;
        this.stName = stName;
        this.dob = dob;
        this.gender = gender;
        this.pass = pass;
    }

    

    /**
     * @return the stId
     */
    public String getStId() {
        return stId;
    }

    /**
     * @param stId the stId to set
     */
    public void setStId(String stId) {
        this.stId = stId;
    }

    /**
     * @return the stName
     */
    public String getStName() {
        return stName;
    }

    /**
     * @param stName the stName to set
     */
    public void setStName(String stName) {
        this.stName = stName;
    }

    /**
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
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
    
    
}
