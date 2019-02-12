/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.controller;

import com.ijse.sss.model.MyNote;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author NEOx
 */
public interface MyNoteController extends Remote{
    public boolean writeMyNote(MyNote myNote) throws ClassNotFoundException, IOException, RemoteException, ParseException;
    public boolean updateMyNote(MyNote myNote) throws ClassNotFoundException, IOException, RemoteException, ParseException;
    public boolean deleteMyNote(String mid) throws ClassNotFoundException, IOException, RemoteException, ParseException;
    public MyNote searchMyNote(String mid) throws ClassNotFoundException, IOException, RemoteException, ParseException;
    public HashMap<String, String> getMyNotesTopics(String userName,String teacherStudent) throws RemoteException, IOException, ClassNotFoundException, ParseException;
}
