package com.example.ms_termproject_team1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MealInfoViewDetail extends AppCompatActivity {

    TextView detailList;
    String date, restaurant, menu, kcal,
            totalKcal, start, eating, price, point;
    byte[] image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_info_view_detail);

        // Intent에서 식사 세부 정보 가져오기
        Intent intent = getIntent();
        date = intent.getExtras().getString("date");
        restaurant = intent.getExtras().getString("restaurant");
        menu = intent.getExtras().getString("menu");
        kcal = intent.getExtras().getString("kcal");
        totalKcal = intent.getExtras().getString("totalKcal");
        start = intent.getExtras().getString("start");
        eating = intent.getExtras().getString("eating");
        price = intent.getExtras().getString("price");
        point = intent.getExtras().getString("point");
        image = intent.getExtras().getByteArray("image");


        if (date != null) {
            // 예: 일부 정보를 포함한 Toast 표시
            // Toast.makeText(this, "선택한 식사: " + mealList.getMealName(), Toast.LENGTH_SHORT).show();
        } else {
            // 데이터가 전달되지 않은 경우 처리
            Toast.makeText(this, "데이터가 전달되지 않았습니다", Toast.LENGTH_SHORT).show();
        }

        // mealDetails를 사용하여 XML 레이아웃에 값을 설정
        TextView detailDate = findViewById(R.id.detailDate);

        detailList = findViewById(R.id.detailList);

        String mealType = "";
        // 식사 시간 조식, 중식, 석식 구분
        try {
            Date dateNew = parseTime(start);

            mealType = getMealType(dateNew);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        detailDate.setText(date + " "+mealType);

        Button btnInfoView = findViewById(R.id.btnInfoView);
        Button btnKcalView = findViewById(R.id.btnKcalView);

        btnInfoView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                detailList.setText("식당 이름 : "+restaurant);
                detailList.append("\n식사 메뉴 : "+menu);
                detailList.append("\n식사 가격 : "+price+"원");
                detailList.append("\n식사 시간 : "+start+", "+eating);
                detailList.append("\n평점 : "+point);
            }
        });

        btnKcalView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                detailList.setText("총 칼로리 : "+totalKcal+" kcal");
                String[] menu_list = menu.split(", ");
                String[] kcal_list = kcal.split(", ");
                for(int i=0;i<menu_list.length;i++){
                    detailList.append("\n"+menu_list[i]+" : "+kcal_list[i]+" kcal");
                }
            }
        });

        ImageView imageView = findViewById(R.id.detailImage);
        byte[] imageByteArray = image;
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
        imageView.setImageBitmap(bitmap);
    }

    // 조식, 중식, 석식을 구분하는 함수
    private static String getMealType(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        // 여기에서 조식, 중식, 석식을 나누는 규칙을 정의할 수 있습니다.
        if (hour < 11) {
            return "조식";
        } else if (hour < 17) {
            return "중식";
        } else {
            return "석식";
        }
    }

    // String 시간을 Date 객체로 파싱하는 함수
    private static Date parseTime(String timeString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.parse(timeString);
    }
}