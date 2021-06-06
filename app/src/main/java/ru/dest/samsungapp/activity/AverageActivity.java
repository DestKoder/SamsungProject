package ru.dest.samsungapp.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.BreakIterator;
import java.util.HashMap;

import ru.dest.samsungapp.R;
import ru.dest.samsungapp.api.BinanceApi;
import ru.dest.samsungapp.api.BitfinexApi;

public class AverageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_average);

        Spinner spinner = findViewById(R.id.spinner_api_3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.api_3, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        mActivity = AverageActivity.this;
        ImageButton button = findViewById(R.id.image_button3);
        button.setOnClickListener(v -> restartActivity(mActivity));
    }
    public static void restartActivity(Activity activity) {
        activity.recreate();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View  view, int i, long l) {
        adapterView.getItemAtPosition(i);
        switch (i) {
            case 0:
                return;
            case 1:
                Intent intent = new Intent(this, BinanceActivity.class);
                startActivity(intent);
                return;
            case 2:
                Intent intent1 = new Intent(this, BitfinexActivity.class);
                startActivity(intent1);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}

