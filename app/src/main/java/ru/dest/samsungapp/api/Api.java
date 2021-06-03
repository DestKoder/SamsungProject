package ru.dest.samsungapp.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Api {

    private String url;

    public Api(String url){
        this.url = url;
    }

    public String get(String key, String value) throws IOException {
        url += "?"+key + "=" + value;
        URL adress = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)adress.openConnection();

        InputStream is = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        return response.toString();
    }

}
