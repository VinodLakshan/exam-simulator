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
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import com.ijse.sss.model.Question;
import java.io.BufferedWriter;
import java.text.ParseException;

/**
 *
 * @author NEOx
 */
public class QuestionReaderWriter {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static File questionFile = new File("./src/com/ijse/sss/files/model details/QuestionFile.txt");

    public static boolean writeQuestion(Question question) throws ClassNotFoundException, IOException, FileNotFoundException, ParseException {
        BufferedWriter bufferedWriter = null;
        try {
            readWriteLock.writeLock().lock();
            if (!questionFile.exists()) {
                questionFile.createNewFile();
            }

            ArrayList<Question> allQuestions = readAllQuestions();

            int OldIdNo = 0;

            if (!allQuestions.isEmpty()) {
                String[] oldId = allQuestions.get(allQuestions.size() - 1).getQid().split("q");
                OldIdNo = Integer.parseInt(oldId[1]);
            }
            int newIdNo = OldIdNo + 1;
            String newQid = "q" + Integer.toString(newIdNo);
            String questionData = newQid + "#";
            questionData += "Question #";
            questionData += "Correct Answer #";
            questionData += "Answer 1#";
            questionData += "Answer 2#";
            questionData += "Answer 3#";
            questionData += question.getModule() + "#";

            String questionAnsw = question.getQuestion() + "#";
            questionAnsw += question.getCorrectAnswer() + "#";
            questionAnsw += question.getAnswer1() + "#";
            questionAnsw += question.getAnswer2() + "#";
            questionAnsw += question.getAnswer3() + "#";

            String encryptedQuestionData = EncryptionDecryption.encrypt(questionData);
            String encryptedQuestionAnsw = EncryptionDecryption.encrypt(questionAnsw);

            File descFile = new File("./src/com/ijse/sss/files/question descriptions/" + newQid + ".txt");
            FileWriter fileWriter = new FileWriter(questionFile, true);
            FileWriter descWriter = new FileWriter(descFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            descWriter.write(encryptedQuestionAnsw);
            bufferedWriter.write(encryptedQuestionData);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            descWriter.flush();

            return questionExist(newQid);
        } finally {

            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            readWriteLock.writeLock().unlock();
        }
    }

    public static Question searchQuestion(String qid) throws ClassNotFoundException, IOException, ParseException {
        BufferedReader bufferedReader = null;
        try {
            readWriteLock.readLock().lock();
            Question question = null;
            FileReader fileReader = new FileReader(questionFile);
            bufferedReader = new BufferedReader(fileReader);
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] questionData = decryptedData.split("#");
                if (questionData[0].equals(qid)) {
                    FileInputStream fileInputStream = new FileInputStream("./src/com/ijse/sss/files/question descriptions/" + qid + ".txt");
                    int descInt = fileInputStream.read();
                    String desc = "";
                    while (descInt != -1) {
                        desc = desc + (char) descInt;
                        descInt = fileInputStream.read();
                    }
                    String decryptedDesc = EncryptionDecryption.decrypt(desc);
                    String[] questionAnw = decryptedDesc.split("#");
                    question = new Question(questionData[0], questionAnw[0], questionAnw[1], questionAnw[2], questionAnw[3], questionAnw[4], questionData[6]);
                }
            }
            return question;

        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.readLock().unlock();
        }
    }

    private static boolean questionExist(String qid) throws ClassNotFoundException, IOException, ParseException {
        Question searchQuestion = searchQuestion(qid);
        return searchQuestion != null;
    }

    public static boolean updateQuestion(Question question) throws FileNotFoundException, IOException, ClassNotFoundException, ParseException {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            readWriteLock.writeLock().lock();
            FileReader fileReader = new FileReader(questionFile);
            bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> questionList = new ArrayList<>();
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] questionData = decryptedData.split("#");
                if (questionData[0].equals(question.getQid())) {
                    String newQuestionData = question.getQid() + "#";
                    newQuestionData += "Question#";
                    newQuestionData += "Correct Answer#";
                    newQuestionData += "Answer 1#";
                    newQuestionData += "Answer 2#";
                    newQuestionData += "Answer 3#";
                    newQuestionData += question.getModule() + "#";
                    questionList.add(newQuestionData);

                    File descFile = new File("./src/com/ijse/sss/files/question descriptions/" + question.getQid() + ".txt");
                    FileWriter fileWriter = new FileWriter(descFile);
                    String questionAnswrData = question.getQuestion() + "#";
                    questionAnswrData += question.getCorrectAnswer() + "#";
                    questionAnswrData += question.getAnswer1() + "#";
                    questionAnswrData += question.getAnswer2() + "#";
                    questionAnswrData += question.getAnswer3() + "#";
                    String encrypredQuestinAnsw = EncryptionDecryption.encrypt(questionAnswrData);
                    fileWriter.write(encrypredQuestinAnsw);
                    fileWriter.flush();
                } else {
                    questionList.add(decryptedData);
                }
            }
            FileWriter fileWriter = new FileWriter(questionFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (String string : questionList) {
                String encrypted = EncryptionDecryption.encrypt(string);
                bufferedWriter.write(encrypted);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();

            return questionExist(question.getQid());
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.writeLock().unlock();
        }
    }

    public static boolean deleteQuestion(String qid) throws FileNotFoundException, IOException, ClassNotFoundException, ParseException {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            readWriteLock.writeLock().lock();
            FileReader fileReader = new FileReader(questionFile);
            bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> questionList = new ArrayList<>();
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
                String[] questionData = decryptedData.split("#");
                if (!questionData[0].equals(qid)) {
                    questionList.add(decryptedData);
                }
            }

            FileWriter fileWriter = new FileWriter(questionFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (String string : questionList) {
                String encrypted = EncryptionDecryption.encrypt(string);
                bufferedWriter.write(encrypted);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();

            return questionExist(qid);

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

    public static ArrayList<Question> readQuestionsModule(String module) throws ClassNotFoundException, IOException, FileNotFoundException, ParseException {
        try {
            readWriteLock.readLock().lock();
            ArrayList<Question> readAllQuestions = readAllQuestions();
            ArrayList<Question> readModuleQuestions = new ArrayList<>();
            for (Question readModuleQuestion : readAllQuestions) {
                if (module.equalsIgnoreCase("All Modules")) {
                    readModuleQuestions.add(readModuleQuestion);
                } else {
                    if (readModuleQuestion.getModule().equalsIgnoreCase(module)) {
                        readModuleQuestions.add(readModuleQuestion);
                    }
                }
            }

            return readModuleQuestions;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<Question> readAllQuestions() throws FileNotFoundException, IOException, ParseException {
        BufferedReader bufferedReader = null;
        try {
            readWriteLock.readLock().lock();
            FileReader fileReader = new FileReader(questionFile);
            bufferedReader = new BufferedReader(fileReader);
            ArrayList<Question> questions = new ArrayList<>();
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String decryptedData = EncryptionDecryption.decrypt(readLine);
//                System.out.println(readLine);
//                System.out.println(decryptedData);
                String[] questionData = decryptedData.split("#");

                FileInputStream fileInputStream = new FileInputStream("./src/com/ijse/sss/files/question descriptions/" + questionData[0] + ".txt");
                int descInt = fileInputStream.read();
                String desc = "";
                while (descInt != -1) {
                    desc = desc + (char) descInt;
                    descInt = fileInputStream.read();
                }
                String decryptedDesc = EncryptionDecryption.decrypt(desc);
                String[] questionAns = decryptedDesc.split("#");
                Question question = new Question(questionData[0], questionAns[0], questionAns[1], questionAns[2], questionAns[3], questionAns[4], questionData[6]);
                questions.add(question);
            }
            return questions;

        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            readWriteLock.readLock().unlock();
        }
    }
}
