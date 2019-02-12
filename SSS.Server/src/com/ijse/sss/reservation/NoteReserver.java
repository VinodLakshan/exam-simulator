/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.reservation;

import com.ijse.sss.controller.NoteController;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 *
 * @author NEOx
 */
public class NoteReserver {

    private HashMap<String, NoteController> reserveData = new HashMap<>();

    public boolean reserveNote(String nid, NoteController noteController) {
        if (reserveData.containsKey(nid)) {
            if (reserveData.get(nid) == noteController) {
                return true;
            }
        }else{
            reserveData.put(nid, noteController);
            return true;
        }
        return false;
    }

    public boolean releaseNote(String nid, NoteController noteController){
        if (reserveData.get(nid) == noteController) {
            reserveData.remove(nid);
            return true;
        }else{
            return false;
        }
    }
}
