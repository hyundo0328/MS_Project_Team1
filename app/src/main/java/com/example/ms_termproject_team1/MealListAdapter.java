package com.example.ms_termproject_team1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
        CardView cardView;

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
            cardView = itemView.findViewById(R.id.cardView);
        }

        public void setItem(MealList meal){
            mealDate.setText(meal.getMeal_date());
            mealRestaurant.setText(meal.getRestaurant_name());
            mealMenu.setText(meal.getMeal_menu());
            mealTotalKcal.setText(meal.getMeal_totalKcal());
            mealEating.setText(meal.getMeal_time());
            mealPrice.setText(meal.getMeal_price());
            mealPoint.setText(meal.getMeal_point());
            byte[] image = meal.getMenu_image();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            menuImage.setImageBitmap(bitmap);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.meal_info, parent, false);

        return new MyViewHolder(view);
    }

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

        // holder.setItem(meal); // 화면에 데이터 담기

        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int mPosition = holder.getAdapterPosition();

                Context context = view.getContext();

                Intent intent = new Intent(context, MealInfoViewDetail.class);

                intent.putExtra("date", mealLists.get(mPosition).getMeal_date());
                intent.putExtra("restaurant", mealLists.get(mPosition).getRestaurant_name());
                intent.putExtra("menu", mealLists.get(mPosition).getMeal_menu());
                intent.putExtra("kcal", mealLists.get(mPosition).getMeal_kcal());
                intent.putExtra("totalKcal", mealLists.get(mPosition).getMeal_totalKcal());
                intent.putExtra("start",mealLists.get(mPosition).getMeal_start());
                intent.putExtra("eating", mealLists.get(mPosition).getMeal_time());
                intent.putExtra("price", mealLists.get(mPosition).getMeal_price());
                intent.putExtra("point", mealLists.get(mPosition).getMeal_point());
                intent.putExtra("image", mealLists.get(mPosition).getMenu_image());

                ((MealInfoView)context).startActivity(intent);
            }
        });
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

    // OnItemClickListener 인터페이스 선언
    public interface OnItemClickListener {
    }

    // OnItemClickListener 참조 변수 선언
    private OnItemClickListener itemClickListener;
}
