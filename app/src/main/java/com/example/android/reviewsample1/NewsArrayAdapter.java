package com.example.android.reviewsample1;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Shereen on 11/14/2017.
 */

public class NewsArrayAdapter extends ArrayAdapter<NewsStory> {
    public NewsArrayAdapter(@NonNull Context context, int resource, @NonNull List<NewsStory> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }
        TextView titleTextView = convertView.findViewById(R.id.title_text_view);
        TextView descTextview = convertView.findViewById(R.id.description_text_view);
        TextView dateTextView = convertView.findViewById(R.id.date_text_view);
        ImageView imageView = convertView.findViewById(R.id.news_image_view);

        NewsStory item = getItem(position);
        titleTextView.setText(item.getTitle());
        descTextview.setText(item.getDescription());
        dateTextView.setText(item.getPublishedAt());
        new MyAsyncTask(imageView).execute(item.getImageUrl());

        return convertView;
    }

    class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public MyAsyncTask(ImageView im){
            this.imageView=im;
        }
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap bmp = null;

            URL url = null;
            try {
                url = new URL(urls[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }
}
