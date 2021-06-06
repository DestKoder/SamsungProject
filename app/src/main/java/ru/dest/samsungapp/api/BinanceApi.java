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
import java.util.Map;
import java.util.concurrent.ExecutionException;

import ru.dest.samsungapp.tasks.JsonGetTask;
import ru.dest.samsungapp.sqlite.BinanceSQL;


public class BinanceApi implements API{

    private final String API_HOST = "https://api.binance.com/api/v3/ticker/price";


    private HashMap<String,String> data;
    private Context context;

    public BinanceApi(Context context) {
        this.data = new HashMap<String,String>();
        this.context = context;
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

            data.put("ethereum", (String)map.get("price"));

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

        db.insert(BinanceSQL.TABLE_NAME, null, values);

    }

    @Override
    public double getBitcoinPrice() {
        BinanceSQL worker = new BinanceSQL(context);
        SQLiteDatabase db = worker.getWritableDatabase();

        Cursor cursor = db.query(BinanceSQL.TABLE_NAME, null,null,null,null,null,null);

        cursor.moveToLast();

        return cursor.getDouble(cursor.getColumnIndex(BinanceSQL.KEY_PRICE_BTC));
    }

    @Override
    public double getEthereumPrice() {
        BinanceSQL worker = new BinanceSQL(context);
        SQLiteDatabase db = worker.getWritableDatabase();

        Cursor cursor = db.query(BinanceSQL.TABLE_NAME, null,null,null,null,null,null);

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

    private String sendRequest(String key, String val) throws ExecutionException, InterruptedException {
        return new JsonGetTask().execute(API_HOST, key,val).get();
    }
}
