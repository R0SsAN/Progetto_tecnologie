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

    //lista di scene
    Scena[] scene;
    
    //acceso allarme
    boolean allarmeAcceso;
    
    //gestione scelta controllo luci esterne
    //true = sensore | false = ore
    boolean tipoGestioneLuci;
    //ora accensione e spegnimento luci esterne
    int orarioAccensione;
    int orarioSpegnimento;

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

        allarmeAcceso = false;
        pulsanti=new boolean[3];
        scene = new Scena[3];
        tipoGestioneLuci=false;
        orarioAccensione = 0;
        orarioSpegnimento = 0;
        temperaturaDesiderata=25;
        sistemaSicurezza=false;
        distanzaPredefinita1 = 0;
        distanzaPredefinita2 = 0;
        distanza1=100.0f;
        distanza2=100.0f;
        temperaturaCorrente=30.0f;
    }

    public Utente(String stringa) {
        this.pulsanti = new boolean[3];
        this.scene = new Scena[3];
        
        String[] sx = stringa.split("-");
        this.username = sx[0];
        this.password = sx[1];
        String[] dx = sx[2].split("|");
        for (int i = 0; i < dx.length; i++) {
            pulsanti[i] = (dx[i] == "1");
        }
        this.allarmeAcceso = (sx[3] == "1");
        this.tipoGestioneLuci = (sx[4] == "1");
        this.orarioAccensione = Integer.parseInt(sx[5]);
        this.orarioSpegnimento = Integer.parseInt(sx[6]);
        this.temperaturaDesiderata = Integer.parseInt(sx[7]);
        this.sistemaSicurezza = (sx[8] == "1");
        this.distanzaPredefinita1 = Integer.parseInt(sx[9]);
        this.distanzaPredefinita2 = Integer.parseInt(sx[10]);
        
        dx = sx[11].split("|");
        int[] v = new int[dx.length];
        for (int i = 0; i < v.length; i++) {
            v[i] = Integer.parseInt(dx[i]);
        }
        scene[0] = new Scena(v);
        
        dx = sx[12].split("|");
        v = new int[dx.length];
        for (int i = 0; i < v.length; i++) {
            v[i] = Integer.parseInt(dx[i]);
        }
        scene[1] = new Scena(v);
        
        dx = sx[13].split("|");
        v = new int[dx.length];
        for (int i = 0; i < v.length; i++) {
            v[i] = Integer.parseInt(dx[i]);
        }
        scene[2] = new Scena(v);
    }
    
    public String getUsername()
    {
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }

    @Override
    public String toString() {
        return username + "-" + password + "-" + (pulsanti[0] ? 1 : 0) + "|" + (pulsanti[1] ? 1 : 0) + "|" + (pulsanti[2] ? 1 : 0) + "-" + (allarmeAcceso ? 1 : 0) + "-" + (tipoGestioneLuci ? 1 : 0) + "-" + orarioAccensione + 
                "-" + orarioSpegnimento + "-" + temperaturaDesiderata + "-" + (sistemaSicurezza ? 1 : 0) + "-" + distanzaPredefinita1 + "-" + distanzaPredefinita2 + "-" + scene[0].toString() + 
                "-" + scene[1].toString() + "-" + scene[2].toString() + ";"; 
    }
}

