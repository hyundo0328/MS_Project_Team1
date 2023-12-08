package com.example.ms_termproject_team1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MealInfoViewMonth extends AppCompatActivity {
    MealCheckDB db;
    CalendarView calendarView;
    TextView monthView, detailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_info_view_month);

        // 초기화
        calendarView = findViewById(R.id.calendarView);
        monthView = findViewById(R.id.monthView);
        detailText = findViewById(R.id.detailText);
        db = new MealCheckDB(MealInfoViewMonth.this); // DB 생성

        // 초기에 현재 월을 출력
        displayCurrentMonth();

        // CalendarView의 날짜가 선택되었을 때 이벤트 처리
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                // 선택된 날짜의 월을 가져와서 TextView에 표시
                displayMonth(year, month + 1);

                String selectedDate = year + "년 " + (month + 1) + "월 " + dayOfMonth+"일";
                // Toast.makeText(getApplicationContext(), "선택한 날짜: " + selectedDate, Toast.LENGTH_SHORT).show();
                try {
                    storeDateInfo(selectedDate);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // 현재 월을 표시하는 메서드
    private void displayCurrentMonth() {
        // Calendar 객체를 생성하고 현재 월을 가져옴
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1; // 월은 0부터 시작하므로 1을 더해줌

        // displayMonth 메서드를 호출하여 TextView에 현재 월을 설정
        displayMonth(calendar.get(Calendar.YEAR), currentMonth);
    }

    // 월을 표시하는 메서드
    private void displayMonth(int year, int month) {
        // SimpleDateFormat을 사용하여 월을 텍스트로 변환
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // 월은 0부터 시작하므로 1을 빼줌

        String monthText = sdf.format(calendar.getTime());

        // TextView에 월 텍스트 설정
        monthView.setText(monthText+" 식단");
    }

    void storeDateInfo(String date) throws ParseException {
        Cursor cursor = db.readAllData();

        String restaurant = "";
        String menu = "" ;
        String start = "";
        String eating = "";
        // String kcal = "";
        String totalKcal = "";
        String price = "";
        String point = "";
        // byte[] image = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String currentDate = cursor.getString(3);
                try {
                    Date dateFromDatabaseDate = dateFormat.parse(date);
                    Date targetDate = dateFormat.parse(currentDate);

                    // Toast.makeText(getApplicationContext(), targetDate+"   "+dateFromDatabaseDate, Toast.LENGTH_SHORT).show();
                    if (areDatesEqual(dateFromDatabaseDate, targetDate)) {
                        restaurant = cursor.getString(1);
                        menu = cursor.getString(2);
                        start = cursor.getString(4);
                        eating = cursor.getString(5);
                        totalKcal = cursor.getString(7);
                        price = cursor.getString(8);
                        point = cursor.getString(9);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            } while (cursor.moveToNext());
        }

        detailText.setText("식당 이름 : "+restaurant);
        detailText.append("\n식사 메뉴 : "+menu);
        detailText.append("\n총 칼로리 : "+totalKcal+" Kcal");
        detailText.append("\n식사 가격 : "+price+"원");
        detailText.append("\n식사 시간 : "+start+", "+eating);
        detailText.append("\n평점 : "+point);

        cursor.close();
    }

    private static boolean areDatesEqual(Date date1, Date date2) {
        // SimpleDateFormat을 사용하여 년월일 형식으로 날짜를 비교
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date1).equals(sdf.format(date2));
    }
}