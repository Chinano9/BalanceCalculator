/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        final String PATH = "C:\\Users\\EliteBook\\OneDrive\\Documentos\\Balance";
        final String FILE_BALANCE_NAME = "\\Balance.txt",
                FILE_HISTORY_NAME = "\\Balance_History.txt";
        File dir = new File(PATH),
                balance = new File(PATH + FILE_BALANCE_NAME),
                history = new File(PATH + FILE_HISTORY_NAME);
        BufferedWriter bufferedWriterBalance,
                bufferedWriterHistory;
        BufferedReader bufferedReader;

        boolean repeat = true;
        try {
            while (repeat) {
                if (dir.exists()) {
                    if (balance.exists()) {
                    } else {
                        bufferedWriterBalance = new BufferedWriter(new FileWriter(balance));
                        bufferedWriterBalance.write("");
                    }
                    if (history.exists()) {
                        repeat = false;
                    } else {
                        bufferedWriterHistory = new BufferedWriter(new FileWriter(history));
                        bufferedWriterHistory.write("Fecha|Hora|Cantidad|Pago/Gasto|Saldo");
                    }
                } else {
                    dir.mkdir();
                }
                
            }
        } catch (IOException e) {
            System.out.println("XD que pedo por que pasa esto unu");
        }
        int pay = 23; 
        float finalBalance = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader(PATH + FILE_BALANCE_NAME));
            String line = bufferedReader.readLine();
            if (line == null) {
                line = "0";
            }
            finalBalance = pay + Integer.parseInt(line);
        } catch (FileNotFoundException ex) {
            System.out.println("Jaja no existe el archivo we");
        }catch (IOException e){
            System.out.println("No se pudo acceder carnal que pedo jaja xd");
        }
    }
}
