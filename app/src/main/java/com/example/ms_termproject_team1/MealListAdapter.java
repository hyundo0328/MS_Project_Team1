package com.example.ms_termproject_team1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.MyViewHolder> {
    Context context;
    private ArrayList<MealList> mealLists = new ArrayList<>(); // 데이터를 담을 배열

    // MealList에 있는 정보를 가져와서 임시로 저장
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mealDate, mealRestaurant, mealMenu;
        TextView mealEating, mealPrice, mealPoint;
        TextView mealKcal, mealTotalKcal;
        ImageView menuImage;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            mealDate = itemView.findViewById(R.id.mealDate);
            mealRestaurant = itemView.findViewById(R.id.mealRestaurant);
            mealMenu = itemView.findViewById(R.id.mealMenu);
            mealTotalKcal = itemView.findViewById(R.id.mealTotalKcal);
            // mealKcal = itemView.findViewById(R.id.mealKcal);
            mealEating = itemView.findViewById(R.id.mealEating);
            mealPrice = itemView.findViewById(R.id.mealPrice);
            mealPoint = itemView.findViewById(R.id.mealPoint);
            menuImage = itemView.findViewById(R.id.menuImage);
            mainLayout = itemView.findViewById(R.id.main_layout);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.meal_info, parent, false);

        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position){
        MealList meal = mealLists.get(position);

        holder.mealDate.setText(String.valueOf(meal.getMeal_date()));
        holder.mealDate.append(" "+String.valueOf(meal.getMeal_start()));
        holder.mealRestaurant.setText("식사 장소 : "+meal.getRestaurant_name());
        holder.mealMenu.setText("식사 메뉴 : "+meal.getMeal_menu());
        // holder.mealKcal.setText("칼로리 : "+meal.getMeal_kcal());
        holder.mealTotalKcal.setText("총 칼로리 : "+meal.getMeal_totalKcal()+"kcal");
        holder.mealEating.setText("식사 시간 : "+meal.getMeal_time());
        holder.mealPrice.setText("메뉴 가격 : "+meal.getMeal_price());
        holder.mealPoint.setText("평점 : "+meal.getMeal_point());

        byte[] image = meal.getMenu_image();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        holder.menuImage.setImageBitmap(bitmap);

    }

    MealListAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getItemCount(){
        return mealLists.size();
    }

    public void addItem(MealList item){
        mealLists.add(item);
    }

    public void removeItem(int position){
        mealLists.remove(position);
    }


}
