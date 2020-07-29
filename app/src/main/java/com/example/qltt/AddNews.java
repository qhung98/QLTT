package com.example.qltt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qltt.model.Category;
import com.example.qltt.model.News;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class AddNews extends AppCompatActivity {
    Uri uri;
    String image;
    TextView tvImage;
    DatabaseHelper db;
    ArrayList<Category> list;
    ArrayList<String> listName = new ArrayList<>();
    String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        final EditText edHeader, edContent, edLink;
        Spinner spinnerCategory;
        Button btnAddNews, btnImagePicker;

        tvImage = findViewById(R.id.tvImage);
        edHeader = findViewById(R.id.edHeader);
        edContent = findViewById(R.id.edContent);
        edLink = findViewById(R.id.edLink);
        btnAddNews = findViewById(R.id.btnAddNews);
        btnImagePicker = findViewById(R.id.btnImagePicker);
        spinnerCategory = findViewById(R.id.spinnerCategory);

        db = new DatabaseHelper(this);
        list = db.getCategory();

        for(int i=0;i<list.size();i++)
            listName.add(list.get(i).getName());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listName);
        spinnerCategory.setAdapter(adapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category = listName.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnImagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });


        btnAddNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss-dd/MM/yyyy");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));
                String time = sdf.format(new Date());

                News news = new News(edHeader.getText().toString(), image, edContent.getText().toString(),
                        edLink.getText().toString(), time, 0, category);

                db.addNews(news);
                Toast.makeText(getBaseContext(), "THEM TIN TUC THANH CONG", Toast.LENGTH_SHORT).show();

                setResult(RESULT_OK, null);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==1 && data!=null){
            uri = data.getData();
            image = uri.toString();
            tvImage.setText(uri.getPath());
        }
    }
}
