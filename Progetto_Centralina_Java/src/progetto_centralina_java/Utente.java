/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progetto_centralina_java;

import java.util.Date;

/**
 *
 * @author Lorenzo
 */
public class Utente {
    //informazioni utente
    private String username;
    private String password;

    //pulsanti digitali
    boolean[] pulsanti;

    //gestione scelta controllo luci esterne
    //true = sensore | false = ore
    boolean tipoGestioneLuci;
    //ora accensione e spegnimento luci esterne
    Date orarioAccensione;
    Date orarioSpegnimento;

    //temperatura desiderata termostato
    int temperaturaDesiderata;

    //accensione e spegnimento sistema sicurezza
    boolean sistemaSicurezza;

    //distanze target sensori 
    float distanzaPredefinita1;
    float distanzaPredefinita2;

    //distanze sensori
    float distanza1;
    float distanza2;

    //temperatura corrente
    float temperaturaCorrente;

    //valore sensore luce
    float valoreLuce;


    public Utente(String username, String password) {
        this.username = username;
        this.password = password;

        pulsanti=new boolean[3];
        tipoGestioneLuci=false;
        orarioAccensione = new Date();
        orarioSpegnimento = new Date();
        temperaturaDesiderata=25;
        sistemaSicurezza=false;
        distanza1=100.0f;
        distanza2=100.0f;
        temperaturaCorrente=30.0f;
    }
    public String getUsername()
    {
        return this.username;
    }
}

