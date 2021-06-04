package ru.dest.samsungapp.api;

import android.os.AsyncTask;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Map;

import ru.dest.samsungapp.MainActivity;

public class JsonGetTask extends AsyncTask <String, String, String> {

    @Override
    protected String doInBackground(String... params) {
        String res = "error";

        Api api = new Api(params[0]);

        try {
            res = api.get(params[1], params[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }



}
