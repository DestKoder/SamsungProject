package ru.dest.samsungapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import ru.dest.samsungapp.api.Api;
import ru.dest.samsungapp.api.BinanceApi;
import ru.dest.samsungapp.api.JsonGetTask;

public class MainActivity extends AppCompatActivity {


    private static HashMap<String,String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView price = (TextView)findViewById(R.id.text_btc);

//        String res = null;
//
//        try {
//            res = new JsonGetTask().execute("https://api.binance.com/api/v3/ticker/price", "symbol", "BTCUSDT").get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        Gson gson = new Gson();
//
//        Type type = new TypeToken<Map<String,String>>(){}.getType();
//
//        Map<String,String> answer = gson.fromJson(res,type);
//
//        price.setText(answer.get("price"));

        HashMap<String,String> binanceData  = new BinanceApi("https://api.binance.com/api/v3/ticker/price").getData();

        if(!binanceData.isEmpty()){
            String text = "BTC: " + binanceData.get("bitcoin") + " ETH: " + binanceData.get("etherium");

            price.setText(text);
        }

    }

}