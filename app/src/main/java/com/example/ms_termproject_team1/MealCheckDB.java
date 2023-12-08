package com.example.ms_termproject_team1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class MealCheckDB extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "mealInfoDetail.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "meal_list";
    public static final String COLUMN_RESTAURANT = "restaurant_name";
    public static final String COLUMN_MENU = "menu_list";
    public static final String COLUMN_EATING_DATE = "eating_date";
    public static final String COLUMN_EATING_START = "eating_start";
    public static final String COLUMN_EATING_TIME = "eating_time";
    public static final String COLUMN_KCAL = "eating_kcal";
    public static final String COLUMN_TOTAL_KCAL = "eating_totalkcal";
    public static final String COLUMN_PRICE = "menu_price";
    public static final String COLUMN_POINT = "menu_point";
    public static final String COLUMN_MENU_PHOTO = "menu_image";

    // db 생성
    public MealCheckDB(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE "+TABLE_NAME+
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_RESTAURANT + " TEXT, " +
                COLUMN_MENU + " TEXT, " +
                COLUMN_EATING_DATE + " TEXT, " +
                COLUMN_EATING_START + " TEXT, " +
                COLUMN_EATING_TIME + " TEXT, " +
                COLUMN_KCAL + " TEXT, " +
                COLUMN_TOTAL_KCAL + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_POINT + " TEXT, " +
                COLUMN_MENU_PHOTO + " BLOP);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor readSelectData(String date){
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE "+ COLUMN_EATING_DATE + "=?";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, new String[]{date});
        }

        return cursor;
    }

    public void addMeal(String restaurant, String meal, String date, String start, String time, String kcal,
                        String totalKcal, String price, String point, byte[] meal_image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_RESTAURANT, restaurant);
        cv.put(COLUMN_MENU, meal);
        cv.put(COLUMN_EATING_DATE, date);
        cv.put(COLUMN_EATING_START, start);
        cv.put(COLUMN_EATING_TIME, time);
        cv.put(COLUMN_KCAL, kcal);
        cv.put(COLUMN_TOTAL_KCAL, totalKcal);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_POINT, point);
        cv.put(COLUMN_MENU_PHOTO, meal_image);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "업로드 실패", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "업로드 성공", Toast.LENGTH_SHORT).show();
        }
    }
}
