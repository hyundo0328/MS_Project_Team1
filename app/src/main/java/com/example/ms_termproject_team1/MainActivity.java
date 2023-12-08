package com.example.ms_termproject_team1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_input_move = findViewById(R.id.btn_input_move);
        Button btn_view_move = findViewById(R.id.btn_view_move);
        Button btn_analysis_move = findViewById(R.id.btn_analysis_move);

        btn_input_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭 시 다른 액티비티로 이동
                Intent intent = new Intent(MainActivity.this, MealInfoInput.class);
                startActivity(intent);
            }
        });

        btn_view_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭 시 다른 액티비티로 이동
                Intent intent = new Intent(MainActivity.this, MealInfoView.class);
                startActivity(intent);
            }
        });

        btn_analysis_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭 시 다른 액티비티로 이동
                Intent intent = new Intent(MainActivity.this, MealAnalysis.class);
                startActivity(intent);
            }
        });
    }
}