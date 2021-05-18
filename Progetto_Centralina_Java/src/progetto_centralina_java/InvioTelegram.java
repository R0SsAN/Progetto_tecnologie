/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progetto_centralina_java;

import java.util.logging.Level;
import java.util.logging.Logger;
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

    public InvioTelegram() {
        utenti = Utenti.getInstance();
    }

    public String getBotUsername() {
        return "Invio Telegram";
    }

    @Override
    public String getBotToken() {
        // inserire qui il proprio token
        return "1846536624:AAGq7nZ-MVRoto4WGTfWxB01DfP56giKz5w";
    }

    public void inviaAllarme() {
        //commento per ricordare allarme
        SendMessage message = new SendMessage();
        message.setText("ALLARME INTRUSIONE!");
        try {
            sendMessage(message);
        } catch (TelegramApiException ex) {
            Logger.getLogger(InvioTelegram.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void onUpdateReceived(Update update) {
        //nulla da fare
    }

}
