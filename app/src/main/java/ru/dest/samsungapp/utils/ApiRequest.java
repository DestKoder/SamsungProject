package ru.dest.samsungapp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiRequest {

    private String url;

    public ApiRequest(String url) {
        this.url = url;
    }

    /*
    * Метод отправки Get запроса к url.
    */

    public String get(String key, String value) throws IOException {
        url += "?" + key + "=" + value;
        URL address = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) address.openConnection();

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

    /*
     * Метод обращения к странице сайта.
     */
    public String get(String callPage) throws IOException {
        url+=callPage;

        URL address = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) address.openConnection();

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
