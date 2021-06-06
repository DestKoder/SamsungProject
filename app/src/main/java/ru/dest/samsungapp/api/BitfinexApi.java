package ru.dest.samsungapp.api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;

import java.util.concurrent.ExecutionException;

import ru.dest.samsungapp.tasks.JsonQueryTask;
import ru.dest.samsungapp.sqlite.BinanceSQL;

public class BitfinexApi implements API{

    private final String API_HOST = "https://api-pub.bitfinex.com/v2/ticker/t";

    private HashMap<String,String> data;
    private Context context;

    public BitfinexApi(Context context) {
        this.data = new HashMap<String,String>();
        this.context = context;
        reloadData();
    }

    public void reloadData(){
        if(!data.isEmpty()) data.clear();

        Gson gson = new Gson();
        Type type = new TypeToken<Double[]>(){}.getType();

        try {
            String btcAnswer = this.sendRequest("BTCUSD");

            Double[] answer = gson.fromJson(btcAnswer,type);

            data.put("bitcoin", answer.length == 10 ? answer[6] + "" : "error");

            String ethAnswer = this.sendRequest("ETHUSD");

            answer = gson.fromJson(ethAnswer, type);

            data.put("ethereum", answer.length == 10 ? answer[6] + "" : "error");

            this.saveData(context, new Double[]{Double.parseDouble(data.get("bitcoin")),Double.parseDouble(data.get("ethereum"))});

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveData(Context context, Double[] data) {
        BinanceSQL worker = new BinanceSQL(context);
        SQLiteDatabase db = worker.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BinanceSQL.KEY_TIME, new Date().getTime());
        values.put(BinanceSQL.KEY_PRICE_BTC, data[0]);
        values.put(BinanceSQL.KEY_PRICE_ETH, data[1]);

        Cursor cursor = db.query(BinanceSQL.TABLE_NAME_BITFINEX, null,null,null,null,null,null);

        db.insert(BinanceSQL.TABLE_NAME_BITFINEX, null, values);

    }

    @Override
    public double getBitcoinPrice() {
        BinanceSQL worker = new BinanceSQL(context);
        SQLiteDatabase db = worker.getWritableDatabase();

        Cursor cursor = db.query(BinanceSQL.TABLE_NAME_BITFINEX, null,null,null,null,null,null);

        cursor.moveToLast();

        return cursor.getDouble(cursor.getColumnIndex(BinanceSQL.KEY_PRICE_BTC));
    }

    @Override
    public double getEthereumPrice() {
        BinanceSQL worker = new BinanceSQL(context);
        SQLiteDatabase db = worker.getWritableDatabase();

        Cursor cursor = db.query(BinanceSQL.TABLE_NAME_BITFINEX, null,null,null,null,null,null);

        cursor.moveToLast();

        return cursor.getDouble(cursor.getColumnIndex(BinanceSQL.KEY_PRICE_ETH));
    }

    @Override
    public double getLastBitcoinPrice() {
        this.reloadData();

        return this.getBitcoinPrice();
    }

    @Override
    public double getLastEthereumPrice() {
        this.reloadData();

        return this.getEthereumPrice();
    }

    private String sendRequest(String page) throws ExecutionException, InterruptedException {
        return new JsonQueryTask().execute(API_HOST, page).get();
    }
}
