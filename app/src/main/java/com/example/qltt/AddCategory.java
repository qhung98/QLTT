package com.example.qltt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qltt.model.Category;

public class AddCategory extends AppCompatActivity {
    Button btnAddCategory;
    EditText edName;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        edName = findViewById(R.id.edName);
        btnAddCategory = findViewById(R.id.btnAddCategory);
        db = new DatabaseHelper(this);
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category category = new Category(edName.getText().toString());
                db.addCategory(category);
                Toast.makeText(getBaseContext(), "THEM CHUYEN MUC THANH CONG", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK, null);
                finish();
            }
        });
    }
}
