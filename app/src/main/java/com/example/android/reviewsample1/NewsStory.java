package com.example.android.reviewsample1;

/**
 * Created by Shereen on 11/14/2017.
 */

public class NewsStory {
    private String title;
    private String description;
    private String url;
    private String imageUrl;
    private String publishedAt;

    public NewsStory(String title, String description, String url, String imageUrl, String publishedAt) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPublishedAt() {
        return publishedAt;
    }
}
