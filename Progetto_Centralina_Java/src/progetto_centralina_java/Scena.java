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

    public Scena() {
        pulsantiComandati=new ArrayList<Integer>();
    }
    public void aggiungiComandato(int comandato)
    {
        pulsantiComandati.add(comandato);
    }

    @Override
    public String toString() {
        String finale = "";
        for (int i = 0; i < pulsantiComandati.size(); i++) {
            finale += pulsantiComandati.get(i) + "|";
        }
        finale = finale.substring(0, finale.length() - 1);
        return finale;
    }
    
}
