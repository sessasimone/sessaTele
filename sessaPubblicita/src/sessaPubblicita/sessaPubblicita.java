/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessaPubblicita;

import java.io.IOException;
import static java.lang.Thread.sleep;
import telegramAPI.GestisciMetodi;


/**
 *
 * @author sessa
 */
public class sessaPubblicita { //commenti

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO code application logic here
        GestisciMetodi gm = new GestisciMetodi();
      while(true){
         if(gm.mySendMessageAll("messaggio con encode")){
            System.out.println("messaggio inviato correttamente");
        } else {
            System.out.println("errore nel invio del messaggio");
        } 
         sleep(1000);
      }
        
    }
    
}
