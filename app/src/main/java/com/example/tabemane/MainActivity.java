package com.example.tabemane;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //定義
        //共通・ボタン
        ImageButton home_imagebutton = findViewById(R.id.homeImageButton);
        ImageButton food_imagebutton = findViewById(R.id.foodImageButton);
        ImageButton recipe_imagebutton = findViewById(R.id.recipeImageButton);
        ImageButton coupon_imagebutton = findViewById(R.id.couponImageButton);
        ImageButton shop_imagebutton = findViewById(R.id.shopImageButton);
        Toolbar toolbar = findViewById(R.id.toolbar);

        //page毎

        TextView point_TextView = findViewById(R.id.point_TextView);
        ImageView barcode_ImageView = findViewById(R.id.barcode_ImageView);



        //page毎



        //インスタンス
        home_imagebutton.setOnClickListener(new HomeClickListner());
        food_imagebutton.setOnClickListener(new FoodClickListner());
        recipe_imagebutton.setOnClickListener(new RecipeClickListner());
        coupon_imagebutton.setOnClickListener(new CouponClickListner());
        shop_imagebutton.setOnClickListener(new ShopClickListner());
        toolbar.setTitle("食べマネ");
        setSupportActionBar(toolbar);




    }

    //サブメニュー用
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //サブメニュー押下時
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        //設定押下時
        if(id==R.id.action_string1){
            Intent intent = new Intent(getApplication(), SettingActivity.class);
            startActivity(intent);
        }

        //ログアウト押下時
        if(id==R.id.action_string2){
            Intent intent = new Intent(getApplication(),MainActivity.class);
            startActivity(intent);
        }
        return true;
    }

    //home押下時
    private class HomeClickListner implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(),MainActivity.class);
            startActivity(intent);
        }
    }

    //food押下時
    private class FoodClickListner implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), FoodActivity.class);
            startActivity(intent);
        }
    }

    //recipe押下時
    private class RecipeClickListner implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), RecipeActivity.class);
            startActivity(intent);
        }
    }

    //coupon押下時
    private class CouponClickListner implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), CouponActivity.class);
            startActivity(intent);
        }
    }

    //shop押下時
    private class ShopClickListner implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), ShopActivity.class);
            startActivity(intent);
        }
    }
}