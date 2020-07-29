package com.example.qltt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.qltt.model.Category;
import com.example.qltt.model.News;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    ArrayList<News> listNews;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        Button btnCategory = findViewById(R.id.btnCategory);
        Button btnNews = findViewById(R.id.btnNews);
        final ListView listView = findViewById(R.id.listView);
        final Spinner spinner = findViewById(R.id.spinner);


        ArrayList<Category> listCategory;
        final ArrayList<String> listSpinner = new ArrayList<>();
        listSpinner.add("MOI NHAT");
        listCategory = db.getCategory();

        for(int i=0;i<listCategory.size();i++){
            listSpinner.add(listCategory.get(i).getName());
        }

        ArrayAdapter<String> spinAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listSpinner);
        spinner.setAdapter(spinAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    listNews = db.sortByViews();
                    adapter = new MainAdapter(getBaseContext(), listNews);
                    listView.setAdapter(adapter);
                }
                else {
                    listNews = db.sortByCategory(listSpinner.get(i));
                    adapter = new MainAdapter(getBaseContext(), listNews);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), CategoryManager.class));
            }
        });

        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult((new Intent(getBaseContext(), NewsManager.class)), 1);
            }
        });
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==1){
            setResult(RESULT_OK, null);
            this.finish();
        }
    }
}
