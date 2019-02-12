/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.observerble;

import java.rmi.RemoteException;
import java.util.ArrayList;
import com.ijse.sss.observer.StudentObserver;

/**
 *
 * @author NEOx
 */
public class StudentObserverble {

    private ArrayList<StudentObserver> stList = new ArrayList<>();

    public StudentObserverble(){

    }

    public void addStudentObserver(StudentObserver studentObserver) throws RemoteException {
        stList.add(studentObserver);
    }

    public void RemoveStudentObserver(StudentObserver studentObserver) throws RemoteException {
        stList.remove(studentObserver);
    }

    public void notifyObserversNotification(String msg) throws RemoteException {
        for (StudentObserver studentObserver : stList) {
            studentObserver.updateNotification(msg);
        }
    }
}
