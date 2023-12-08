package com.example.ms_termproject_team1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MealInfoInput extends AppCompatActivity {

    public static String restaurantText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_info_input);

        restaurantText = ""; // restaurantText 초기화

        Button button1 = findViewById(R.id.button1); // 한강로 부엌
        Button button2 = findViewById(R.id.button2); // 돈가스
        Button button3 = findViewById(R.id.button3); // 오넛지
        Button button4 = findViewById(R.id.button4); // 상록원 1층
        Button button5 = findViewById(R.id.button5); // 상록원 2층
        Button button6 = findViewById(R.id.button6); // 상록원 3층
        Button button7 = findViewById(R.id.button7); // 폴 바셋
        Button button8 = findViewById(R.id.button8); // 블루팟
        Button button9 = findViewById(R.id.button9); // 그루터기

        // 선택된 식당 출력 TextView
        TextView restaurant_text = findViewById(R.id.restaurant_text);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼 클릭 시 TextView에 버튼의 텍스트 출력
                restaurantText = button1.getText().toString();
                restaurant_text.setText(restaurantText +"을 선택했습니다.");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼 클릭 시 TextView에 버튼의 텍스트 출력
                restaurantText = button2.getText().toString();
                restaurant_text.setText(restaurantText +"을 선택했습니다.");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼 클릭 시 TextView에 버튼의 텍스트 출력
                restaurantText = button3.getText().toString();
                restaurant_text.setText(restaurantText +"을 선택했습니다.");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼 클릭 시 TextView에 버튼의 텍스트 출력
                restaurantText = button4.getText().toString();
                restaurant_text.setText(restaurantText +"을 선택했습니다.");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼 클릭 시 TextView에 버튼의 텍스트 출력
                restaurantText = button5.getText().toString();
                restaurant_text.setText(restaurantText +"을 선택했습니다.");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼 클릭 시 TextView에 버튼의 텍스트 출력
                restaurantText = button6.getText().toString();
                restaurant_text.setText(restaurantText +"을 선택했습니다.");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼 클릭 시 TextView에 버튼의 텍스트 출력
                restaurantText = button7.getText().toString();
                restaurant_text.setText(restaurantText +"을 선택했습니다.");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼 클릭 시 TextView에 버튼의 텍스트 출력
                restaurantText = button8.getText().toString();
                restaurant_text.setText(restaurantText +"을 선택했습니다.");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼 클릭 시 TextView에 버튼의 텍스트 출력
                restaurantText = button9.getText().toString();
                restaurant_text.setText(restaurantText +"을 선택했습니다.");
            }
        });

        Button prev = findViewById(R.id.prev);
        Button next2 = findViewById(R.id.next2);


        // 2페이지로 이동
        next2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MealInfoInput.this, MealInfoInputSecond.class);

                startActivity(intent);
            }
        });
    }
}