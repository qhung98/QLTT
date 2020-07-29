package com.example.qltt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.qltt.model.News;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter {
    Context context;
    ArrayList<News> list;
    DatabaseHelper db;

    public NewsAdapter(@NonNull Context context, ArrayList<News> list) {
        super(context, R.layout.row_news, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_news, parent, false);

        TextView tvHeader, tvTime, tvViews, tvCategory;
        Button btnEdit, btnDelete;

        tvHeader = view.findViewById(R.id.tvHeader);
        tvCategory = view.findViewById(R.id.tvCategory);
        tvTime = view.findViewById(R.id.tvTime);
        tvViews = view.findViewById(R.id.tvViews);
        btnEdit = view.findViewById(R.id.btnEdit);
        btnDelete = view.findViewById(R.id.btnDelete);

        tvHeader.setText(list.get(position).getHeader());
        tvCategory.setText(list.get(position).getCategory());
        tvTime.setText(list.get(position).getTime());
        tvViews.setText(Integer.toString(list.get(position).getViews()));

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,EditNews.class);
                intent.putExtra("editNews", list.get(position));
                ((Activity)context).startActivityForResult(intent, 2);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db=new DatabaseHelper(context);
                db.deleteNews(list.get(position));
                Toast.makeText(context, "XOA TIN TUC THANH CONG", Toast.LENGTH_SHORT).show();
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }

}
