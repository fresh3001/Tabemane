package com.example.tabemane;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;

public class Alert_setting extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertsetting);

        /*************共通frame*************/
        //定義
        ImageButton home_imagebutton = findViewById(R.id.homeImageButton);
        ImageButton food_imagebutton = findViewById(R.id.foodImageButton);
        ImageButton recipe_imagebutton = findViewById(R.id.recipeImageButton);
        ImageButton coupon_imagebutton = findViewById(R.id.couponImageButton);
        ImageButton shop_imagebutton = findViewById(R.id.shopImageButton);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //インスタンス
        home_imagebutton.setOnClickListener(new Alert_setting.HomeClickListener());
        food_imagebutton.setOnClickListener(new Alert_setting.FoodClickListener());
        recipe_imagebutton.setOnClickListener(new Alert_setting.RecipeClickListener());
        coupon_imagebutton.setOnClickListener(new Alert_setting.CouponClickListener());
        shop_imagebutton.setOnClickListener(new Alert_setting.ShopClickListener());
        toolbar.setTitle("食べマネ");
        setSupportActionBar(toolbar);
        /*************共通frame*************/

        /******************ここから下にpage毎記述*******************/
        Spinner aSpinner=findViewById(R.id.aSpinner);
        aSpinner.setOnItemSelectedListener(this);

        Spinner bSpinner=findViewById(R.id.bSpinner);
        bSpinner.setOnItemSelectedListener(this);
    }

    /***********************共通listner*********************/

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
            Intent intent = new Intent(getApplication(), LoginActivity.class);
            startActivity(intent);
        }
        return true;
    }

    //home押下時
    private class HomeClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        }
    }

    //food押下時
    private class FoodClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), FoodActivity.class);
            startActivity(intent);
        }
    }

    //recipe押下時
    private class RecipeClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), RecipeActivity.class);
            startActivity(intent);
        }
    }

    //coupon押下時
    private class CouponClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(),CouponActivity.class);
            startActivity(intent);
        }
    }

    //shop押下時
    private class ShopClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), ShopActivity.class);
            startActivity(intent);
        }
    }

    /***********************共通listener*********************/

    /***********************page毎listener*********************/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}