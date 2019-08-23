/*
 *  ===========================================================================
 *  * QuMedia Confidential
 *  *
 *  * (C) Copyright QuMedia Corp. 2014.
 *  * ===========================================================================
 */

package com.example.user.buttomnavigation_test.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.user.buttomnavigation_test.common.CustomerBaseActivity;


/**
 * ProductInfoActivity
 * <p/>
 * 驗證
 */
public class ProductInfoActivity extends CustomerBaseActivity {

    private static final String TAG = ProductInfoActivity.class.getName();

    private TextView btnBack;

    public ProductInfoActivity() {
        super(R.layout.product_info, true, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
        setListeners();
    }


    /**
     * 設定 Layout
     */
    private void findViews() {
        btnBack=(TextView)findViewById(R.id.btnBack);
    }


    /**
     * setListeners
     */
    private void setListeners() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductInfoActivity.this.finish();
            }
        });
    }


}
