package ru.dest.samsungapp.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;

    public static final String DATABASE_NAME = "cryptoland";
    public static final String TABLE_NAME = "binance_data";
    public static final String TABLE_NAME_BITFINEX = "bitfinex_data";

    public static final String KEY_ID = "_id";
    public static final String KEY_TIME = "time";
    public static final String KEY_PRICE_BTC = "btc_price";
    public static final String KEY_PRICE_ETH = "eth_price";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create table "+ TABLE_NAME + "(" +
        //        KEY_ID + " integer primary key," +
        //        KEY_TIME + " integer," +
        //        KEY_PRICE_BTC + "real," +
        //        KEY_PRICE_ETH + " real)");
        db.execSQL("CREATE TABLE binance_data (_id integer primary key autoincrement, time integer, btc_price real, eth_price real);");
        db.execSQL("CREATE TABLE bitfinex_data (_id integer primary key autoincrement, time integer, btc_price real, eth_price real);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        db.execSQL("drop table if exists " + TABLE_NAME);

        this.onCreate(db);
    }
}
