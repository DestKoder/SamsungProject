package ru.dest.samsungapp.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;

public class ApiWorker {
    private HttpURLConnection connection;

    public ApiWorker(URL url) throws IOException {
        this.connection = (HttpURLConnection)url.openConnection();
    }

    public void setRequestType(String requestType) throws ProtocolException {
        this.connection.setRequestMethod(requestType);
    }

    public void addAllParams(HashMap<String, String> params){
        Set<String> keys = params.keySet();

        for(String s : keys){
            this.addParam(s, params.get(s));
        }
    }
    public void addParam(String key, String value){
        this.connection.addRequestProperty(key,value);
    }

    public String getResponse() throws IOException {
        return connection.getResponseMessage();
    }
    public Integer getResponseCode() throws IOException {
        return connection.getResponseCode();
    }
}
