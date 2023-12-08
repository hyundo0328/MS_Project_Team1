package com.example.ms_termproject_team1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MealInfoInputFifth extends AppCompatActivity {

    private TextView eatingStart, eatingEnd, totaltimeText, eatingDate;
    private Calendar calendar;
    private Long today = MaterialDatePicker.todayInUtcMilliseconds();
    private Calendar startCalendar, endCalendar;
    public static String selectedDate = "";
    public static String startTime = "";
    public static String differenceMinutesText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_info_input_fifth);

        selectedDate = ""; // 식사 날짜 초기화
        startTime = ""; // 식사 시간시간 초기화
        differenceMinutesText = ""; // 식사 종료시간 초기화

        eatingStart = findViewById(R.id.eatingStart);
        eatingEnd = findViewById(R.id.eatingEnd);
        totaltimeText = findViewById(R.id.totaltimeText);

        eatingDate = findViewById(R.id.eatingDate);
        calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        startCalendar = Calendar.getInstance(); // 시작시간을 불러오기 위한 Class
        endCalendar = Calendar.getInstance(); // 종료시간을 불러오기 위한 Class

        Button prev4 = findViewById(R.id.prev4);
        Button next6 = findViewById(R.id.next6);

        // 이전 페이지(페이지 4)로 이동
        prev4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MealInfoInputFifth.this, MealInfoInputFourth.class);
                startActivity(intent);
            }
        });

        // 다음 페이지(페이지 6)로 이동
        next6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MealInfoInputFifth.this, MealInfoInputSixth.class);
                startActivity(intent);
            }
        });
    }

    // 달력 Alert 출력 함수
    public void showDatePickerDialog(View view){
        // 오늘 날짜 셋팅
        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker().setTitleText("식사 날짜 선택").setSelection(today).build();

        materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");

        // 확인 버튼
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
                Date date = new Date();
                date.setTime(selection);

                String dateString = simpleDateFormat.format(date);

                eatingDate.setText(dateString);
            }
        });
    }

    // 시계 Alert 출력 함수
    public void showTimePickerDialog(View view) {
        final boolean isStartTime = (view.getId() == R.id.eatingStart);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                (TimePicker view1, int hourOfDay, int minute) -> {
                    // 선택한 시간 저장
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute);

                    if (isStartTime) { // 식사 시작 TextView일 때
                        startCalendar = calendar;
                        eatingStart.setText(formatTime(hourOfDay, minute));
                    } else { // 식사 종료 TextView일 때
                        endCalendar = calendar;
                        eatingEnd.setText(formatTime(hourOfDay, minute));
                    }
                },
                startCalendar.get(Calendar.HOUR_OF_DAY),
                startCalendar.get(Calendar.MINUTE),
                true // 24시간 형식으로 설정
        );

        timePickerDialog.show();
    }

    // 저장 버튼 함수
    public void onSaveButtonClick(View view) {
        long startTimeInMillis;

        if (view.getId() == R.id.dateSave){
            // 날짜 출력
            selectedDate = eatingDate.getText().toString();
        } else if (view.getId() == R.id.startSave) {
            differenceMinutesText = "";
            // 시작 시간을 totaltimeText에 출력
            startTimeInMillis = startCalendar.getTimeInMillis();
            startTime = formatTime(startCalendar.get(Calendar.HOUR_OF_DAY), startCalendar.get(Calendar.MINUTE));
        } else if (view.getId() == R.id.endSave) {
            // 시작 시간과 종료 시간을 가져와 차이를 계산
            startTimeInMillis = startCalendar.getTimeInMillis();
            long endTimeInMillis = endCalendar.getTimeInMillis();
            long differenceInMillis = endTimeInMillis - startTimeInMillis;

            // 차이를 분으로 변환
            long differenceInMinutes = differenceInMillis / (60 * 1000);
            differenceMinutesText = Long.toString(differenceInMinutes)+"분";
        }
        totaltimeText.setText("식사 날짜 : "+selectedDate+"\n식사 시작시간 : "+startTime+"\n총 식사시간 : "+differenceMinutesText);
    }


    // 시간을 00:00 형식으로 포맷하는 메서드
    private String formatTime(int hourOfDay, int minute) {
        return String.format("%02d:%02d", hourOfDay, minute);
    }
}