/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progetto_centralina_java;

import com.fazecast.jSerialComm.SerialPort;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 *
 * @author Andrea
 */
public class InvioTelegram extends TelegramLongPollingBot {
    
    Utenti utenti;
    private final String pathFile= "listaChat.txt";
    ArrayList<Long> listaChat;

    static InvioTelegram _instance=null;
    static synchronized public InvioTelegram getInstance()
    {
        return _instance;
    }
    
    public InvioTelegram() {
        _instance=this;
        utenti = Utenti.getInstance();
        listaChat=new ArrayList<Long>();
        caricaChat();
    }

    public String getBotUsername() {
        return "Echo Bot";
    }

    @Override
    public String getBotToken() {
        // inserire qui il proprio token
        return "1846536624:AAGq7nZ-MVRoto4WGTfWxB01DfP56giKz5w";
    }
    
    @Override
    public void onUpdateReceived(Update update) {
        listaChat.add(update.getMessage().getChatId());
        salvaChat();
    }
    public void inviaAllarme() {
        //commento per ricordare allarme
        for (int i = 0; i < listaChat.size(); i++) {
            SendMessage message = new SendMessage().setChatId(listaChat.get(i));
            message.setText("ALLARME INTRUSIONE!");
            try {
                sendMessage(message);
            } catch (TelegramApiException ex) {
                Logger.getLogger(InvioTelegram.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public void caricaChat()
    {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(pathFile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utenti.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "File "+pathFile+" non trovato!");
        }
        String text = null;
        try {
            while ((text = br.readLine()) != null){
                listaChat.add(Long.parseLong(text));
            }
        } catch (IOException ex) {
            Logger.getLogger(Utenti.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Errore nel caricamento del file!");
        }
        
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Utenti.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Errore nella chiusura del file!");
        }
    }
    public void salvaChat()
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile))){
            for (Long id : listaChat) {
                bw.append(id.toString());
                bw.newLine();
            }
            bw.flush();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Impossibile salvare!");
        }
    }

}
