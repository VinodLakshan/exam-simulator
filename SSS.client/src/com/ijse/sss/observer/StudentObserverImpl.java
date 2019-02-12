/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.observer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import com.ijse.sss.view.CreateNewAccount;
import com.ijse.sss.view.MainForm;

/**
 *
 * @author NEOx
 */
public class StudentObserverImpl extends UnicastRemoteObject implements StudentObserver {

    private MainForm mainForm;

    public StudentObserverImpl(MainForm mainForm) throws RemoteException {
        this.mainForm = mainForm;
    }

    @Override
    public void updateNotification(String msg) throws RemoteException {
        mainForm.setMessage(msg);
    }
}
