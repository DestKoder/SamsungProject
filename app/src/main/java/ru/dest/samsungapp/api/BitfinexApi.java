package ru.dest.samsungapp.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

import java.util.concurrent.ExecutionException;

import ru.dest.samsungapp.tasks.JsonQueryTask;

public class BitfinexApi implements API{

    private final String API_HOST = "https://api-pub.bitfinex.com/v2/ticker/t";

    private HashMap<String,String> data;

    public BitfinexApi() {
        this.data = new HashMap<String,String>();

        reloadData();
    }

    public void reloadData(){
        if(!data.isEmpty()) data.clear();

        Gson gson = new Gson();
        Type type = new TypeToken<Float[]>(){}.getType();

        try {
            String btcAnswer = this.sendRequest("BTCUSD");

            Float[] answer = gson.fromJson(btcAnswer,type);

            data.put("bitcoin", answer.length == 10 ? answer[6] + "" : "error");

            String ethAnswer = this.sendRequest("ETHUSD");

            answer = gson.fromJson(ethAnswer, type);

            data.put("ethereum", answer.length == 10 ? answer[6] + "" : "error");

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private String sendRequest(String page) throws ExecutionException, InterruptedException {
        return new JsonQueryTask().execute(API_HOST, page).get();
    }


    public HashMap<String, String> getData(){
        return data;
    }
}
