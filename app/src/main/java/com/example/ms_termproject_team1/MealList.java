package com.example.ms_termproject_team1;

public class MealList {
    private String name;
    private String meal;
    private String date;
    private String start;
    private String time;
    private String kcal;
    private String totalKcal;
    private String price;
    private String point;
    private byte[] image; // 이미지는 리소스 ID로 저장하도록 가정합니다.

    // 생성자
    public MealList(String name, String meal, String date, String start, String time,
                    String kcal, String totalKcal, String price, String point, byte[] image) {
        this.name = name;
        this.meal = meal;
        this.date = date;
        this.start = start;
        this.time = time;
        this.kcal = kcal;
        this.totalKcal = totalKcal;
        this.price = price;
        this.point = point;
        this.image = image;
    }

    // 각 필드의 getter 및 setter 메서드

    public String getRestaurant_name() {
        return name;
    }

    public void setRestaurant_name(String name) {
        this.name = name;
    }

    public String getMeal_menu() {
        return meal;
    }

    public void setMeal_menu(String meal) {
        this.meal = meal;
    }

    public String getMeal_date() {
        return date;
    }

    public void setMeal_date(String date) {
        this.date = date;
    }

    public String getMeal_start() {
        return start;
    }

    public void setMeal_start(String start) {
        this.start = start;
    }

    public String getMeal_time() {
        return time;
    }

    public void setMeal_time(String time) {
        this.time = time;
    }

    public String getMeal_kcal() {
        return kcal;
    }

    public void setMeal_kcal(String kcal) {
        this.kcal = kcal;
    }

    public String getMeal_totalKcal() {
        return totalKcal;
    }

    public void setMeal_totalKcal(String totalKcal) {
        this.totalKcal = totalKcal;
    }

    public String getMeal_price() {
        return price;
    }

    public void setMeal_price(String price) {
        this.price = price;
    }

    public String getMeal_point() {
        return point;
    }

    public void setMeal_point(String point) {
        this.point = point;
    }

    public byte[] getMenu_image() {
        return image;
    }

    public void setMenu_image(byte[] image) {
        this.image = image;
    }
}
