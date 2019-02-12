/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.reservation;

import com.ijse.sss.controller.QuestionController;
import java.util.HashMap;

/**
 *
 * @author NEOx
 */
public class QuestionReserver {

    private HashMap<String,QuestionController> reserveData = new HashMap<>();
    
    public boolean reserveQuestion(String qid,QuestionController questionController){
        if (reserveData.containsKey(qid)) {
            if (reserveData.get(qid) == questionController) {
                return true;
            }
        }else{
            reserveData.put(qid, questionController);
            return true;
        }
        return false;
    }
    
    public boolean releaseQuestion(String qid,QuestionController questionController){
        if (reserveData.get(qid) == questionController) {
            reserveData.remove(qid);
            return  true;
        }else{
            return false;
        }
    }
}
