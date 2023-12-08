package com.example.ms_termproject_team1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWindow;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MealInfoView extends AppCompatActivity {

    MealCheckDB db;
    ArrayList<MealList> mealLists = new ArrayList<>();
    RecyclerView recyclerView;
    MealListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_info_view);

        // 이미지 크기 오류 방지
        try{
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100*1014*1024);
        } catch (Exception e){
            e.printStackTrace();
        }

        // 리스트에 보여줄 화면
        recyclerView = findViewById(R.id.mealList);
        // 어댑터
        adapter = new MealListAdapter(MealInfoView.this);
        // 어댑터 등록
        recyclerView.setAdapter(adapter);
        // 레이아웃 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(MealInfoView.this));
        // DB 생성
        db = new MealCheckDB(MealInfoView.this);
        // 데이터 가져오기
        storeDataInArray();


    }

    void storeDataInArray(){
        Cursor cursor = db.readAllData();

        if(cursor.getCount() == 0){
            // recyclerView.setVisibility(recyclerView.INVISIBLE);
        } else {
            recyclerView.setVisibility(recyclerView.VISIBLE);

            while(cursor.moveToNext()){
                MealList meal = new MealList(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getBlob(10));

                // 데이터 등록
                mealLists.add(meal);
                System.out.println(mealLists);
                adapter.addItem(meal);

                // 적용
                adapter.notifyDataSetChanged();
            }
        }
    }
}