/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progetto_centralina_java;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrea
 */
public class Utenti {
    //lista di utenti
    private ArrayList<Utente> listaUtenti;
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
    
}
