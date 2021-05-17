/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progetto_centralina_java;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Andrea
 */
public class Utenti {
    //lista di utenti
    private ArrayList<Utente> listaUtenti;
    private final String pathFile = "utenti.txt";
    //utente attivo in questo momento
    public Utente corrente;
    
    public Utenti() {
        listaUtenti=new ArrayList<Utente>();
        corrente = null;
    }
    //singleton
    static Utenti _instance=null;
    static synchronized public Utenti getInstance()
    {
        if(_instance==null)
            _instance=new Utenti();
        return _instance;
    }
   
   public boolean aggiungiUtente(String username, String password){
       boolean presente=false;
       for(Utente utente : listaUtenti)
       {
           if(utente.getUsername().equals(username)){
               presente=true;
           }
       }
       if(presente==false){
           Utente utenteNuovo=new Utente(username,password);
           listaUtenti.add(utenteNuovo);
       }
       return presente;
   }
   public Utente controllaPresenzaUtente(String username, String password)
   {
       for(Utente utente : listaUtenti)
       {
           if(utente.getUsername().equals(username))
           {
               if(utente.getPassword().equals(password))
                   return utente;
               else
               {
                   JOptionPane.showMessageDialog(null, "Password sbagliata!");
               }
           }
       }
       return null;
   }
    public void SalvaUtenti(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile))){
            for (Utente utente : listaUtenti) {
                bw.append(utente.toString());
                bw.newLine();
            }
            bw.flush();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Impossibile salvare!");
        }
    }
    public void caricaUtenti(){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(pathFile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utenti.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "File "+pathFile+" non trovato!");
        }
        String text = null;
        try {
            while ((text = br.readLine()) != null){
                listaUtenti.add(new Utente(text));
            }
        } catch (IOException ex) {
            Logger.getLogger(Utenti.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Errore nel caricamento del file!");
        }
        
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Utenti.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Errore nella chiusura del file!");
        }
    }
}
