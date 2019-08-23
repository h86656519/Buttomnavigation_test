/*
 *  ===========================================================================
 *  * QuMedia Confidential
 *  *
 *  * (C) Copyright QuMedia Corp. 2014.
 *  * ===========================================================================
 */

package com.example.user.buttomnavigation_test.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.buttomnavigation_test.AlertDialogFragment;
import com.example.user.buttomnavigation_test.ViartrilMobileResponseVO;
import com.example.user.buttomnavigation_test.action.HttpCallBackProcessAction;
import com.example.user.buttomnavigation_test.common.CustomerConfigData;
import com.example.user.buttomnavigation_test.common.CustomerNfcBaseActivity;
import com.qumedia.android.core.common.QuMediaLogger;
import com.qumedia.android.core.util.StringUtils;
//import com.qumedia.android.viartril.customer.action.HttpCallBackProcessAction;
//import com.qumedia.android.viartril.customer.common.CustomerAndroidUtils;
//import com.qumedia.android.viartril.customer.common.CustomerConfigData;
//import com.qumedia.android.viartril.customer.common.CustomerNfcBaseActivity;
//import com.qumedia.android.viartril.customer.common.ViartrilMobileResponseVO;

import java.io.Serializable;


/**
 * ProductVerifyActivity
 * <p/>
 * 驗證
 */
public class ProductVerifyActivity extends CustomerNfcBaseActivity {

    private static final String TAG = ProductVerifyActivity.class.getName();

    /**
     *
     */
    private TextView btnMobileMain;

    /**
     * nfc 讀取到的值
     */
    private String scanValue;

    private int recordAction = -1;

    private int scanCount = 0;

    private LinearLayout layout_product1;

    private LinearLayout layout_product2;

    private TextView btnProduct1;

    private TextView btnProduct2;

    /**
     * 1  :  膠囊
     * 2  :  粉劑
     */
    private int btnSelectedType = 1;


    public ProductVerifyActivity() {
        super(R.layout.product_verify, true, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QuMediaLogger.debug(TAG, ".......onCreate...ProductVerifyActivity............");
        if (!resolveIntent(getIntent())) {
            initValues();
        }
    }


    /**
     * 初始化 member values
     */
    public void initValues() {
        if (btnMobileMain == null) {
            findViews();
//            setListeners();
            onValueScaned(scanValue);
        } else {
            onValueScaned(scanValue);
        }
    }


    /**
     * 設定 Layout
     */
    private void findViews() {
        btnProduct1 = (TextView) findViewById(R.id.btnProduct1);
        btnProduct2 = (TextView) findViewById(R.id.btnProduct2);
        layout_product1 = (LinearLayout) findViewById(R.id.layout_product1);
        layout_product2 = (LinearLayout) findViewById(R.id.layout_product2);
        layout_product2.setVisibility(View.GONE);

        //預設選擇
        btnSelectedType = 1;
        btnProduct1.setSelected(true);
        btnProduct2.setSelected(false);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mtoolbar_title = (TextView) findViewById(R.id.toolbar_title);
        mtoolbar_title.setText(R.string.vertifyproduct);

        new AppCompatActivity().setSupportActionBar(mToolbar);
    }


//    /**
//     * setListeners
//     */
//    private void setListeners() {
//        btnProduct1.setOnClickListener(onChange);
//        btnProduct2.setOnClickListener(onChange);
//    }


    @Override
    public void doHttpCallBack(Serializable doAction) {
        super.doHttpCallBack(doAction);
        ViartrilMobileResponseVO reponseVO = (ViartrilMobileResponseVO) doAction;
        if (reponseVO.isSucess()) {
//            goSuccessPage();
            AlertDialogFragment dialogFragment = new AlertDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "Dialog");


        } else if (reponseVO.getStatusCode().equals("8055")) {
//            goFailPage();
        } else {
            showErrorDialog(reponseVO.getStatusDesc());
        }

    }

    /**
     * 更新 Activity
     */
    public void onValueScaned(String tagName) {
        cloasDialog();
        if (!StringUtils.isEmpty(tagName)) {

            StringBuilder requestUrl = new StringBuilder(CustomerConfigData.VIARTRIL_WEB_SERVICE_URL);
            requestUrl.append(CustomerConfigData.DO_CHECK_TAG);
            requestUrl.append("tokenKey=").append(tagName);
            startHttpRequestDiaLog(requestUrl
                            .toString(), HttpCallBackProcessAction.class, null, 0,
                    CustomerConfigData.ACTION_TAB_1
            );
//            scanCount++;
//            HashMap<String,Serializable> parameterMap=new HashMap<String, Serializable>();
//            if(scanCount%2==0){
//                parameterMap.put(ProductVerifyResultActivity.VERIFY_RESULT_BUNDLE_KEY,"Y");
//            }else{
//                parameterMap.put(ProductVerifyResultActivity.VERIFY_RESULT_BUNDLE_KEY,"N");
//            }
//            CustomerAndroidUtils.startActivity(ProductVerifyActivity.this,
//                    ProductVerifyResultActivity.class,parameterMap,0,CustomerConfigData.ACTION_TAB_1);
        }
    }

//    private void goSuccessPage() {
//        HashMap<String, Serializable> parameterMap = new HashMap<String, Serializable>();
//        parameterMap.put(ProductVerifyResultActivity.VERIFY_RESULT_BUNDLE_KEY, "Y");
//
//        CustomerAndroidUtils.startActivity(ProductVerifyActivity.this,
//                ProductVerifyResultActivity.class, parameterMap, 0, CustomerConfigData.ACTION_TAB_1);
//    }
//
//    private void goFailPage() {
//        HashMap<String, Serializable> parameterMap = new HashMap<String, Serializable>();
//        parameterMap.put(ProductVerifyResultActivity.VERIFY_RESULT_BUNDLE_KEY, "N");
//
//        CustomerAndroidUtils.startActivity(ProductVerifyActivity.this,
//                ProductVerifyResultActivity.class, parameterMap, 0, CustomerConfigData.ACTION_TAB_1);
//    }


    protected boolean resolveIntent(Intent intent) {
        scanValue = "";
        // Parse the intent
        String action = intent.getAction();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction()) ||
                NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction()) ||
                NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
            byte[] byte_id = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
            String cardNumber = StringUtils.convertByteArrayToHexString(byte_id);
            scanValue = cardNumber;
            //Tag 就是讀到的 RFID Block 0 的 ID 碼
            QuMediaLogger.debug(TAG, "scanValue=[" + scanValue + "] ");
            initValues();
            return true;
        }
        return false;
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
            if (v.getId() == R.id.btnProduct1) {
                btnSelectedType = 1;

            } else if (v.getId() == R.id.btnProduct2) {
                btnSelectedType = 2;

            }
            doSetSelectedItem();

        }
    };

    @Override
    public void onResume() {
        super.onResume();
        doSetSelectedItem();
    }

    public class AlertDialogYesNolistener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case DialogInterface.BUTTON_POSITIVE:
                    //   m_tv_message.setText("thanks"); m_tv_message 的layout ???
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    // m_tv_message.setText("狗腿");
                    break;
            }
        }
    }

}
