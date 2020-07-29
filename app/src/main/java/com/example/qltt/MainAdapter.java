package com.example.qltt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.qltt.model.News;

import java.util.ArrayList;

public class MainAdapter extends ArrayAdapter {
    Context context;
    ArrayList<News> list;
    DatabaseHelper db;

    public MainAdapter(@NonNull Context context, ArrayList<News> list) {
        super(context, R.layout.row_main, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main, parent, false);

        TextView tvHeader, tvTime, tvViews, tvCategory;
        LinearLayout layout;

        db = new DatabaseHelper(context);

        tvHeader = view.findViewById(R.id.tvHeader);
        tvCategory = view.findViewById(R.id.tvCategory);
        tvTime = view.findViewById(R.id.tvTime);
        tvViews = view.findViewById(R.id.tvViews);
        layout = view.findViewById(R.id.layoutNews);

        tvHeader.setText(list.get(position).getHeader());
        tvCategory.setText(list.get(position).getCategory());
        tvTime.setText(list.get(position).getTime());
        tvViews.setText(Integer.toString(list.get(position).getViews()));

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                News news = list.get(position);
                int views = news.getViews();
                news.setViews(views + 1);
                notifyDataSetChanged();
                db.editNews(news);

                Intent intent = new Intent(context, DetailNews.class);
                intent.putExtra("News", news);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
