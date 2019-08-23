package com.example.user.buttomnavigation_test.activity;


import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.user.buttomnavigation_test.common.CustomerAndroidUtils;
import com.example.user.buttomnavigation_test.common.CustomerBaseActivity;
import com.qumedia.android.core.common.QuMediaLogger;

public class WebActivity extends CustomerBaseActivity {

    private static final String TAG = WebActivity.class.getName();

    private TextView btnBack = null;

    private String requestUrl = null;

    public static String REQUEST_URL_BUNDLE_KEY="REQUEST_URL_BUNDLE_KEY";

    public WebActivity(){
        super(R.layout.web, false);
    }

    /**
     * Called when the activity is first created.
     * 首次建立
     */
    @Override
    public void onCreate(Bundle icicle) {
        try {
            this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        }
        catch (Exception err) {
            QuMediaLogger.error(TAG, err);
        }
        super.onCreate(icicle);
        Bundle bundle = this.getIntent().getExtras();
        requestUrl=bundle.getString(REQUEST_URL_BUNDLE_KEY);
        displayTutorial();
        setListeners();
    }



    private void displayTutorial() {
        btnBack = (TextView)findViewById(R.id.btnBack);
        WebView wv = (WebView) findViewById(R.id.webdisplayview);
        wv.getSettings().setJavaScriptEnabled(true);
        CustomerAndroidUtils.startWebViewLoad(wv, this, requestUrl,
                this.getString(R.string.business_common_dataTitle), this.getString(R.string.business_common_webLoading),
                this.getString(R.string.webconnect_error));
    }

    /**
     * 設定Listener
     */
    private void setListeners() {
        if(btnBack!=null){
            btnBack.setOnClickListener(onBackLastPage);
        }
    }

    private View.OnClickListener onBackLastPage = new View.OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };
}
