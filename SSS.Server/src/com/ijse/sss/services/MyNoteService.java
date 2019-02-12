/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.services;

import com.ijse.sss.fileAccessFactory.FileAccessFactory;
import com.ijse.sss.fileAccessFactoryImpl.FileAccessFactoryImpl;
import com.ijse.sss.model.MyNote;
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
public class MyNoteService {

    FileAccessFactory fileAccessFactory = new FileAccessFactoryImpl();

    public boolean writeMyNote(MyNote myNote) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getMyNoteReaderWriter().writeMyNote(myNote);
    }

    public boolean updateMyNote(MyNote myNote) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getMyNoteReaderWriter().updateMyNote(myNote);
    }

    public boolean deleteMyNote(String mid) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getMyNoteReaderWriter().deleteMyNote(mid);
    }

    public MyNote searchMyNote(String mid) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        return fileAccessFactory.getMyNoteReaderWriter().searchMyNote(mid);
    }

    public HashMap<String, String> getMyNotesTopics(String userName, String teacherStudent) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        ArrayList<MyNote> allMyNotes = fileAccessFactory.getMyNoteReaderWriter().getAllMyNotes();
        HashMap<String, String> allMyNotesTopics = new HashMap<>();
        for (MyNote myNote : allMyNotes) {
            if (myNote.getUserName().equals(userName) && myNote.getTeacherStudent().equals(teacherStudent)) {
                allMyNotesTopics.put(myNote.getMid(), myNote.getTopic());
            }
        }
        return allMyNotesTopics;
    }
}
