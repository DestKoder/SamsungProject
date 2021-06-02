package ru.dest.samsungapp.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class ApiRequester {

    private HttpURLConnection connection;

    public ApiRequester(URL url) throws IOException {
        this.connection = (HttpURLConnection)url.openConnection();
    }

    public String post(HashMap<String, String> data) throws IOException {
        connection.setRequestMethod("POST");
        this.addParams(data);

        int response = connection.getResponseCode();

        if(response == 200) {  // OK, well done
            return connection.getResponseMessage();
        }else throw new ApiConnectException();
    }

    public String get(HashMap<String, String> data) throws IOException{
        connection.setRequestMethod("GET");
        this.addParams(data);

        int response = connection.getResponseCode();

        if(response == 200) {  // OK, well done
            return connection.getResponseMessage();
        }else throw new ApiConnectException();
    }

    private void addParams(HashMap<String, String> data){
        for(String s : data.keySet()){
            this.connection.addRequestProperty(s, data.get(s));
        }
    }

}
