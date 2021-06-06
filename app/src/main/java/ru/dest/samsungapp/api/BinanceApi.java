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
import ru.dest.samsungapp.utils.SQLiteHelper;


public class BinanceApi implements API{

    private final String API_HOST = "https://api.binance.com/api/v3/ticker/price";

    private Context context;

    public BinanceApi(Context context) {
        this.context = context;
        reloadData();
    }

    public void reloadData(){
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String,String>>(){}.getType();

        try {
            Double[] data = new Double[2];

            String btcAnswer = this.sendRequest("symbol", "BTCUSDT");
            Map<String,String> map = gson.fromJson(btcAnswer, type);

            data[0] = Double.parseDouble(map.get("price"));

            String ethAnswer = this.sendRequest("symbol", "ETHUSDT");
            map = gson.fromJson(ethAnswer, type);

            data[1] = Double.parseDouble(map.get("price"));

            this.saveData(context, data);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveData(Context context, Double[] data) {
        SQLiteHelper worker = new SQLiteHelper(context);
        SQLiteDatabase db = worker.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SQLiteHelper.KEY_TIME, new Date().getTime());
        values.put(SQLiteHelper.KEY_PRICE_BTC, data[0]);
        values.put(SQLiteHelper.KEY_PRICE_ETH, data[1]);

        db.insert(SQLiteHelper.TABLE_NAME, null, values);

    }

    @Override
    public double getBitcoinPrice() {
        SQLiteHelper worker = new SQLiteHelper(context);
        SQLiteDatabase db = worker.getWritableDatabase();

        Cursor cursor = db.query(SQLiteHelper.TABLE_NAME, null,null,null,null,null,null);

        cursor.moveToLast();

        return cursor.getDouble(cursor.getColumnIndex(SQLiteHelper.KEY_PRICE_BTC));
    }

    @Override
    public double getEthereumPrice() {
        SQLiteHelper worker = new SQLiteHelper(context);
        SQLiteDatabase db = worker.getWritableDatabase();

        Cursor cursor = db.query(SQLiteHelper.TABLE_NAME, null,null,null,null,null,null);

        cursor.moveToLast();

        return cursor.getDouble(cursor.getColumnIndex(SQLiteHelper.KEY_PRICE_ETH));
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