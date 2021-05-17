/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progetto_centralina_java;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Andrea Puglisi
 */
public class ThreadRichieste extends Thread{
    Utenti listaUtenti;

    public ThreadRichieste() {
        listaUtenti=Utenti.getInstance();
    }

    @Override
    public void run() 
    {
        while(true)
        {
            try {
                riceviUpdate();
            } catch (IOException ex) {
                Logger.getLogger(ThreadRichieste.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadRichieste.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public void riceviUpdate() throws MalformedURLException, IOException
    {
        URL url = new URL("http://194.87.139.81/api/updates.php?action=get_update");
        InputStream is = url.openStream();
        try {
            String risultato = IOUtils.toString(is, StandardCharsets.UTF_8);
            System.out.println(risultato);
            
            
            ObjectMapper mapper = new ObjectMapper();
            Messaggio messaggio = mapper.readValue(risultato, Messaggio.class);
            aggiornaUtente(messaggio);
            
            
        } finally {
          is.close();
        }
    }
    public void inviaRichiesta(String azione, String data) throws IOException
    {
        URL url = new URL("http://194.87.139.81/api/updates.php"+azione+Base64.getEncoder().encodeToString(data.getBytes()));
        System.out.println(url.toString());
        InputStream is = url.openStream();
        System.out.println("inviato");
        is.close();
    }
    public void aggiornaUtente(Messaggio messaggio)
    {
        for (int i = 0; i < messaggio.bottoni.size(); i++) {
            listaUtenti.corrente.pulsanti[i]=(boolean)messaggio.bottoni.get(i);
        }
    }
    
}
