package ru.dest.samsungapp.api;

import android.content.Context;


public interface API {

    void reloadData();

    void saveData(Context context, Double[] data);

    double getBitcoinPrice();
    double getEthereumPrice();

    double getLastBitcoinPrice();
    double getLastEthereumPrice();
}
