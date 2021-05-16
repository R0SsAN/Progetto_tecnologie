/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progetto_centralina_java;

import com.fazecast.jSerialComm.SerialPort;
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
                    String valoreLetto = scanner.nextLine();
                    String[] vettore=valoreLetto.split("-");
                    utenti.corrente.valoreLuce=parseInt(vettore[0]);
                    utenti.corrente.distanza1=parseInt(vettore[1]);
                    utenti.corrente.distanza2=parseInt(vettore[2]);
                } catch (Exception e) {
                    System.out.println("Errore generato");
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadSeriale.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
