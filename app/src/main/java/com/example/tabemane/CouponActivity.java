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
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
public class CouponActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);


        /*************共通frame*************/
        //定義
        ImageButton home_imagebutton = findViewById(R.id.homeImageButton);
        ImageButton food_imagebutton = findViewById(R.id.foodImageButton);
        ImageButton recipe_imagebutton = findViewById(R.id.recipeImageButton);
        ImageButton coupon_imagebutton = findViewById(R.id.couponImageButton);
        ImageButton shop_imagebutton = findViewById(R.id.shopImageButton);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //インスタンス
        home_imagebutton.setOnClickListener(new HomeClickListener());
        food_imagebutton.setOnClickListener(new FoodClickListener());
        recipe_imagebutton.setOnClickListener(new RecipeClickListener());
        coupon_imagebutton.setOnClickListener(new CouponClickListener());
        shop_imagebutton.setOnClickListener(new ShopClickListener());
        toolbar.setTitle("食べマネ");
        setSupportActionBar(toolbar);
        /*************共通frame*************/


        /******************ここから下にpage毎記述*******************/
//        ListView coupon_listview = findViewById(R.id.couponListView);
//        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,strings);
//        coupon_listview.setAdapter(adapter);



        //DBアクセス
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("クーポン").addValueEventListener(listener);

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

            LinearLayout couponLayout = findViewById(R.id.couponLayout);

            int filenumber = 1;

            while (mapkey.hasNext()) {
                Object key = mapkey.next();
                HashMap map_child = (HashMap)map.get(key);
                Log.d("あべ",key+ "=" +map_child.get("name"));
                if(!key.equals("001"))
                    generateView(map_child.get("name").toString(),couponLayout,filenumber);
            }


        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    private void generateView(String text,LinearLayout couponLayout,int filenumber){

        ImageView imageView = new ImageView(this);
        Resources res = getResources();
        String filename = "coupon" + String.valueOf(filenumber )+ "image";
        int fileId = res.getIdentifier(filename, "drawable", getPackageName());
        imageView.setImageResource(fileId);

        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20f);
        textView.setPadding(0,10,0,30);

        couponLayout.addView(imageView);
        couponLayout.addView(textView);

    }


}

