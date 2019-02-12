/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.observer;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author NEOx
 */
public interface StudentObserver extends Remote {

    public void updateNotification(String type) throws RemoteException;
}
