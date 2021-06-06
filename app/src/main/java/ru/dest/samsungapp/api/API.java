package ru.dest.samsungapp.api;

import android.content.Context;


public interface API {
    /*
    * Метод для обновления данных/Получение актуальных данных
    */
    void reloadData();

    /*
    * Метод для сохранения данных в DataBase.
    */
    void saveData(Context context, Double[] data);


    /*
    * Методы для получения последней цены Биткоина и Эфира из Базы Данных
    */
    double getBitcoinPrice();
    double getEthereumPrice();

    /*
    * Аналог методов выше, только сначала данные обновляются.
    */
    double getLastBitcoinPrice();
    double getLastEthereumPrice();
}
