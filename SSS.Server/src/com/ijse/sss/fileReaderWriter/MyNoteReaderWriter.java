/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.fileReaderWriter;

import com.ijse.sss.encryption.EncryptionDecryption;
import com.ijse.sss.model.MyNote;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NEOx
 */
public class MyNoteReaderWriter {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static final File myNoteFile = new File("./src/com/ijse/sss/files/model details/MyNoteFile.txt");

    public static boolean writeMyNote(MyNote myNote) throws ClassNotFoundException, IOException, ParseException {
        BufferedWriter bufferedWriter = null;
        try {
            readWriteLock.writeLock().lock();
            if (!myNoteFile.exists()) {
                myNoteFile.createNewFile();
            }

            ArrayList<MyNote> allMyNotes = getAllMyNotes();
            int OldIdNo = 0;

            if (!allMyNotes.isEmpty()) {
                String[] oldId = allMyNotes.get(allMyNotes.size() - 1).getMid().split("m");
                OldIdNo = Integer.parseInt(oldId[1]);
            }
            int newIdNo = OldIdNo + 1;
            String newNid = "m" + Integer.toString(newIdNo);

            String myNoteData = newNid + "#";
            myNoteData += myNote.getTopic() + "#";
            myNoteData += "Description #";
            myNoteData += myNote.getUserName() + "#";
            myNoteData += myNote.getDate() + "#";
            myNoteData += myNote.getTeacherStudent() + "#";

            String myNoteDesc = myNote.getDesc();

            String encryptedMyNoteData = EncryptionDecryption.encrypt(myNoteData);
            String encryptedMyNoteDesc = EncryptionDecryption.encrypt(myNoteDesc);

            File descFile = new File("./src/com/ijse/sss/files/myNote descriptions/" + newNid + ".txt");
            FileWriter fileWriter = new FileWriter(myNoteFile, true);
            FileWriter descWriter = new FileWriter(descFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            descWriter.write(encryptedMyNoteDesc);
            descWriter.flush();
            bufferedWriter.write(encryptedMyNoteData);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            return myNoteExist(newNid);
        } finally {

            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            readWriteLock.writeLock().unlock();
        }
    }
    
    public static boolean updateMyNote(MyNote myNote) throws FileNotFoundException, IOException, ClassNotFoundException, ParseException {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            readWriteLock.writeLock().lock();
            FileReader fileReader = new FileReader(myNoteFile);
            bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> myNoteList = new ArrayList<>();
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] myNoteData = decryptedData.split("#");
                if (myNoteData[0].equals(myNote.getMid())) {
                    String newMyNoteData = myNote.getMid() + "#";
                    newMyNoteData += myNote.getTopic() + "#";
                    newMyNoteData += "Description #";
                    newMyNoteData += myNote.getUserName() + "#";
                    newMyNoteData += myNote.getDate() + "#";
                    newMyNoteData += myNote.getTeacherStudent() + "#";
                    myNoteList.add(newMyNoteData);

                    File descFile = new File("./src/com/ijse/sss/files/myNote descriptions/" + myNote.getMid() + ".txt");
                    FileWriter fileWriter = new FileWriter(descFile);
                    String encryptedDesc = EncryptionDecryption.encrypt(myNote.getDesc());
                    fileWriter.write(encryptedDesc);
                    fileWriter.flush();
                } else {
                    myNoteList.add(decryptedData);
                }
            }
            FileWriter fileWriter = new FileWriter(myNoteFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (String string : myNoteList) {
                String encrypted = EncryptionDecryption.encrypt(string);
                bufferedWriter.write(encrypted);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();

            return myNoteExist(myNote.getMid());
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.writeLock().unlock();
        }
    }
    
    public static boolean deleteMyNote(String mid) throws FileNotFoundException, IOException, ClassNotFoundException, ParseException {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            readWriteLock.writeLock().lock();
            FileReader fileReader = new FileReader(myNoteFile);
            bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> myNoteList = new ArrayList<>();
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] myNoteData = decryptedData.split("#");
                if (!myNoteData[0].equals(mid)) {
                    myNoteList.add(decryptedData);
                }
            }

            FileWriter fileWriter = new FileWriter(myNoteFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (String string : myNoteList) {
                String encrypted = EncryptionDecryption.encrypt(string);
                bufferedWriter.write(encrypted);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();

            return myNoteExist(mid);

        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            readWriteLock.writeLock().unlock();
        }
    }

    
    public static ArrayList<MyNote> getAllMyNotes() throws FileNotFoundException, FileNotFoundException, IOException {
        BufferedReader bufferedReader = null;
        try {
            readWriteLock.readLock().lock();
            FileReader fileReader = new FileReader(myNoteFile);
            bufferedReader = new BufferedReader(fileReader);
            String readLine = null;
            ArrayList<MyNote> myNotes = new ArrayList<>();
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] myNoteData = decryptedData.split("#");
                FileInputStream fileInputStream = new FileInputStream("./src/com/ijse/sss/files/myNote descriptions/" + myNoteData[0] + ".txt");
                String desc = "";
                int read = fileInputStream.read();

                while (read != -1) {
                    desc = desc + (char) read;
                    read = fileInputStream.read();
                }
                String decryptedDesc = EncryptionDecryption.decrypt(desc);
                MyNote myNote = new MyNote(myNoteData[0], myNoteData[1], decryptedDesc, myNoteData[3], myNoteData[4], myNoteData[5]);
                myNotes.add(myNote);
            }
            return myNotes;
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.readLock().unlock();
        }

    }

    private static boolean myNoteExist(String newNid) throws FileNotFoundException, IOException {
        MyNote myNote = searchMyNote(newNid);
        return myNote != null;
    }

    public static MyNote searchMyNote(String mid) throws FileNotFoundException, IOException {
        BufferedReader bufferedReader = null;
        try {
            readWriteLock.readLock().lock();
            FileReader fileReader = new FileReader(myNoteFile);
            bufferedReader = new BufferedReader(fileReader);
            String readline = null;
            MyNote myNote = null;
            while ((readline = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readline);
                String[] myNoteData = decryptedData.split("#");

                if (myNoteData[0].equals(mid)) {
                    FileInputStream fileInputStream = new FileInputStream("./src/com/ijse/sss/files/myNote descriptions/" + myNoteData[0] + ".txt");
                    String desc = "";
                    int read = fileInputStream.read();
                    while (read != -1) {
                        desc = desc + (char) read;
                        read = fileInputStream.read();
                    }
                    String decryptedDesc = EncryptionDecryption.decrypt(desc);
                    myNote = new MyNote(myNoteData[0], myNoteData[1], decryptedDesc, myNoteData[3], myNoteData[4], myNoteData[5]);
                }
            }
            return myNote;
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.readLock().unlock();
        }
    }
}
