/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progetto_centralina_java;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luca
 */
public class Messaggio {
    List bottoni=new ArrayList<>();
    List scene=new ArrayList<>();

    public Messaggio() {
    }

    public void setBottoni(List bottoni) {
        this.bottoni = bottoni;
    }

    public void setScene(List scene) {
        this.scene = scene;
    }

    
}
