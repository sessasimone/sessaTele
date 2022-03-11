/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package telegramAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.*;

/**
 *
 * @author sessa
 */
public class GestisciMetodi {

    private String urlBase = "https://api.telegram.org/bot5280783436:AAH0cU9fi7xf6B0JF7Q-b4tD7BsXI8fQJ_Q/";
    private JSONArray VetMessaggi;

    public void myGetUpdate() throws MalformedURLException, IOException {

        String urlParziale = urlBase + "getUpdates";
        //trascrivo la risposta su un file
        File DaLeggere = ScriviSuFile(urlParziale);
        //leggo il file e lo transformo in stringa
        String Response = LeggiDaFile(DaLeggere);
        //parso la stringa
        JSONObject obj = new JSONObject(Response);
        //VetMesssaggi contiene tutti i messaggi inviati al bot
        VetMessaggi = obj.getJSONArray("result");
    }

    public boolean mySendMessageAll(String msg) throws MalformedURLException, IOException {
        List<String> ListaID = myFindAllId();
        String urlParziale = urlBase + "sendMessage";
        boolean Sent = false;

        for (int i = 0; i < ListaID.size(); i++) {
            //costruisco i vari URL
            urlParziale += "?chat_id=" + ListaID.get(i) + "&text=" + URLEncoder.encode(msg, StandardCharsets.UTF_8);
            System.out.println(urlParziale);
            URL url = new URL(urlParziale);
            Scanner sc = new Scanner(url.openStream());
            sc.useDelimiter("\u001a");
            Sent = true;
        }
        return Sent;
    }

    //trovo tutti gli ID delle chat che hanno scritto al bot
    public List<String> myFindAllId() throws IOException {
        //sono sicuro che VetMessaggi si inizializzato
        myGetUpdate();
        //lista con tutti gli ID delle chat che hanno scritto al bot
        List<String> ListaID = new ArrayList<String>();

        for (int i = 0; i < VetMessaggi.length(); i++) {
            JSONObject appoggio = new JSONObject(VetMessaggi.get(i).toString());
            JSONObject messaggio = appoggio.getJSONObject("message");
            JSONObject chat = messaggio.getJSONObject("chat");
            String ID = Integer.toString(chat.getInt("id"));
            
            //controllo che l'id non sia già stato inserito, se manca lo aggiungo alla lista
            if (!ListaID.contains(ID.toString())) {
                ListaID.add(ID);
            }
        }
        return ListaID;
    }

    private File ScriviSuFile(String urlParziale) throws MalformedURLException, IOException {
        URL url = new URL(urlParziale);
        Scanner sc = new Scanner(url.openStream());
        sc.useDelimiter("\u001a");

        File file = new File("prova.txt");
        FileWriter fw = new FileWriter(file);

        fw.write(sc.next());
        fw.flush();
        fw.close();

        return file;
    }

    private String LeggiDaFile(File f) throws FileNotFoundException, IOException {
        //variabile di appoggio
        String jsString = "";
        BufferedReader br = new BufferedReader(new FileReader(f));

        String line = br.readLine();
        while (line != null) {
            jsString += line;
            line = br.readLine();
        }

        return jsString;
    }
}
