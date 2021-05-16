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
}
