///*
// *  ===========================================================================
// *  * QuMedia Confidential
// *  *
// *  * (C) Copyright QuMedia Corp. 2014.
// *  * ===========================================================================
// */
//
//package com.example.user.buttomnavigation_test;
//
//import android.os.Bundle;
//import android.text.SpannableString;
//import android.text.Spanned;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
////import com.qumedia.android.viartril.customer.R;
////import com.qumedia.android.viartril.customer.common.graphics.ColorSpan;
//
//
///**
// * ProductVerifyActivity
// * <p/>
// * 驗證
// */
//public class ProductVerifyResultActivity extends CustomerBaseActivity {
//
//    private static final String TAG = ProductVerifyResultActivity.class.getName();
//
//    public static final String VERIFY_RESULT_BUNDLE_KEY="VERIFY_RESULT_BUNDLE_KEY";
//
//    private TextView btnBack;
//
////    private TextView showMessage1;
//
////    private TextView showMessage2;
//
//    private ImageView verifyImage;
//
//
//    public ProductVerifyResultActivity() {
//        super(R.layout.product_verify_result, true, false);
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        findViews();
//        setListeners();
//        Bundle bundle = this.getIntent().getExtras();
//        String result = bundle.getString(VERIFY_RESULT_BUNDLE_KEY);
//        //成功
//        if("Y".equals(result)){
////            showMessage1.setText(this.getString(R.string.business_verify_message1));
////            showMessage1.setTextColor(this.getResources().getColor(R.color.text_highlight_color));
//
//            SpannableString showMessageContent = new SpannableString(this.getString(R.string.business_verify_message2));
//            showMessageContent.setSpan(new ColorSpan(this.getResources().getColor(R.color.text_highlight_color)), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            showMessageContent.setSpan(new ColorSpan(this.getResources().getColor(R.color.text_highlight_color)), 12, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
////            showMessage2.setTextColor(this.getResources().getColor(R.color.text_normal_color));
////            showMessage2.setText(showMessageContent);
//            verifyImage.setImageResource(R.drawable.success_icon);
//        }else{
//            ColorSpan us = new ColorSpan(this.getResources().getColor(R.color.text_highlight_color));
//            SpannableString showMessageContent = new SpannableString(this.getString(R.string.business_verify_message3));
//            showMessageContent.setSpan(new ColorSpan(this.getResources().getColor(R.color.text_highlight_color)), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            showMessageContent.setSpan(new ColorSpan(this.getResources().getColor(R.color.text_highlight_color)), 6, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
////            showMessage1.setText(showMessageContent);
//            verifyImage.setImageResource(R.drawable.fail_icon);
//        }
//    }
//
//
//    /**
//     * 初始化 member values
//     */
//    public void initValues() {
//
//
//        setListeners();
//    }
//
//
//    /**
//     * 設定 Layout
//     */
//    private void findViews() {
////        showMessage1=(TextView)findViewById(R.id.showMessage1);
////        showMessage2=(TextView)findViewById(R.id.showMessage2);
//        verifyImage=(ImageView)findViewById(R.id.verifyImage);
//        btnBack=(TextView)findViewById(R.id.btnBack);
//    }
//
//
//    /**
//     * setListeners
//     */
//    private void setListeners() {
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ProductVerifyResultActivity.this.finish();
//            }
//        });
//    }
//
//}
