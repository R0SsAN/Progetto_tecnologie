/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progetto_centralina_java;

import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luca
 */
public class ThreadSeriale extends Thread {
    Thread thread = new Thread();
    SerialPort portaSeriale;
    Utenti utenti;

    //costruttore con la porta seriale
    ThreadSeriale(SerialPort p) {
        portaSeriale = p;
        utenti=Utenti.getInstance();
        portaSeriale.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        portaSeriale.openPort();
    }
    
    public void setPortaSeriale(SerialPort p){
        portaSeriale=p;
    }
    
   

    static ThreadSeriale _instance=null;
    static synchronized public ThreadSeriale getInstance(SerialPort p)
    {
        if(_instance==null)
            _instance=new ThreadSeriale(p);
        return _instance;
    }
    static synchronized public ThreadSeriale getInstance()
    {
        return _instance;
    }
    
    public void run() {
        Scanner scanner = new Scanner(portaSeriale.getInputStream());
        while (true) {
            while (scanner.hasNextLine()) {
                try {
                    String valoreLetto = scanner.nextLine().substring(12);
                    System.out.println(valoreLetto);
                    String[] vettore=valoreLetto.split("-");
                    utenti.corrente.valoreLuce=parseInt(vettore[0]);
                    utenti.corrente.temperaturaCorrente=parseInt(vettore[1]);
                    utenti.corrente.distanza1=parseInt(vettore[2]);
                    utenti.corrente.distanza2=parseInt(vettore[3].substring(0, vettore[3].length()-1));
                    
                } catch (Exception e) {
                    System.out.println("Errore generato");
                }
            }
        }
    }
    
    public void inviaSeriale(String stringa)
    {
        OutputStream prova;
        prova=portaSeriale.getOutputStream();
        try {
            prova.write(stringa.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(ThreadSeriale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
