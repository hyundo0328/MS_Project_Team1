package com.example.ms_termproject_team1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MealInfoInputThird extends AppCompatActivity {

    public static ArrayList<String> totalMenuList;
    public static ArrayList<Integer> kcalList;
    public static int totalKcal;
    private int randomNumber;
    private CustomAdapter adapter;
    private ListView menuList;
    private EditText menuText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_info_input_third);

        // 초기화
        totalMenuList = new ArrayList<String>();
        kcalList = new ArrayList<Integer>();
        adapter = new CustomAdapter(this, totalMenuList, this);
        totalKcal = 0;

        menuList = findViewById(R.id.menuList);
        menuText = findViewById(R.id.menuText);

        // 어댑터 적용
        menuList.setAdapter(adapter);

        Button menuAdd = findViewById(R.id.menuAdd); // 메뉴 추가 버튼
        // 메뉴 추가 버튼 이벤트
        menuAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Random random = new Random();

                // 300에서 400 사이의 랜덤한 숫자 생성
                randomNumber = random.nextInt(201) + 300;

                addItemList();
            }
        });

        Button prev2 = findViewById(R.id.prev2);
        Button next4 = findViewById(R.id.next4);

        // 이전 페이지(페이지 2)로 이동
        prev2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MealInfoInputThird.this, MealInfoInputSecond.class);
                startActivity(intent);
            }
        });

        // 다음 페이지(페이지 4)로 이동
        next4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MealInfoInputThird.this, MealInfoInputFourth.class);
                startActivity(intent);
            }
        });
    }

    // 메뉴 추가 함수
    public void addItemList(){
        // 메뉴 등록
        totalMenuList.add(menuText.getText().toString());
        // 칼로리 등록
        kcalList.add(randomNumber);
        totalKcal += randomNumber;
        // 적용
        adapter.notifyDataSetChanged();
        // 입력창 초기화
        menuText.setText("");
    }

    // 메뉴 삭제 함수
    public void deleteItemOnClick(int position) {
        // 리스트에서 해당 항목 제거
        totalMenuList.remove(position);
        totalKcal -= kcalList.get(position);
        kcalList.remove(position);

        // 어댑터 갱신
        adapter.notifyDataSetChanged();
    }
}

// 리스트에 TextView랑 Button을 한 번에 출력하는 class
class CustomAdapter extends ArrayAdapter<String> {
    private final MealInfoInputThird activity;

    public CustomAdapter(Context context, List<String> items, MealInfoInputThird activity) {
        super(context, R.layout.list_item, items);
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.list_item, null);
        }

        TextView textViewItem = view.findViewById(R.id.menuName);
        Button buttonDelete = view.findViewById(R.id.buttonDelete);

        // 삭제 버튼에 고정된 가로 길이 설정
        ViewGroup.LayoutParams params = buttonDelete.getLayoutParams();
        params.width = 200;
        buttonDelete.setLayoutParams(params);

        // TextView에 텍스트 설정
        textViewItem.setText(getItem(position));

        // 삭제 버튼에 대한 onClickListener 설정
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 여기서 삭제 버튼 클릭을 처리하거나 deleteItemOnClick 같은 메서드 호출
                activity.deleteItemOnClick(position);
            }
        });

        return view;
    }
}