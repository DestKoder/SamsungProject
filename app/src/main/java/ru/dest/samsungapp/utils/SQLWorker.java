package ru.dest.samsungapp.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLWorker extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "cryptoland";
    public static final String TABLE_DATA_BINANCE = "bincance_data";
    public static final String TABLE_DATA_BITFINEX = "bitfinex_data";

    public static final String KEY_ID = "_id";
    public static final String TABLE_DATA_TIME = "time";
    public static final String TABLE_DATA_PRICE_BTC = "btc_price";
    public static final String TABLE_DATA_PRICE_ETH = "eth_price";

    public SQLWorker(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_DATA_BINANCE + "(" +
                KEY_ID + " integer primary key," +
                TABLE_DATA_TIME + " integer," +
                TABLE_DATA_PRICE_BTC + "real," +
                TABLE_DATA_PRICE_ETH + " real)");

        db.execSQL("create table "+ TABLE_DATA_BITFINEX + "(" +
                KEY_ID + " integer primary key," +
                TABLE_DATA_TIME + " integer," +
                TABLE_DATA_PRICE_BTC + "real," +
                TABLE_DATA_PRICE_ETH + " real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_DATA_BINANCE);
        db.execSQL("drop table if exists " + TABLE_DATA_BITFINEX);

        this.onCreate(db);
    }
}
