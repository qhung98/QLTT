package com.example.qltt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qltt.model.Category;

import java.util.ArrayList;

public class CategoryManager extends AppCompatActivity {
    Button btnAdd;
    ListView listView;
    DatabaseHelper db;
    ArrayList<Category> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_manager);

        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.listViewCategory);
        TextView textView = findViewById(R.id.textView);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getBaseContext(), AddCategory.class), 1);
            }
        });

        db = new DatabaseHelper(this);
        list = db.getCategory();

//        textView.setText(list.get(0).getName());
        CategoryAdapter adapter = new CategoryAdapter(this, list);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==1){
            startActivity(new Intent(getBaseContext(), CategoryManager.class));
            this.finish();
        }

        if(resultCode==RESULT_OK && requestCode==2){
            startActivity(new Intent(getBaseContext(), CategoryManager.class));
            this.finish();
        }
    }
}
