/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.controller;

import com.ijse.sss.controller.NoteController;
import com.ijse.sss.fileReaderWriter.NoteReaderWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import com.ijse.sss.model.Note;
import com.ijse.sss.reservation.NoteReserver;
import com.ijse.sss.service.factory.ServiceFactory;
import com.ijse.sss.service.factoryImpl.ServiceFactoryImpl;

/**
 *
 * @author NEOx
 */
public class NoteControllerImpl extends UnicastRemoteObject implements NoteController{
    
    private ServiceFactory serviceFactory = new ServiceFactoryImpl();
    private static final NoteReserver noteReserver = new NoteReserver();
    
    public NoteControllerImpl() throws RemoteException,IOException{
        
    }
    
    @Override
    public  boolean writeNote(Note note) throws RemoteException, IOException, ClassNotFoundException, ParseException {
        return serviceFactory.getNoteService().addNote(note);
    }

    @Override
    public boolean updateNote(Note note) throws RemoteException, IOException, ClassNotFoundException, FileNotFoundException, ParseException {
        return serviceFactory.getNoteService().updateNote(note);
    }

    @Override
    public boolean deleteNote(String nid) throws RemoteException, IOException, ClassNotFoundException, FileNotFoundException, ParseException {
        return serviceFactory.getNoteService().deleteNote(nid);
    }

    @Override
    public ArrayList<String> getModule() throws RemoteException, IOException, ClassNotFoundException, ParseException, ParseException {
        return serviceFactory.getNoteService().getModules();
    }

    @Override
    public HashMap<String,String> getTopics(String module) throws RemoteException, IOException, ClassNotFoundException, ParseException {
        return serviceFactory.getNoteService().getTopics(module);
    }

    @Override
    public Note searchNote(String searchTopic) throws RemoteException, IOException, ClassNotFoundException, ParseException {
        return serviceFactory.getNoteService().searchNote(searchTopic);
    }

    @Override
    public ArrayList<String> getAllTopics() throws RemoteException, IOException, ClassNotFoundException, ParseException {
        return serviceFactory.getNoteService().getAllTopics();
    }

    @Override
    public boolean reserveNote(String nid) throws RemoteException {
        return noteReserver.reserveNote(nid, this);
    }

    @Override
    public boolean releaseNote(String nid) throws RemoteException {
        return noteReserver.releaseNote(nid, this);
    }

   
}
