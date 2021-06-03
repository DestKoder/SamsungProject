package ru.dest.samsungapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import ru.dest.samsungapp.api.BinanceApi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView price = (TextView)findViewById(R.id.text_btc);
        try {
            new BinanceApi().getData().get("BTC");
        } catch (IOException e) {
            e.printStackTrace();
        }
        price.setText("text");
    }
}