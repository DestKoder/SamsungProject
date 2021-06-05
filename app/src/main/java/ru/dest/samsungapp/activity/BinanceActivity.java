package ru.dest.samsungapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

import ru.dest.samsungapp.R;
import ru.dest.samsungapp.api.BinanceApi;

public class BinanceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static HashMap<String,String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        TextView price_btc = (TextView)findViewById(R.id.bitcoin);
        TextView price_eth = (TextView)findViewById(R.id.ethereum);

        HashMap<String,String> data  = new BinanceApi().getData();

        if(!data.isEmpty()){
            String text_btc = "BTC: " + data.get("bitcoin") + "$";
            price_btc.setText(text_btc);
            String text_eth = "ETH: " + data.get("ethereum") + "$";
            price_eth.setText(text_eth);
        }

        Spinner spinner = findViewById(R.id.spinner_api);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.api, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        adapterView.getItemAtPosition(i);
        switch (i) {
            case 0:
                return;
            case 1:
            case 2:
                Intent intent = new Intent(this, BitfinexActivity.class);
                startActivity(intent);
                return;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}