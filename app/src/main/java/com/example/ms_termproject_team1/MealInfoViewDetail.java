package com.example.ms_termproject_team1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MealInfoViewDetail extends AppCompatActivity {

    TextView detailDate, detailRestaurant, detailMenu,
            detailPrice, detailTime, detailPoint;

    ImageView imageView3;
    String date, restaurant, menu, price, time, point;
    byte[] image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_info_view_detail);

        // Intent에서 식사 세부 정보 가져오기
        Intent intent = getIntent();
        MealList mealList = (MealList) intent.getSerializableExtra("mealList");
        if (mealList != null) {
            // 예: 일부 정보를 포함한 Toast 표시
            // Toast.makeText(this, "선택한 식사: " + mealList.getMealName(), Toast.LENGTH_SHORT).show();
        } else {
            // 데이터가 전달되지 않은 경우 처리
            Toast.makeText(this, "데이터가 전달되지 않았습니다", Toast.LENGTH_SHORT).show();
        }

        // mealDetails를 사용하여 XML 레이아웃에 값을 설정
        if (mealList != null) {
            TextView detailDate = findViewById(R.id.detailDate);
            TextView detailRestaurant = findViewById(R.id.detailRestaurant);
            TextView detailMenu = findViewById(R.id.detailMenu);
            TextView detailPrice = findViewById(R.id.detailPrice);
            TextView detailTime = findViewById(R.id.detailTime);
            TextView detailPoint = findViewById(R.id.detailPoint);

            String mealType="";
            // 식사 시간 조식, 중식, 석식 구분
            try {
                Date date = parseTime(mealList.getMeal_start());

                mealType = getMealType(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            detailDate.setText(mealList.getMeal_date() + " "+mealType);
            detailRestaurant.setText("식당 이름 : "+mealList.getRestaurant_name());
            detailMenu.setText("식사 메뉴 : "+mealList.getMeal_menu());
            detailPrice.setText("식사 가격 : "+mealList.getMeal_price()+"원");
            detailTime.setText("식사 시간 : "+mealList.getMeal_start()+", "+mealList.getMeal_time());
            detailPoint.setText("평점 : "+mealList.getMeal_point());

            // 이미지가 있는 경우 적절한 라이브러리를 사용하여 로드
            ImageView imageView = findViewById(R.id.imageView3);
            byte[] imageByteArray = mealList.getMenu_image();
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
            imageView.setImageBitmap(bitmap);
        }
    }

    // 조식, 중식, 석식을 구분하는 함수
    private static String getMealType(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        // 여기에서 조식, 중식, 석식을 나누는 규칙을 정의할 수 있습니다.
        if (hour < 12) {
            return "조식";
        } else if (hour < 18) {
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