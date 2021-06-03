package ru.dest.samsungapp.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import jdk.nashorn.internal.parser.JSONParser;

public class BinanceApi{
    private Api api;
    private HashMap<String, String> data;
    private Gson gson;


    public BinanceApi() throws IOException {
        api  = new Api("https://api.binance.com/api/v3/ticker/price");
        data = new HashMap<String, String>();
        gson = new Gson();

        String json = api.get("symbol", "BTCUSDT");

        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> map = gson.fromJson(json, type);
        data.put("BTC", map.get("price"));

        json = api.get("symbol", "ETHUSDT");
        map = gson.fromJson(json, type);

        data.put("ETH", map.get("price"));
    }

    public HashMap<String, String> getData(){
        return data;
    }
}
