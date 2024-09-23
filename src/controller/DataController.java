package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

public class DataController {
    public FileWriter fileWriter;
    public BufferedWriter bufferedWriter;
    public PrintWriter printWriter;
    public Scanner scanner;

    public void openFileToWrite(String fileName) {
        try {
            fileWriter = new FileWriter(fileName,  false);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeScoreToFile(int score, String fileName) {
        openFileToWrite(fileName);
        printWriter.println(score);
        closeFileAfterWrite();
    }

    public void closeFileAfterWrite() {
        try {
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openFileToRead(String fileName) {
        try {
            scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int readScoreFromFile(String fileName) {
        openFileToRead(fileName);
        int score = 0;
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            score = Integer.parseInt(data);
        }
        closeFileAfterRead();
        return score;
    }

    public void closeFileAfterRead() {
        scanner.close();
    }
}
