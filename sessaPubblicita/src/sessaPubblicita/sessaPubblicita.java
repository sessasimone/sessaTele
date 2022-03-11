/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessaPubblicita;

import java.io.IOException;
import telegramAPI.GestisciMetodi;
import telegramAPI.Test;

/**
 *
 * @author sessa
 */
public class sessaPubblicita {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        GestisciMetodi gm = new GestisciMetodi();
      
        if(gm.mySendMessageAll("messaggio con encode")){
            System.out.println("messaggio inviato correttamente");
        } else {
            System.out.println("errore nel invio del messaggio");
        }
    }
    
}
