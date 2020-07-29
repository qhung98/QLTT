package com.example.qltt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.qltt.model.Category;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<Category> {
    private final Context context;
    private final ArrayList<Category> list;

    public CategoryAdapter(@NonNull Context context, ArrayList<Category> list) {
        super(context, R.layout.row_category, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category, parent, false);

        TextView tvCategoryId, tvCategoryName;
        Button btnEdit, btnDelete;

        tvCategoryId = view.findViewById(R.id.tvCategoryId);
        tvCategoryName = view.findViewById(R.id.tvIdCategoryName);
        btnEdit = view.findViewById(R.id.btnEdit);
        btnDelete = view.findViewById(R.id.btnDelete);
        tvCategoryId.setText(Integer.toString(list.get(position).getId()));
        tvCategoryName.setText(list.get(position).getName());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditCategory.class);
                intent.putExtra("Category", list.get(position));
                ((Activity)context).startActivityForResult(intent, 2);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(context);
                db.deleteCategory(list.get(position));
                Toast.makeText(context, "XOA CHUYEN MUC THANH CONG", Toast.LENGTH_SHORT).show();
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }


}
