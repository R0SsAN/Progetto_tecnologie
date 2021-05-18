/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progetto_centralina_java;

import java.util.ArrayList;

/**
 *
 * @author Luca
 */
public class Scena {
    ArrayList<Integer> pulsantiComandati;
    boolean attiva;

    public Scena() {
        pulsantiComandati=new ArrayList<Integer>();
        attiva=false;
    }
    public Scena(ArrayList<Integer> array){
        this.pulsantiComandati=array;
    }
    public void aggiungiComandato(int comandato)
    {
        pulsantiComandati.add(comandato);
    }

    @Override
    public String toString() {
        String finale = "";
        for (int i = 0; i < pulsantiComandati.size(); i++) {
            finale += pulsantiComandati.get(i) + "&";
        }
        if(finale.length()>0)
        {
            finale = finale.substring(0, finale.length() - 1);
        }
        return finale;
    }
    
}
