/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.controller;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import com.ijse.sss.model.Note;

/**
 *
 * @author NEOx
 */
public interface NoteController extends Remote {

    public boolean writeNote(Note note) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public  boolean updateNote(Note note) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public boolean deleteNote(String nid) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public ArrayList<String> getModule() throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public HashMap<String, String> getTopics(String module) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public Note searchNote(String searchTopic) throws RemoteException, IOException, ClassNotFoundException, ParseException;

    public ArrayList<String> getAllTopics() throws RemoteException, IOException, ClassNotFoundException, ParseException;
    
    public boolean reserveNote(String nid) throws RemoteException;
    
    public boolean releaseNote(String nid) throws RemoteException;
}
