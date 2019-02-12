/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.controller;

import com.ijse.sss.model.MyNote;
import com.ijse.sss.service.factory.ServiceFactory;
import com.ijse.sss.service.factoryImpl.ServiceFactoryImpl;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.util.HashMap;

/**
 *
 * @author NEOx
 */
public class MyNoteControllerImpl extends UnicastRemoteObject implements MyNoteController{

    ServiceFactory serviceFactory = new ServiceFactoryImpl();
    
    MyNoteControllerImpl() throws RemoteException{
        
    }
    
    @Override
    public boolean writeMyNote(MyNote myNote) throws ClassNotFoundException, IOException, RemoteException, ParseException {
        return serviceFactory.getMyNoteService().writeMyNote(myNote);
    }

    @Override
    public boolean updateMyNote(MyNote myNote) throws ClassNotFoundException, IOException, RemoteException, ParseException {
        return  serviceFactory.getMyNoteService().updateMyNote(myNote);
    }

    @Override
    public boolean deleteMyNote(String mid) throws ClassNotFoundException, IOException, RemoteException, ParseException {
        return serviceFactory.getMyNoteService().deleteMyNote(mid);
    }

    @Override
    public MyNote searchMyNote(String mid) throws ClassNotFoundException, IOException, RemoteException, ParseException {
        return serviceFactory.getMyNoteService().searchMyNote(mid);
    }

    @Override
    public HashMap<String, String> getMyNotesTopics(String userName, String teacherStudent) throws RemoteException, IOException, ClassNotFoundException, ParseException {
        return serviceFactory.getMyNoteService().getMyNotesTopics(userName, teacherStudent);
    }
    
}
