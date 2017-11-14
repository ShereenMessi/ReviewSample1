package com.example.android.reviewsample1;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Shereen on 11/14/2017.
 */

public class NewsAsyncTaskLoader extends AsyncTaskLoader<String> {
    public NewsAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    public String loadInBackground() {
        URL url = null;
        StringBuilder json = new StringBuilder();
        HttpURLConnection httpURLConnection=null;
        InputStream inputStream=null;
        try {
            url = new URL("https://newsapi.org/v1/articles?source=bbc-sport&sortBy=top&apiKey=1618c9a9c64844bd9db9f04a8147f32d");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                json.append(line);
                line = bufferedReader.readLine();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(httpURLConnection!=null)
                httpURLConnection.disconnect();
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return json.toString();
    }
}
