package ru.dest.samsungapp.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class BinanceApi{

    private String host;
    private HashMap<String,String> data;


    public BinanceApi(String host) {
        this.host = host;
        this.data = new HashMap<String,String>();

        reloadData();
    }

    public void reloadData(){
        if(!data.isEmpty()) data.clear();

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String,String>>(){}.getType();

        try {
            String btcAnswer = this.sendRequest("symbol", "BTCUSDT");
            Map<String,String> map = gson.fromJson(btcAnswer, type);

            data.put("bitcoin", (String)map.get("price"));

            String ethAnswer = this.sendRequest("symbol", "ETHUSDT");
            map = gson.fromJson(ethAnswer, type);

            data.put("etherium", (String)map.get("price"));

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private String sendRequest(String key, String val) throws ExecutionException, InterruptedException {
        return new JsonGetTask().execute(host, key,val).get();
    }


    public HashMap<String, String> getData(){
        return data;
    }
}
