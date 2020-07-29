package com.example.qltt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qltt.model.News;

import java.io.IOException;
import java.io.Serializable;

public class DetailNews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        TextView tvHeader, tvContent, tvLink, tvCategory, tvTimes, tvViews;
        ImageView ivNews;

        tvHeader = findViewById(R.id.tvHeader);
        tvContent = findViewById(R.id.tvContent);
        tvLink = findViewById(R.id.tvLink);
        tvCategory = findViewById(R.id.tvCategory);
        tvTimes = findViewById(R.id.tvTime);
        tvViews = findViewById(R.id.tvViews);
        ivNews = findViewById(R.id.imageNews);

        Intent intent = getIntent();
        News news = (News)intent.getSerializableExtra("News");

        tvHeader.setText(news.getHeader());
        tvCategory.setText(news.getCategory());
        tvContent.setText(news.getContent());
        tvLink.setText(news.getLink());
        tvTimes.setText(news.getTime());
        tvViews.setText(Integer.toString(news.getViews()));

        Uri uri = Uri.parse(news.getImage());

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            ivNews.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
