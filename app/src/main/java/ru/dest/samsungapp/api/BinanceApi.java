package ru.dest.samsungapp.api;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class BinanceApi extends AsyncTask {
    private Api api;
    private HashMap<String, String> data;
    private Gson gson;

    @Override
    protected Object doInBackground(Object[] objects) {
        api  = new Api("https://api.binance.com/api/v3/ticker/price");
        data = new HashMap<String, String>();
        gson = new Gson();

        String json = null;
        try {
            json = api.get("symbol", "BTCUSDT");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> map = gson.fromJson(json, type);
        data.put("BTC", map.get("price"));

        try {
            json = api.get("symbol", "ETHUSDT");
        } catch (IOException e) {
            e.printStackTrace();
        }
        map = gson.fromJson(json, type);

        data.put("ETH", map.get("price"));
        return null;
    }

    public HashMap<String, String> getData(){
        return data;
    }
}
