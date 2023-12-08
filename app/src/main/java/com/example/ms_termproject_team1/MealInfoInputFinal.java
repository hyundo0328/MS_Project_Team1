package com.example.ms_termproject_team1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MealInfoInputFinal extends AppCompatActivity {

    private static TextView totalText;
    private ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_info_input_final);

        Button btnInputOut = findViewById(R.id.btnInputOut);
        totalText = findViewById(R.id.totalText);

        // 식당 명 출력
        totalText.append("식당 이름 : "+MealInfoInput.restaurantText +"\n");

        // 식사 메뉴+칼로리 출력
        totalText.append("식사 메뉴 : ");
        for(int i = 0; i<MealInfoInputThird.totalMenuList.size(); i++){
            if(i == MealInfoInputThird.totalMenuList.size()-1){
                totalText.append(MealInfoInputThird.totalMenuList.get(i)+" ("+ MealInfoInputThird.kcalList.get(i) +"kcal)");
            } else {
                totalText.append(MealInfoInputThird.totalMenuList.get(i)+" ("+ MealInfoInputThird.kcalList.get(i) +"kcal), ");
            }
        }
        // 총 칼로리 출력
        totalText.append("\n총 칼로리 : "+ MealInfoInputThird.totalKcal +"kcal");

        // 식사 날짜, 시작시간, 식사시간 출력
        totalText.append("\n식사 날짜 : "+MealInfoInputFifth.selectedDate);
        totalText.append("\n식사 시작시간 : "+MealInfoInputFifth.startTime);
        totalText.append("\n총 식사시간 : "+MealInfoInputFifth.differenceMinutesText);

        // 메뉴 가격 출력
        totalText.append("\n메뉴 가격 : "+MealInfoInputSixth.priceText+"원");

        // 메뉴 평점 출력
        totalText.append("\n메뉴 평점 : "+MealInfoInputFourth.gradeText);

        // 음식 사진 불러오기
        imageView2 = findViewById(R.id.imageView2);
        Bitmap bitmap = BitmapFactory.decodeByteArray(MealInfoInputSecond.imageViewToByte(MealInfoInputSecond.imageView), 0,
                MealInfoInputSecond.imageViewToByte(MealInfoInputSecond.imageView).length);
        imageView2.setImageBitmap(bitmap);

        btnInputOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 입력값 변수에 담기
                String name = MealInfoInput.restaurantText;
                String meal = "";
                for(int i = 0; i<MealInfoInputThird.totalMenuList.size(); i++){
                    if(i == MealInfoInputThird.totalMenuList.size()-1){
                        meal += MealInfoInputThird.totalMenuList.get(i);
                    } else {
                        meal += (MealInfoInputThird.totalMenuList.get(i)+", ");
                    }
                }
                String date = MealInfoInputFifth.selectedDate;
                String start = MealInfoInputFifth.startTime;
                String time = MealInfoInputFifth.differenceMinutesText;
                String kcal = "";
                for(int i=0;i<MealInfoInputThird.kcalList.size();i++){
                    if(i == MealInfoInputThird.kcalList.size()-1){
                        kcal += String.valueOf(MealInfoInputThird.kcalList.get(i));
                    } else {
                        kcal += String.valueOf(MealInfoInputThird.kcalList.get(i)+", ");
                    }
                }
                String totalKcal = String.valueOf(MealInfoInputThird.totalKcal);
                String price = MealInfoInputSixth.priceText;
                String point = MealInfoInputFourth.gradeText;
                byte[] image = MealInfoInputSecond.imageViewToByte(MealInfoInputSecond.imageView);

                // DB 객체 생성
                MealCheckDB addMealInfoDB = new MealCheckDB(MealInfoInputFinal.this);

                // DB에 저장하기
                addMealInfoDB.addMeal(name, meal, date, start, time, kcal, totalKcal, price, point, image);

                Intent intent = new Intent(MealInfoInputFinal.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}