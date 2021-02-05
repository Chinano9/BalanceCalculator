package budget;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FileRead {

    final private String PATH = "C:\\Users\\EliteBook\\OneDrive\\Documentos\\Balance",
            FILE_BALANCE_NAME = "\\Balance.txt",
            FILE_HISTORY_NAME = "\\Balance_History.txt";
    File dir = new File(PATH),
            balance = new File(PATH + FILE_BALANCE_NAME),
            history = new File(PATH + FILE_HISTORY_NAME);
    BufferedWriter bufferedWriterBalance,
            bufferedWriterHistory;
    BufferedReader bufferedReader;

    public FileRead() {
        boolean repeat = true;
        try {
            while (repeat) {
                if (dir.exists()) {
                    if (balance.exists()) {
                    } else {
                        bufferedWriterBalance = new BufferedWriter(new FileWriter(balance));
                        bufferedWriterBalance.write("0");
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
            JOptionPane.showMessageDialog(null, "Ummmm... Wtf happened??? xD");
        }finally{
            try {
                if (bufferedWriterBalance != null) {
                    bufferedWriterBalance.close();
                }
                if (bufferedWriterHistory != null) {
                    bufferedWriterHistory.close();
                }
            } catch (Exception e) {
            }
        }
    }
    

    public float pay(String payment) {
        float pay = Float.parseFloat(payment);
        float finalBalance = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader(PATH + FILE_BALANCE_NAME));
            String line = bufferedReader.readLine();
            if (line == null) {
                line = "0";
            }
            finalBalance = pay + Integer.parseInt(line);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "This file doesn't exists");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to acces file");
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "I still not knowing why this is happening");
            }

        }
        return finalBalance;
    }

    public float spent(String spented) {
        return 0;
    }

}
