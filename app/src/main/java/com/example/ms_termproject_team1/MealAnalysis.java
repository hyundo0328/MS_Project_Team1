package com.example.ms_termproject_team1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MealAnalysis extends AppCompatActivity {

    private MealCheckDB db;
    private TextView monthKcal, monthWater, monthBreakfast, monthLunch, monthDinner;
    private Calendar calendar = Calendar.getInstance(); // 현재 날짜 정보를 가져옴
    private int monthTotalKcal;
    private int monthWaterPrice;
    private int monthBreakfastPrice;
    private int monthLunchPrice;
    private int monthDinnerPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_analysis);

        monthKcal = findViewById(R.id.monthKcal);
        monthBreakfast = findViewById(R.id.monthBreakfast);
        monthLunch = findViewById(R.id.monthLunch);
        monthDinner = findViewById(R.id.monthDinner);
        monthWater = findViewById(R.id.monthWater);

        // 현재 날짜로부터 한 달 전의 날짜로 설정
        calendar.add(Calendar.MONTH, -1);
        monthTotalKcal = 0;
        monthBreakfastPrice = 0;
        monthLunchPrice = 0;
        monthDinnerPrice = 0;

        monthKcal.setText("총 : ");
        monthBreakfast.setText("아침 : ");
        monthLunch.setText("점심 : ");
        monthDinner.setText("저녁 : ");
        monthWater.setText("카페 : ");

        // DB 생성
        db = new MealCheckDB(MealAnalysis.this);
        // 데이터 가져오기
        try {
            storeDataInArray();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    void storeDataInArray() throws ParseException {
        Cursor cursor = db.readAllData();

        String restaurant = "";
        String date = "";
        String start = "";
        String totalKcal = "";
        String price = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        if(cursor.getCount() == 0){

        } else {
            while(cursor.moveToNext()){
                restaurant = cursor.getString(1);
                date = cursor.getString(3);
                start = cursor.getString(4);
                totalKcal = cursor.getString(7);
                price = cursor.getString(8);

                Date inputDate = dateFormat.parse(date);
                // 현재로부터 한 달 전의 날짜와 입력된 날짜를 비교
                if (inputDate.after(calendar.getTime())) {
                    monthTotalKcal += Integer.parseInt(totalKcal);
                    if(restaurant.equals("폴 바셋") || restaurant.equals("블루팟") || restaurant.equals("그루터기")){
                        monthWaterPrice += Integer.parseInt(price);
                    } else {
                        String mealType = "";
                        // 식사 시간 아침, 점심, 저녁 구분
                        try {
                            Date dateNew = parseTime(start);

                            mealType = getMealType(dateNew);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if(mealType.equals("아침")){
                            monthBreakfastPrice += Integer.parseInt(price);
                        } else if(mealType.equals("중식")){
                            monthLunchPrice += Integer.parseInt(price);
                        } else if(mealType.equals("저녁")){
                            monthDinnerPrice += Integer.parseInt(price);
                        }
                    }
                }
            }
        }

        monthKcal.append(String.valueOf(monthTotalKcal)+" Kcal");
        monthBreakfast.append(String.valueOf(monthBreakfastPrice)+" 원");
        monthLunch.append(String.valueOf(monthLunchPrice)+" 원");
        monthDinner.append(String.valueOf(monthDinnerPrice)+" 원");
        monthWater.append(String.valueOf(monthWaterPrice)+" 원");
    }

    private static String getMealType(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        // 여기에서 조식, 중식, 석식을 나누는 규칙을 정의할 수 있습니다.
        if (hour < 11) {
            return "아침";
        } else if (hour < 17) {
            return "점심";
        } else {
            return "저녁";
        }
    }

    // String 시간을 Date 객체로 파싱하는 함수
    private static Date parseTime(String timeString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.parse(timeString);
    }
}