package com.example.qltt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.qltt.model.Category;
import com.example.qltt.model.News;

import java.io.Serializable;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;

    private final static String DB_NAME = "QLTT";
    private final static String NEWS_TABLE = "News";
    private final static String CATEGORY_TABLE = "Category";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query1 = "CREATE TABLE " + NEWS_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "header TEXT, image TEXT, content TEXT, link TEXT, time TEXT, views INTEGER, category TEXT);";

        String query2 = "CREATE TABLE " + CATEGORY_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);";

        sqLiteDatabase.execSQL(query1);
        sqLiteDatabase.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NEWS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +  CATEGORY_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addCategory(Category category){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", category.getName());

        db.insert(CATEGORY_TABLE, null, contentValues);
    }

    public ArrayList<Category> getCategory(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CATEGORY_TABLE, null);

        ArrayList<Category> list = new ArrayList<>();

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            Category category = new Category(cursor.getInt(0), cursor.getString(1));
            list.add(category);
        }

        cursor.close();
        return list;
    }

    public void editCategory(Category category){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", category.getName());

        db.update(CATEGORY_TABLE, contentValues, "id=?", new String[]{Integer.toString(category.getId())});
    }

    public void deleteCategory(Category category){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CATEGORY_TABLE, "id=?", new String[]{Integer.toString(category.getId())});
    }

    public void addNews(News news){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("header", news.getHeader());
        contentValues.put("image", news.getImage());
        contentValues.put("content", news.getContent());
        contentValues.put("link", news.getLink());
        contentValues.put("time", news.getTime());
        contentValues.put("views", Integer.toString(news.getViews()));
        contentValues.put("category", news.getCategory());

        db.insert(NEWS_TABLE, null, contentValues);
    }

    public ArrayList<News> getNews(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<News> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + NEWS_TABLE, null);
        for(cursor.moveToFirst(); !cursor.isAfterLast();cursor.moveToNext()){
            News news = new News(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getInt(6), cursor.getString(7));
            list.add(news);
        }

        cursor.close();
        return list;
    }

    public void editNews(News news){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("header", news.getHeader());
        contentValues.put("image", news.getImage());
        contentValues.put("content", news.getContent());
        contentValues.put("link", news.getLink());
        contentValues.put("time", news.getTime());
        contentValues.put("views", Integer.toString(news.getViews()));
        contentValues.put("category", news.getCategory());

        db.update(NEWS_TABLE, contentValues, "id=?", new String[]{Integer.toString(news.getId())});
    }

    public void deleteNews(News news){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(NEWS_TABLE, "id=?", new String[]{Integer.toString(news.getId())});
    }

    public ArrayList<News> sortByViews(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<News> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + NEWS_TABLE + " ORDER BY views DESC ",null);
        for(cursor.moveToFirst(); !cursor.isAfterLast();cursor.moveToNext()){
            News news = new News(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getInt(6), cursor.getString(7));
            list.add(news);
        }

        cursor.close();
        return list;
    }

    public ArrayList<News> sortByCategory(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<News> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + NEWS_TABLE + " WHERE category = '" + name +"'",null);
        for(cursor.moveToFirst(); !cursor.isAfterLast();cursor.moveToNext()){
            News news = new News(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getInt(6), cursor.getString(7));
            list.add(news);
        }

        cursor.close();
        return list;
    }
}
