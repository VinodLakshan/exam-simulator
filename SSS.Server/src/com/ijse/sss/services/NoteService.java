/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.services;

import com.ijse.sss.fileAccessFactory.FileAccessFactory;
import com.ijse.sss.fileAccessFactoryImpl.FileAccessFactoryImpl;
import com.ijse.sss.model.Exam;
import com.ijse.sss.model.Note;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author NEOx
 */
public class NoteService {

    private FileAccessFactory fileAccessFactory = new FileAccessFactoryImpl();

    public boolean addNote(Note note) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getNoteReaderWriter().writeNote(note);
    }

    public boolean updateNote(Note note) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getNoteReaderWriter().updateNote(note);
    }

    public boolean deleteNote(String nid) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getNoteReaderWriter().deleteNote(nid);
    }
    
    public ArrayList<String> getModules() throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getNoteReaderWriter().getModule();
    }
    
    public HashMap<String,String> getTopics(String module) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getNoteReaderWriter().getTopics(module);
    }

    public Note searchNote(String searchId) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getNoteReaderWriter().searchNote(searchId);
    }
    
    public ArrayList<String> getAllTopics() throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getNoteReaderWriter().getAllTopics();
    }
}
