/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.fileReaderWriter;

import com.ijse.sss.encryption.EncryptionDecryption;
import com.ijse.sss.model.Results;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NEOx
 */
public class ResultsReaderWriter {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public ResultsReaderWriter() {
    }

    public boolean writeResult(Results results) throws IOException {
        BufferedWriter bufferedWriter = null;
        try {
            readWriteLock.writeLock().lock();
            File resultsFile = new File("./src/com/ijse/sss/files/results/" + results.geteId() + "#.txt");

            String marks = Integer.toString(results.getMarks());

            String resultData = results.getStId() + "#";
            resultData += marks + "#";

            String encapsulatedData = EncryptionDecryption.encrypt(resultData);

            FileWriter fileWriter = new FileWriter(resultsFile, true);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(encapsulatedData);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            return resultExists(results.geteId(), results.getStId());
        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            readWriteLock.writeLock().unlock();
        }
    }

    private boolean resultExists(String eId, String stId) throws FileNotFoundException, IOException {
        Results results = searchStudentResults(eId, stId);
        return results != null;
    }

    public Results searchStudentResults(String eId, String stId) throws FileNotFoundException, IOException {
        BufferedReader bufferedReader = null;
        try {
            readWriteLock.readLock().lock();
            Results results = null;
            File directory = new File("./src/com/ijse/sss/files/results/");
            File[] listFiles = directory.listFiles();

            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].getName().equals(eId + "#.txt")) {
                    FileReader fileReader = new FileReader(listFiles[i]);
                    bufferedReader = new BufferedReader(fileReader);

                    String readLine = null;
                    while ((readLine = bufferedReader.readLine()) != null) {
                        String decryptedData = EncryptionDecryption.decrypt(readLine);
                        String[] resultData = decryptedData.split("#");
                        if (resultData[0].equals(stId)) {
                            int marks = Integer.parseInt(resultData[1]);
                            results = new Results(eId, stId, marks);
                        }
                    }
                }
            }
            return results;
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public ArrayList<Results> getAllExamResults() throws FileNotFoundException, IOException {
        BufferedReader bufferedReader = null;
        try {
            readWriteLock.readLock().lock();
            ArrayList<Results> allResults = new ArrayList<>();
            File directory = new File("./src/com/ijse/sss/files/results/");
            File[] listFiles = directory.listFiles();

            for (int i = 0; i < listFiles.length; i++) {
                FileReader fileReader = new FileReader(listFiles[i]);
                bufferedReader = new BufferedReader(fileReader);
                String readLine = null;
                while ((readLine = bufferedReader.readLine()) != null) {
                    String decryptedData = EncryptionDecryption.decrypt(readLine);
                    String[] resultData = decryptedData.split("#");
                    int marks = Integer.parseInt(resultData[1]);
                    String[] FileName = listFiles[i].getName().split("#");
                    System.out.println(listFiles[i].getName());
                    Results results = new Results(FileName[0], resultData[0], marks);
                    allResults.add(results);
                }
            }
            return allResults;
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public ArrayList<String> reportExams() {
        ArrayList<String> eidList = new ArrayList<>();
        try {
            readWriteLock.readLock().lock();
            File directory = new File("./src/com/ijse/sss/files/results/");
            File[] listFiles = directory.listFiles();

            for (int i = 0; i < listFiles.length; i++) {
                String[] splitNames = listFiles[i].getName().split("#");
                eidList.add(splitNames[0]);
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
        return eidList;
    }
}
