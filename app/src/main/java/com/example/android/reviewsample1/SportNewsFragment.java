package com.example.android.reviewsample1;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Shereen on 11/14/2017.
 */

public class SportNewsFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport_news, container, false);
        listView = view.findViewById(R.id.news_list_view);
        ArrayList<NewsStory> list = new ArrayList<>();
        list.add(new NewsStory("title", "descriptio", "url", "imageUrl", "publishedAt"));

        getActivity().getSupportLoaderManager().initLoader(1, null, this).forceLoad();
        return view;
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new NewsAsyncTaskLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        ArrayList<NewsStory> list = extractJSON(data);
        NewsArrayAdapter adapter = new NewsArrayAdapter(getContext(), 0, list);
        listView.setAdapter(adapter);

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    public ArrayList<NewsStory> extractJSON(String json) {
        ArrayList<NewsStory> list = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(json);
            JSONArray array = root.getJSONArray("articles");
            for (int i = 0; i < array.length(); i++) {
                JSONObject itm = array.getJSONObject(i);
                String title = itm.getString("title");
                String description = itm.getString("description");
                String url = itm.getString("url");
                String urlToImage = itm.getString("urlToImage");
                String publishedAt = itm.getString("publishedAt");


                NewsStory story = new NewsStory(title, description, url, urlToImage, publishedAt);
                list.add(story);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
