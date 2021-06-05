package ru.dest.samsungapp.tasks;

import android.os.AsyncTask;

import java.io.IOException;

import ru.dest.samsungapp.utils.ApiRequest;

public class JsonQueryTask extends AsyncTask<String,String,String> {

    @Override
    protected String doInBackground(String... params) {
        String res = "error";

        ApiRequest api = new ApiRequest(params[0]);

        try {
            res = api.get(params[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }
}
