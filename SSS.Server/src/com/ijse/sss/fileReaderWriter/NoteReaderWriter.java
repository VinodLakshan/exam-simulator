/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.fileReaderWriter;

import com.ijse.sss.encryption.EncryptionDecryption;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import com.ijse.sss.model.Note;
import java.io.BufferedWriter;

/**
 *
 * @author NEOx
 */
public class NoteReaderWriter {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static final File noteFile = new File("./src/com/ijse/sss/files/model details/NoteFile.txt");

    public static boolean writeNote(Note note) throws ClassNotFoundException, IOException, ParseException {
        BufferedWriter bufferedWriter = null;
        try {
            readWriteLock.writeLock().lock();
            if (!noteFile.exists()) {
                noteFile.createNewFile();
            }

            ArrayList<Note> allNotes = getAllNotes();
            int OldIdNo = 0;

            if (!allNotes.isEmpty()) {
                String[] oldId = allNotes.get(allNotes.size() - 1).getNid().split("n");
                OldIdNo = Integer.parseInt(oldId[1]);
            }
            int newIdNo = OldIdNo + 1;
            String newNid = "n" + Integer.toString(newIdNo);
            String noteData = newNid + "#";
            noteData += note.getTopic() + "#";
            noteData += "Description #";
            noteData += note.getModule() + "#";
            noteData += note.getUserName() + "#";
            noteData += note.getDate() + "#";

            String noteDesc = note.getDescription();

            String encryptedNoteData = EncryptionDecryption.encrypt(noteData);
            String encryptedNoteDesc = EncryptionDecryption.encrypt(noteDesc);

            File descFile = new File("./src/com/ijse/sss/files/note descriptions/" + newNid + ".txt");
            FileWriter fileWriter = new FileWriter(noteFile, true);
            FileWriter descWriter = new FileWriter(descFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            descWriter.write(encryptedNoteDesc);
            descWriter.flush();
            bufferedWriter.write(encryptedNoteData);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            
            return noteExist(newNid);
        } finally {

            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            readWriteLock.writeLock().unlock();
        }
    }

    public static Note searchNote(String nid) throws ClassNotFoundException, IOException, ParseException {
        BufferedReader bufferedReader = null;
        try {
            readWriteLock.readLock().lock();
            Note note = null;
            FileReader fileReader = new FileReader(noteFile);
            bufferedReader = new BufferedReader(fileReader);
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] noteData = decryptedData.split("#");
                if (noteData[0].equals(nid)) {
                    FileInputStream fileInputStream = new FileInputStream("./src/com/ijse/sss/files/note descriptions/" + nid + ".txt");
                    int descInt = fileInputStream.read();
                    String desc = "";
                    while (descInt != -1) {
                        desc = desc + (char) descInt;
                        descInt = fileInputStream.read();
                    }
                    String decryptedDesc = EncryptionDecryption.decrypt(desc);
                    note = new Note(noteData[0], noteData[1], decryptedDesc, noteData[3], noteData[4], noteData[5]);
                }
            }
            return note;

        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.readLock().unlock();
        }
    }

    private static boolean noteExist(String nid) throws ClassNotFoundException, IOException, ParseException {
        Note searchNote = searchNote(nid);
        return searchNote != null;
    }

    public static boolean updateNote(Note note) throws FileNotFoundException, IOException, ClassNotFoundException, ParseException {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            readWriteLock.writeLock().lock();
            FileReader fileReader = new FileReader(noteFile);
            bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> noteList = new ArrayList<>();
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] noteData = decryptedData.split("#");
                if (noteData[0].equals(note.getNid())) {
                    String newNoteData = note.getNid() + "#";
                    newNoteData += note.getTopic() + "#";
                    newNoteData += "Description #";
                    newNoteData += note.getModule() + "#";
                    newNoteData += note.getUserName() + "#";
                    newNoteData += note.getDate() + "#";
                    noteList.add(newNoteData);

                    File descFile = new File("./src/com/ijse/sss/files/note descriptions/" + note.getNid() + ".txt");
                    FileWriter fileWriter = new FileWriter(descFile);
                    String encryptedDesc = EncryptionDecryption.encrypt(note.getDescription());
                    fileWriter.write(encryptedDesc);
                    fileWriter.flush();
                } else {
                    noteList.add(decryptedData);
                }
            }
            FileWriter fileWriter = new FileWriter(noteFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (String string : noteList) {
                String encrypted = EncryptionDecryption.encrypt(string);
                bufferedWriter.write(encrypted);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();

            return noteExist(note.getNid());
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.writeLock().unlock();
        }
    }

    public static boolean deleteNote(String nid) throws FileNotFoundException, IOException, ClassNotFoundException, ParseException {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            readWriteLock.writeLock().lock();
            FileReader fileReader = new FileReader(noteFile);
            bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> noteList = new ArrayList<>();
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] noteData = decryptedData.split("#");
                if (!noteData[0].equals(nid)) {
                    noteList.add(decryptedData);
                }
            }

            FileWriter fileWriter = new FileWriter(noteFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (String string : noteList) {
                String encrypted = EncryptionDecryption.encrypt(string);
                bufferedWriter.write(encrypted);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();

            return noteExist(nid);

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

    public static ArrayList<String> getModule() throws ClassNotFoundException, IOException, ParseException {
        try {
            readWriteLock.readLock().lock();
            ArrayList<String> modules = allModules();
            int count = modules.size();
            for (int i = 0; i < count; i++) {
                for (int j = i + 1; j < count; j++) {
                    if (modules.get(i).equals(modules.get(j))) {
                        modules.remove(j--);
                        count--;
                    }
                }
            }
            return modules;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    private static ArrayList<String> allModules() throws ClassNotFoundException, IOException {
        BufferedReader bufferedReader = null;
        FileReader noteReader = new FileReader(noteFile);
        bufferedReader = new BufferedReader(noteReader);
        ArrayList<String> modules = new ArrayList<>();
        String readLine = null;
        while ((readLine = bufferedReader.readLine()) != null) {
            String decryptedData = EncryptionDecryption.decrypt(readLine);
            String[] noteData = decryptedData.split("#");
            modules.add(noteData[3]);
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        return modules;
    }

    public static HashMap<String, String> getTopics(String module) throws ClassNotFoundException, IOException, ParseException {
        BufferedReader bufferedReader = null;
        try {
            readWriteLock.readLock().lock();
            FileReader fileReader = new FileReader(noteFile);
            bufferedReader = new BufferedReader(fileReader);
            HashMap<String, String> topics = new HashMap<>();
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] noteData = decryptedData.split("#");
                if (noteData[3].equals(module)) {
                    topics.put(noteData[0], noteData[1]);
                }
            }
            return topics;

        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<String> getAllTopics() throws ClassNotFoundException, IOException, ParseException {
        BufferedReader bufferedReader = null;
        try {
            readWriteLock.readLock().lock();
            FileReader fileReader = new FileReader(noteFile);
            bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> topics = new ArrayList<>();
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] noteData = decryptedData.split("#");
                topics.add(noteData[1]);
            }
            return topics;

        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<Note> getAllNotes() throws ClassNotFoundException, IOException, ParseException {
        BufferedReader bufferedReader = null;
        try {
            readWriteLock.readLock().lock();
            FileReader fileReader = new FileReader(noteFile);
            bufferedReader = new BufferedReader(fileReader);
            ArrayList<Note> Notes = new ArrayList<>();
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] noteData = decryptedData.split("#");
                FileInputStream fileInputStream = new FileInputStream("./src/com/ijse/sss/files/note descriptions/" + noteData[0] + ".txt");
                int descInt = fileInputStream.read();
                String desc = "";
                while (descInt != -1) {
                    desc = desc + (char) descInt;
                    descInt = fileInputStream.read();
                }
                String decryptedDesc = EncryptionDecryption.decrypt(desc);
                Note note = new Note(noteData[0], noteData[1], decryptedDesc, noteData[3], noteData[4], noteData[5]);
                Notes.add(note);
            }
            return Notes;

        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.readLock().unlock();
        }
    }
}
