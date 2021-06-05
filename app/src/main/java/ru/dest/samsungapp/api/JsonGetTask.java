package ru.dest.samsungapp.api;

import android.os.AsyncTask;

import java.io.IOException;

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
