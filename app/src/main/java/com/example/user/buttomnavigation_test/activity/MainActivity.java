package com.example.user.buttomnavigation_test.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.user.buttomnavigation_test.common.CustomerAndroidUtils;
import com.example.user.buttomnavigation_test.common.CustomerConfigData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.entertop:
                intent = new Intent(MainActivity.this, TopActivity.class);
                startActivity(intent);
                break;
            case R.id.vertifyproduct:
             //  Toast.makeText(MainActivity.this, "功能尚待補齊中", Toast.LENGTH_SHORT).show();
//                intent = new Intent(MainActivity.this, ProductVerifyActivity.class);
//                startActivity(intent);

//                CustomerAndroidUtils.startActivity(
//                        MainActivity.this,
//                        ProductVerifyActivity.class,
//                        null,
//                        Intent.FLAG_ACTIVITY_CLEAR_TOP,
//                        CustomerConfigData.ACTION_TAB_1);
                break;
        }
    }
}
