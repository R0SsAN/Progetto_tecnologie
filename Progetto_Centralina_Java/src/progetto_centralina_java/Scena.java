/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progetto_centralina_java;
/**
 *
 * @author Luca
 */
public class Scena {
    String nome;
    boolean attiva;
    int[] pulsantiComandati;

    public Scena() {
        nome="";
        attiva=false;
        pulsantiComandati=null;
    }
    public Scena(int[] pulsantiComandati){
        //nome = "";
        attiva = false;
        this.pulsantiComandati = pulsantiComandati;
    }
    void creaScena(String nome, int[] pulsantiComandati)
    {
        this.nome=nome;
        this.pulsantiComandati=pulsantiComandati;
        attiva=true;
    }
    void eliminaScena()
    {
        nome="";
        attiva=false;
        pulsantiComandati=null;
    }

    @Override
    public String toString() {
        String finale = "";
        for (int i = 0; i < pulsantiComandati.length; i++) {
            finale += pulsantiComandati[i] + "|";
        }
        finale = finale.substring(0, finale.length() - 1);
        return finale;
    }
    
}
