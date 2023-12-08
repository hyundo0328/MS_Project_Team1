package com.example.ms_termproject_team1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MealInfoInputSixth extends AppCompatActivity {

    private int totalPrice = 0;
    public static String priceText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_info_input_sixth);

        // 각 가격 버튼에 대한 class 호출
        Button tenThousandBtn = findViewById(R.id.tenThousand);
        Button thousandBtn = findViewById(R.id.thousand);
        Button hundredBtn = findViewById(R.id.hundred);
        Button mtenThousandBtn = findViewById(R.id.mtenThousand);
        Button mthousandBtn = findViewById(R.id.mthousand);
        Button mhundredBtn = findViewById(R.id.mhundred);
        Button completeBtn = findViewById(R.id.btnComplete);

        tenThousandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 10,000원 추가
                changePrice(10000);
            }
        });

        thousandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1,000원 추가
                changePrice(1000);
            }
        });

        hundredBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 100원 추가
                changePrice(100);
            }
        });

        mtenThousandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 10,000원 추가
                changePrice(-10000);
            }
        });

        mthousandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1,000원 추가
                changePrice(-1000);
            }
        });

        mhundredBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 100원 추가
                changePrice(-100);
            }
        });

        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 입력 완료 버튼 클릭 시 동작
                showCompletionMessage();
                priceText = Integer.toString(totalPrice);
            }
        });

        Button prev5 = findViewById(R.id.prev5);
        Button next = findViewById(R.id.next);

        // 이전 페이지(페이지 5)로 이동
        prev5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MealInfoInputSixth.this, MealInfoInputFifth.class);
                startActivity(intent);
            }
        });

        // 다음 페이지(최종 확인 페이지)로 이동
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MealInfoInputSixth.this, MealInfoInputFinal.class);
                startActivity(intent);
            }
        });
    }

    // 가격을 저장하는 함수
    private void changePrice(int price) {
        totalPrice += price;

        // TextView에 현재 가격 표시
        TextView priceText = findViewById(R.id.priceText);
        if(totalPrice < 0){
            priceText.setText("0원");
        } else {
            priceText.setText(String.valueOf(totalPrice) + "원");
        }
    }

    // 입력 완료 시 메시지를 보여주는 함수
    private void showCompletionMessage() {
        TextView completeText = findViewById(R.id.completeText);
        completeText.setText("가격이 저장되었습니다.");
    }
}