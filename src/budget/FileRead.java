package budget;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FileRead {

    //<editor-fold defaultstate="collapsed" desc="Variabe and Constants Initializacion">
    final private String PATH = "C:\\Users\\EliteBook\\OneDrive\\Documentos\\Balance",FILE_BALANCE_NAME = "\\Balance.txt",FILE_HISTORY_NAME = "\\Balance_History.txt";
    File dir = new File(PATH),balance = new File(PATH + FILE_BALANCE_NAME),history = new File(PATH + FILE_HISTORY_NAME);
    BufferedWriter bufferedWriterBalance,bufferedWriterHistory;
    BufferedReader bufferedReader;
    Calendar calendar = new GregorianCalendar();
    private float finalBalance = 0;
    private PrintWriter printWriter;
    //</editor-fold>

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
                        bufferedWriterHistory.write("Fecha|Hora|Cantidad|Pago/Gasto|Saldo\n");
                    }
                } else {
                    dir.mkdir();
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ummmm... Wtf happened??? xD");
        } finally {
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
        try {
            bufferedReader = new BufferedReader(new FileReader(balance));
            String line = bufferedReader.readLine();
            if (line == null) {
                line = "0";
            }
            finalBalance = pay + Float.parseFloat(line);
            bufferedWriterBalance = new BufferedWriter(new FileWriter(balance));
            bufferedWriterBalance.write(Float.toString(finalBalance));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "This file doesn't exists");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to acces file");
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriterBalance != null) {
                    bufferedWriterBalance.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "I still not knowing why this is happening");
            }

        }
        registerHistory(finalBalance, payment, "pay");
        return finalBalance;
    }

    public float spent(String spented) {
        float pay = Float.parseFloat(spented);
        try {
            bufferedReader = new BufferedReader(new FileReader(balance));
            String line = bufferedReader.readLine();
            if (line == null) {
                line = "0";
            }
            finalBalance = Float.parseFloat(line) - pay;
            bufferedWriterBalance = new BufferedWriter(new FileWriter(balance));
            bufferedWriterBalance.write(Float.toString(finalBalance));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "This file doesn't exists");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to acces file");
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriterBalance != null) {
                    bufferedWriterBalance.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "I still not knowing why this is happening");
            }

        }
            registerHistory(finalBalance, spented, "spent");
        return finalBalance;
    }

    public void registerHistory(float finalBalance, String quantity, String payOrSpent) {
        try {
            printWriter = new PrintWriter(new FileWriter(history, true));
            printWriter.println(LocalDate.now() + "|" + LocalTime.now() + "|" + quantity + "|" + payOrSpent + "|" + finalBalance);
            printWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(FileRead.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public float getFinalBalance(){
        try {
            bufferedReader = new BufferedReader(new FileReader(balance));
            String line = bufferedReader.readLine();
            if (line == null) {
                line = "0";
            }
            finalBalance = Float.parseFloat(line);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "How you did this???");
        }
        return finalBalance;
    }
}
