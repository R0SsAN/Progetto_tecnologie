/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progetto_centralina_java;

import static java.lang.Integer.parseInt;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author Lorenzo
 */
public class ThreadGestioneArduino extends Thread {
    boolean connessioneSeriale;
    Utenti listaUtenti;
    
    
    //genera le stringhe in base alle variabili dell'utente e alle informazioni ricevute dagli arduini
    //prende le varie stringhe dalle variabili e le invia da arduini

    public ThreadGestioneArduino() {
        connessioneSeriale = false;
        listaUtenti = Utenti.getInstance();
    }
    
    public void LogIn(){
        connessioneSeriale = true;
        this.start();
    }
    
    public void LogOut(){
        connessioneSeriale = false;
    }
    
    @Override
    public void run(){
        while (connessioneSeriale){
            generaStringaArduino();
            inviaStringaArduino(); 
        }
    }

    public String generaStringaArduino() {
        String finale = "";
        
        //pulsanti
        for (int i = 0; i < listaUtenti.corrente.pulsanti.length; i++) {
            finale += listaUtenti.corrente.pulsanti[i] ? 1 : 0 + "-";
        }
        
        //luci
        if (listaUtenti.corrente.tipoGestioneLuci){
            if (listaUtenti.corrente.valoreLuce < 500){
                finale+="1-";
            } else {
                finale+="0-";
            }
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("HH");
            int oraAccensione = listaUtenti.corrente.orarioAccensione;
            int oraSpegnimento = listaUtenti.corrente.orarioSpegnimento;
            int oraCorrente = parseInt(formatter.format(new Date(System.currentTimeMillis())));
            if (oraCorrente > oraAccensione || oraCorrente < oraSpegnimento){
                finale += "1-";
            } else if (oraCorrente < oraAccensione && oraCorrente > oraSpegnimento){
                finale += "0-";
            }
        }
        
        //termo
        if (listaUtenti.corrente.temperaturaCorrente < listaUtenti.corrente.temperaturaDesiderata){
            finale += "1-";
        } else {
            finale += "0-";
        }
        
        //buzzer
        if (listaUtenti.corrente.distanza1 < listaUtenti.corrente.distanzaPredefinita1 || listaUtenti.corrente.distanza2 < listaUtenti.corrente.distanzaPredefinita2){
            finale += "1";
            listaUtenti.corrente.allarmeAcceso = true;
        } else {
            finale += "0";
            listaUtenti.corrente.allarmeAcceso = false;
        }
        return finale+";";
    }

    public void inviaStringaArduino() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
