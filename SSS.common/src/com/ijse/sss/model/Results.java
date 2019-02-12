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
public class Results implements Serializable{
    private String eId;
    private String stId;
    private int marks;

    public Results() {
    }

    public Results(String eId, String stId, int marks) {
        this.eId = eId;
        this.stId = stId;
        this.marks = marks;
    }

    /**
     * @return the eId
     */
    public String geteId() {
        return eId;
    }

    /**
     * @param eId the eId to set
     */
    public void seteId(String eId) {
        this.eId = eId;
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
     * @return the marks
     */
    public int getMarks() {
        return marks;
    }

    /**
     * @param marks the marks to set
     */
    public void setMarks(int marks) {
        this.marks = marks;
    }

    
}
