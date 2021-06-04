package ru.dest.samsungapp.api;

import android.os.AsyncTask;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import ru.dest.samsungapp.MainActivity;

public class BinanceApi{
    private Api api;
    private HashMap<String, String> data;
    private Gson gson;

    public BinanceApi(){
        api  = new Api("https://api.binance.com/api/v3/ticker/price");
        data = new HashMap<String, String>();
        gson = new Gson();

        loadData();
    }

//    @Override
//    protected Object doInBackground(Object[] objects) {
//        String json = null;
//        try {
//            json = api.get("symbol", "BTCUSDT");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Type type = new TypeToken<Map<String, String>>(){}.getType();
//        Map<String, String> map = gson.fromJson(json, type);
//        data.put("BTC", map.get("price"));
//
//        try {
//            json = api.get("symbol", "ETHUSDT");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        map = gson.fromJson(json, type);
//
//        data.put("ETH", map.get("price"));
//        return null;
//    }

    public void loadData(){
        data.clear();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Api api = new Api("https://api.binance.com/api/v3/ticker/price");
                Gson gson = new Gson();
                try {
                    String res = api.get("symbol", "BTCUSDT");

                    Type type = new TypeToken<Map<String, String>>(){}.getType();
                    Map<String, String> map = gson.fromJson(res, type);

                    String answer = (String)map.get("price");

                    data.put("BTC", answer.equals( "" )? "error" : answer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public HashMap<String, String> getData(){
        return data;
    }
}
