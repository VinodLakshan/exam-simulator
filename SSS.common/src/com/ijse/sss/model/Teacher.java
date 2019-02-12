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
public class Teacher implements Serializable{
    private String tId;
    private String userName;
    private String tName;
    private String gender;
    private String pass;

    public Teacher() {
    }

    public Teacher(String tId, String userName, String tName, String gender, String pass) {
        this.tId = tId;
        this.userName = userName;
        this.tName = tName;
        this.gender = gender;
        this.pass = pass;
    }

    /**
     * @return the tId
     */
    public String gettId() {
        return tId;
    }

    /**
     * @param tId the tId to set
     */
    public void settId(String tId) {
        this.tId = tId;
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
     * @return the tName
     */
    public String gettName() {
        return tName;
    }

    /**
     * @param tName the tName to set
     */
    public void settName(String tName) {
        this.tName = tName;
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
    
    
}
