package com.example.ms_termproject_team1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class MealAnalysis extends AppCompatActivity {

    MealCheckDB db;
    TextView monthKcal, monthWater, monthBreakfast, monthLunch, monthDinner;
    Calendar calendar = Calendar.getInstance(); // 현재 날짜 정보를 가져옴

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_analysis);

        monthKcal = findViewById(R.id.monthKcal);
        monthWater = findViewById(R.id.monthWater);
        monthBreakfast = findViewById(R.id.monthBreakfast);
        monthLunch = findViewById(R.id.monthLunch);
        monthDinner = findViewById(R.id.monthDinner);

        // 현재 날짜로부터 한 달 전의 날짜로 설정
        calendar.add(Calendar.MONTH, -1);

        // DB 생성
        db = new MealCheckDB(MealAnalysis.this);
        // 데이터 가져오기
        storeDataInArray();
    }

    void storeDataInArray(){
        Cursor cursor = db.readAllData();

        String restaurant = "";
        String date = "";
        String start = "";
        String totalKcal = "";
        String price = "";

        if(cursor.getCount() == 0){

        } else {

            while(cursor.moveToNext()){
                restaurant = cursor.getString(1);
                date = cursor.getString(3);
                start = cursor.getString(4);
                totalKcal = cursor.getString(7);
                price = cursor.getString(8);

            }
        }
    }
}