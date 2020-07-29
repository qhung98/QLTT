package com.example.qltt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qltt.model.Category;

public class EditCategory extends AppCompatActivity {
    DatabaseHelper db;
    EditText edName;
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        edName = findViewById(R.id.edName);
        btnEdit = findViewById(R.id.btnEditCategory);
        db = new DatabaseHelper(this);

        Intent intent = getIntent();
        final Category category = (Category)intent.getSerializableExtra("Category");

        edName.setText(category.getName());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category editedCategory = new Category(category.getId(), edName.getText().toString());

                db.editCategory(editedCategory);
                Toast.makeText(getBaseContext(), "SUA CHUYEN MUC THANH CONG", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK, null);
                finish();
            }
        });
    }
}
