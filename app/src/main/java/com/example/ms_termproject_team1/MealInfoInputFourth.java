package com.example.ms_termproject_team1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MealInfoInputFourth extends AppCompatActivity {

    public static String gradeText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_info_input_fourth);

        Button point1Btn = findViewById(R.id.point1);
        Button point2Btn = findViewById(R.id.point2);
        Button point3Btn = findViewById(R.id.point3);
        Button point4Btn = findViewById(R.id.point4);
        Button point5Btn = findViewById(R.id.point5);

        ImageView pointImage = findViewById(R.id.pointImage);

        // 1점
        point1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newImageResource = R.drawable.point1;
                pointImage.setImageResource(newImageResource);

                gradeText = point1Btn.getText().toString();
                showPoint(1);
            }
        });

        // 2점
        point2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newImageResource = R.drawable.point2;
                pointImage.setImageResource(newImageResource);

                gradeText = point2Btn.getText().toString();
                showPoint(2);
            }
        });

        // 3점
        point3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newImageResource = R.drawable.point3;
                pointImage.setImageResource(newImageResource);

                gradeText = point3Btn.getText().toString();
                showPoint(3);
            }
        });

        // 4점
        point4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newImageResource = R.drawable.point4;
                pointImage.setImageResource(newImageResource);

                gradeText = point4Btn.getText().toString();
                showPoint(4);
            }
        });

        // 5점
        point5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newImageResource = R.drawable.point5;
                pointImage.setImageResource(newImageResource);

                gradeText = point5Btn.getText().toString();
                showPoint(5);
            }
        });

        Button prev3 = findViewById(R.id.prev3);
        Button next5 = findViewById(R.id.next5);

        // 이전 페이지(페이지 3)로 이동
        prev3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MealInfoInputFourth.this, MealInfoInputThird.class);
                startActivity(intent);
            }
        });

        // 다음 페이지(페이지 5)로 이동
        next5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MealInfoInputFourth.this, MealInfoInputFifth.class);
                startActivity(intent);
            }
        });
    }

    // 평점을 출력해주는 함수
    private void showPoint(int point) {
        // 평점을 pointText에 표시
        TextView pointText = findViewById(R.id.pointText);
        pointText.setText("음식 평점: " + point + "점");
    }
}