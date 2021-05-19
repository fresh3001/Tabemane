package com.example.tabemane;

/************************************************************************:
 * 共通frame部分のjavaファイルです。
 * ファイル名は各自処理に応じて変更(ex.food画面→foodActivity.java)
 * 同様に#1の部分も変更
 * packageの部分は人によって異なるのでpackage以下をコピペ
 * 処理をpage毎～の下に書いていってください
 * 最終更新:2021/05/12
 ************************************************************************/

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
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

//#1
public class FoodActivity extends AppCompatActivity {

    TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        /*************共通frame*************/
        //定義
        ImageButton home_imagebutton = findViewById(R.id.homeImageButton);
        ImageButton food_imagebutton = findViewById(R.id.foodImageButton);
        ImageButton recipe_imagebutton = findViewById(R.id.recipeImageButton);
        ImageButton coupon_imagebutton = findViewById(R.id.couponImageButton);
        ImageButton shop_imagebutton = findViewById(R.id.shopImageButton);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //インスタンス
        home_imagebutton.setOnClickListener(new HomeClickListner());
        food_imagebutton.setOnClickListener(new FoodClickListner());
        recipe_imagebutton.setOnClickListener(new RecipeClickListner());
        coupon_imagebutton.setOnClickListener(new CouponClickListner());
        shop_imagebutton.setOnClickListener(new ShopClickListner());
        toolbar.setTitle("食べマネ");
        setSupportActionBar(toolbar);
        /*************共通frame*************/


        /******************ここから下にpage毎記述*******************/
        //DBアクセス
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("野菜").addValueEventListener(listener);

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
    private class HomeClickListner implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        }
    }

    //food押下時
    private class FoodClickListner implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), FoodActivity.class);
            startActivity(intent);
        }
    }

    //recipe押下時
    private class RecipeClickListner implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), RecipeActivity.class);
            startActivity(intent);
        }
    }

    //coupon押下時
    private class CouponClickListner implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), CouponActivity.class);
            startActivity(intent);
        }
    }

    //shop押下時
    private class ShopClickListner implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), ShopActivity.class);
            startActivity(intent);
        }
    }

    /***********************共通listner*********************/

    /***********************page毎listner*********************/
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Log.d("一覧",snapshot.getValue().toString());

            HashMap map = (HashMap)snapshot.getValue();
            Iterator mapkey = map.keySet().iterator();

            textSet(mapkey,map);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };


    //TextViewにセットする
    private void textSet(Iterator mapkey,HashMap map) {

        Resources res = getResources();

        int i = 0;

        while (mapkey.hasNext()) {
            Object key = mapkey.next();
            HashMap map_child = (HashMap)map.get(key);
            String name = map_child.get("name").toString();
            String date = map_child.get("date").toString();
            String quant = map_child.get("quant").toString();

            int viewId = res.getIdentifier("foodName0" + i, "id", getPackageName());
            textView = findViewById(viewId);
            textView.setText(name);

            viewId = res.getIdentifier("foodDate0" + i, "id", getPackageName());
            textView = findViewById(viewId);
            textView.setText(date);

            viewId = res.getIdentifier("foodQuant0" + i, "id", getPackageName());
            textView = findViewById(viewId);
            textView.setText(quant);

            i += 1;
        }
    }
}

