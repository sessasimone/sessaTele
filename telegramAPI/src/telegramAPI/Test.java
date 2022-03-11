/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegramAPI;

import org.json.*;

/**
 *
 * @author sessa
 */
public class Test {
    public void foo(){
        String jsString = "{nome:'mario',messaggi:['hello','world']}";
        JSONObject obj = new JSONObject(jsString);
        String name = obj.getString("nome");
        System.out.println(name);
        JSONArray arr = obj.getJSONArray("messaggi");
        for (int i = 0; i < arr.length(); i++) {
            String messaggio = arr.getString(i);
            System.out.println(messaggio);
        }
    }
}
