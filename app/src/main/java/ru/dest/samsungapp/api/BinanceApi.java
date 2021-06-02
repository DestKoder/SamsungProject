package ru.dest.samsungapp.api;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import jdk.nashorn.internal.parser.JSONParser;

public class BinanceApi {
    private ApiRequester requester;
    private HashMap<String, String> data;

    public BinanceApi() throws IOException {
        requester = new ApiRequester(new URL("https://api.binance.com/api/v3/ticker/price"));

        data = new HashMap<String, String>();

        HashMap<String, String> map = new Gson().fromJson(getJsonData("symbol", "BTCUSDT"), HashMap.class);

        data.put("BTC", map.get("price"));

        map = new Gson().fromJson(getJsonData("symbol", "ETHUSDT"), HashMap.class);

        data.put("ETH", map.get("price"));
    }

    private String getJsonData(String key, String val) throws IOException {
        HashMap<String,String> map = new HashMap<String, String>();
        String s = requester.get(map);

        return s;
    }

    public HashMap<String, String> getData(){
        return data;
    }
}
