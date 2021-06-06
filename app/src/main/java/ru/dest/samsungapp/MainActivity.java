package ru.dest.samsungapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;

import ru.dest.samsungapp.activity.BinanceActivity;


public class MainActivity extends AppCompatActivity {

    private Handler handler=new Handler();
    private  boolean isLauncher=false;
    private Timer timer=new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startLauncherActivity();
            }
        },2500);

        System.out.println(this.getDatabasePath("cryptoland"));
    }

    private void startLauncherActivity() {
        if(!isLauncher){
            isLauncher=true;
            Intent intent=new Intent(MainActivity.this, BinanceActivity.class);
            startActivity(intent);
        }
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(handler!=null){
            handler.removeCallbacks(null);
        }
        if(timer!=null){
            timer.cancel();
        }
    }
}
