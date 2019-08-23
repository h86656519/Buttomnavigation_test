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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.buttomnavigation_test.common.CustomerBaseActivity;


/**
 * ProductInfoActivity
 * <p/>
 * 驗證
 */
public class ProductUseActivity extends CustomerBaseActivity {

    private static final String TAG = ProductUseActivity.class.getName();

    private TextView btnBack;

    private LinearLayout layout_product1;

    private LinearLayout layout_product2;

    private TextView btnProduct1;

    private TextView btnProduct2;

    /**
     *  1  :  膠囊
     *  2  :  粉劑
     */
    private int btnSelectedType = 1;

    public ProductUseActivity() {
        super(R.layout.product_use, true, false);
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
        btnProduct1=(TextView)findViewById(R.id.btnProduct1);
        btnProduct2=(TextView)findViewById(R.id.btnProduct2);
        layout_product1=(LinearLayout)findViewById(R.id.layout_product1);
        layout_product2=(LinearLayout)findViewById(R.id.layout_product2);
        layout_product2.setVisibility(View.GONE);

        //預設選擇
        btnSelectedType=1;
        btnProduct1.setSelected(true);
        btnProduct2.setSelected(false);
    }


    /**
     * setListeners
     */
    private void setListeners() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductUseActivity.this.finish();
            }
        });
        btnProduct1.setOnClickListener(onChange);
        btnProduct2.setOnClickListener(onChange);
    }

    /**
     * 設定選定的項目為以選取
     */
    private void doSetSelectedItem() {
        btnProduct1.setSelected(false);
        btnProduct2.setSelected(false);
        if (btnSelectedType == 1) {
            btnProduct1.setSelected(true);
            btnProduct2.setSelected(false);
            layout_product1.setVisibility(View.VISIBLE);
            layout_product2.setVisibility(View.GONE);
        } else {
            btnProduct1.setSelected(false);
            btnProduct2.setSelected(true);
            layout_product1.setVisibility(View.GONE);
            layout_product2.setVisibility(View.VISIBLE);
        }
    }

    /**
     *
     */
    private View.OnClickListener onChange = new View.OnClickListener() {
        public void onClick(View v) {
            if(v.getId()==R.id.btnProduct1){
                btnSelectedType=1;

            }else if(v.getId()==R.id.btnProduct2){
                btnSelectedType=2;

            }
            doSetSelectedItem();

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        doSetSelectedItem();
    }


}

