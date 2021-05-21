package com.example.tabemane;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;

public class Recipe02Activity extends AppCompatActivity {

    String[] foods = {"じゃがいも", "にんじん", "たまねぎ"};

    int[] quants = {4, 1, 1};

    int cnt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe02);

        /*************共通frame*************/
        //定義
        ImageButton home_imagebutton = findViewById(R.id.homeImageButton);
        ImageButton food_imagebutton = findViewById(R.id.foodImageButton);
        ImageButton recipe_imagebutton = findViewById(R.id.recipeImageButton);
        ImageButton coupon_imagebutton = findViewById(R.id.couponImageButton);
        ImageButton shop_imagebutton = findViewById(R.id.shopImageButton);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //インスタンス
        home_imagebutton.setOnClickListener(new Recipe02Activity.HomeClickListener());
        food_imagebutton.setOnClickListener(new Recipe02Activity.FoodClickListener());
        recipe_imagebutton.setOnClickListener(new Recipe02Activity.RecipeClickListener());
        coupon_imagebutton.setOnClickListener(new Recipe02Activity.CouponClickListener());
        shop_imagebutton.setOnClickListener(new Recipe02Activity.ShopClickListener());
        toolbar.setTitle("食べマネ");
        setSupportActionBar(toolbar);
        /*************共通frame*************/

        /************Page毎frame***********/
        Button makeButton = findViewById(R.id.make_button);
        makeButton.setOnClickListener(new makeButtonClickListener());

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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //設定押下時
        if (id == R.id.action_string1) {
            Intent intent = new Intent(getApplication(), SettingActivity.class);
            startActivity(intent);
        }

        //ログアウト押下時
        if (id == R.id.action_string2) {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        }
        return true;
    }

    //home押下時
    private class HomeClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        }
    }

    //food押下時
    private class FoodClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), FoodActivity.class);
            startActivity(intent);
        }
    }

    //recipe押下時
    private class RecipeClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), RecipeActivity.class);
            startActivity(intent);
        }
    }

    //coupon押下時
    private class CouponClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), CouponActivity.class);
            startActivity(intent);
        }
    }

    //shop押下時
    private class ShopClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), ShopActivity.class);
            startActivity(intent);
        }
    }

    /***********************共通listener*********************/

    /***********************page毎listener*********************/
    private class makeButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            reference.child("食材").addValueEventListener(listener);
        }
    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Log.d("一覧", snapshot.getValue().toString());

            HashMap map = (HashMap) snapshot.getValue();
            Iterator mapkey = map.keySet().iterator();

            if(cnt == 1) {
                editDB(map, mapkey);
                cnt++;
            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    private void editDB(HashMap map, Iterator mapkey) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference = reference.child("食材");

        while (mapkey.hasNext()) {
            Object key = mapkey.next();
            HashMap map_child = (HashMap) map.get(key);
            Log.d("あべ",key.toString());
            for(int i = 0;i<3;i++){
                if (map_child.get("name").equals(foods[i])) {
                    long x = (Long)map_child.get("quant");
                    reference.child(key.toString()).child("quant").setValue((int)x - (quants[i]),null);
                }
            }

        }
        Intent intent = new Intent(getApplication(), RecipeActivity.class);
        startActivity(intent);

    }
}