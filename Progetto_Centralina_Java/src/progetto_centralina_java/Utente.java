/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progetto_centralina_java;

import java.util.ArrayList;
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
    int distanzaPredefinita1;
    int distanzaPredefinita2;

    //distanze sensori
    int distanza1;
    int distanza2;

    //temperatura corrente
    int temperaturaCorrente;

    //valore sensore luce
    float valoreLuce;

    public Utente(String username, String password) {
        this.username = username;
        this.password = password;

        allarmeAcceso = false;
        pulsanti=new boolean[6];
        scene = new Scena[3];
        for (int i = 0; i < scene.length; i++) {
            scene[i]=new Scena();
        }
        tipoGestioneLuci=true;
        orarioAccensione = 0;
        orarioSpegnimento = 0;
        temperaturaDesiderata=20;
        sistemaSicurezza=false;
        distanzaPredefinita1 = 0;
        distanzaPredefinita2 = 0;
        distanza1=500;
        distanza2=500;
        temperaturaCorrente=30;
    }

    public Utente(String stringa) {
        this.pulsanti = new boolean[6];
        this.scene = new Scena[3];
        
        String[] sx = stringa.split("-");
        this.username = sx[0];
        this.password = sx[1];
        String[] dx = sx[2].split("&");
        for (int i = 0; i < dx.length; i++) {
            pulsanti[i] = (Integer.parseInt(dx[i]) == 1);  
        }
        this.allarmeAcceso = (sx[3] == "1");
        this.tipoGestioneLuci = (sx[4] == "1");
        this.orarioAccensione = Integer.parseInt(sx[5]);
        this.orarioSpegnimento = Integer.parseInt(sx[6]);
        this.temperaturaDesiderata = Integer.parseInt(sx[7]);
        this.sistemaSicurezza = (sx[8] == "1");
        this.distanzaPredefinita1 = Integer.parseInt(sx[9]);
        this.distanzaPredefinita2 = Integer.parseInt(sx[10]);
        
        dx = sx[11].split("&");
        ArrayList<Integer> v=new ArrayList<Integer>();
        if(dx.length>1)
        {
            for (int i = 0; i < dx.length; i++) {
                v.add(Integer.parseInt(dx[i]));
            }
            scene[0] = new Scena(v);
        }
        else
            scene[0]=new Scena();
        
        dx = sx[12].split("&");
        v = new ArrayList<Integer>();
        if(dx.length>1)
        {
            for (int i = 0; i < dx.length; i++) {
                v.add(Integer.parseInt(dx[i]));
            }
            scene[1] = new Scena(v);
        }
        else
            scene[1]=new Scena();
        
        dx = sx[13].split("&");
        v = new ArrayList<Integer>();
        if(dx.length>1)
        {
            for (int i = 0; i < dx.length; i++) {
                v.add(Integer.parseInt(dx[i]));
            }
            scene[2] = new Scena(v);
        }
        else
            scene[2] = new Scena();
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
        return username + "-" + password + "-" + (pulsanti[0] ? 1 : 0) + "&" + (pulsanti[1] ? 1 : 0) + "&" + (pulsanti[2] ? 1 : 0) + "&" + (pulsanti[3] ? 1 : 0) + "&" + (pulsanti[4] ? 1 : 0) + "&" + (pulsanti[5] ? 1 : 0) + "-" + (allarmeAcceso ? 1 : 0) + "-" + (tipoGestioneLuci ? 1 : 0) + "-" + orarioAccensione + 
                "-" + orarioSpegnimento + "-" + temperaturaDesiderata + "-" + (sistemaSicurezza ? 1 : 0) + "-" + distanzaPredefinita1 + "-" + distanzaPredefinita2 + "-" + scene[0].toString() + 
                "-" + scene[1].toString() + "-" + scene[2].toString() + ";"; 
    }
}

