package com.example.qltt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.qltt.model.News;

import java.util.ArrayList;

public class NewsManager extends AppCompatActivity {
    ArrayList<News> list;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_manager);

        Button btnAdd = findViewById(R.id.btnAdd);
        ListView listView = findViewById(R.id.listViewNews);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getBaseContext(),AddNews.class), 1);
            }
        });


        db = new DatabaseHelper(this);
        list = db.getNews();

        NewsAdapter adapter = new NewsAdapter(this, list);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==1){
            startActivity(new Intent(getBaseContext(), NewsManager.class));
            this.finish();
        }

        if(resultCode==RESULT_OK && requestCode==2){
            startActivity(new Intent(getBaseContext(), NewsManager.class));
            this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        startActivity(new Intent(getBaseContext(), MainActivity.class));
        this.finish();
    }
}
