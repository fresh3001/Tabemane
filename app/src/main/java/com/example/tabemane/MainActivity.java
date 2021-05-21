package com.example.tabemane;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {

    final String TAG = "DialogTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DBアクセス
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("食材").addValueEventListener(listener);


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
        home_imagebutton.setOnClickListener(new HomeClickListener());
        food_imagebutton.setOnClickListener(new FoodClickListener());
        recipe_imagebutton.setOnClickListener(new RecipeClickListener());
        coupon_imagebutton.setOnClickListener(new CouponClickListener());
        shop_imagebutton.setOnClickListener(new ShopClickListener());
        toolbar.setTitle("食べマネ");
        setSupportActionBar(toolbar);

        //toolbar.setNavigationIcon(R.drawable.tabemane_logo);




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
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Log.d("一覧",snapshot.getValue().toString());

            HashMap map = (HashMap)snapshot.getValue();
            Iterator mapkey = map.keySet().iterator();

            syouhi_Alert(map,mapkey);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    private void syouhi_Alert(HashMap map,Iterator mapkey){

        boolean flag = false;
        long x = 1;

        //name=じゃがいもがあったら表示でもいいかも
        //↑そのためには個数0になったら消すシステムが必要

        while (mapkey.hasNext()) {
            Object key = mapkey.next();
            HashMap map_child = (HashMap) map.get(key);
            if(map_child.get("quant").equals(x))
                flag = true;
        }

        if(!flag){
            //消費期限アラート
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("・じゃがいも(2021年5月27日)\n・人参(2021年5月28日)\nの消費期限が近づいています。\n");
            builder.setPositiveButton(
                    "使用済",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            builder.setNegativeButton(
                    "レシピ",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(
                                    getApplication(), RecipeActivity.class);
                            startActivity(intent);
                        }
                    });
            builder.setNeutralButton(
                    "閉じる",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            builder.show();
        }

    }

}