package ru.dest.samsungapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.HashMap;
import ru.dest.samsungapp.api.BinanceApi;

public class MainActivity extends AppCompatActivity {

    private static HashMap<String,String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView price_btc = (TextView)findViewById(R.id.bitcoin);
        TextView price_eth = (TextView)findViewById(R.id.ethereum);

        HashMap<String,String> binanceData  = new BinanceApi("https://api.binance.com/api/v3/ticker/price").getData();

        if(!binanceData.isEmpty()){
            String text_btc = "BTC: " + binanceData.get("bitcoin") + "$";
            price_btc.setText(text_btc);
            String text_eth = "ETH: " + binanceData.get("ethereum") + "$";
            price_eth.setText(text_eth);
        }

        Spinner spinner = findViewById(R.id.spinner_api);
        String[] api = {"Binance", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, api);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
}