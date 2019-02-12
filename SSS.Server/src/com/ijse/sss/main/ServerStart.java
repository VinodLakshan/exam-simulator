/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.main;

import com.ijse.sss.controller.SimulatorFactoryImpl;
import com.ijse.sss.view.StartEndServer;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NEOx
 */
public class ServerStart {
    public static void main(String[] args) {
        new StartEndServer().setVisible(true);
    }
}
