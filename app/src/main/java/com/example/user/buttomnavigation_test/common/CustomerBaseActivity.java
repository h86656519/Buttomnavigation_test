/*
* ===========================================================================
* QuMedia Confidential
*
* (C) Copyright QuMedia Corp. 2010
* ===========================================================================
*/

package com.example.user.buttomnavigation_test.common;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.user.buttomnavigation_test.activity.ProductInfoActivity;
import com.example.user.buttomnavigation_test.activity.ProductUseActivity;
import com.example.user.buttomnavigation_test.activity.ProductVerifyActivity;
import com.example.user.buttomnavigation_test.activity.WebActivity;
import com.qumedia.android.core.activity.QuMediaBaseActivity;
import com.qumedia.android.core.common.QuMediaLogger;
import com.qumedia.android.core.util.AndroidContextUtils;
//暫時不要 leo
//import com.qumedia.android.viartril.customer.activity.ProductInfoActivity;
//import com.qumedia.android.viartril.customer.activity.ProductUseActivity;
//import com.qumedia.android.viartril.customer.activity.WebActivity;

import java.io.Serializable;
import java.util.HashMap;


/**
 * <p>CustomerBaseActivity
 * <p/>
 * </p>
 *
 * @author Brian Liao
 * @version 下午 10:06:44 2010/2/2
 * @see
 */
public class CustomerBaseActivity extends QuMediaBaseActivity {

    private static final String TAG = CustomerBaseActivity.class.getName();

    public static final int mainLayoutId = R.id.mainLinearLayout;

    public static int titleId = R.id.mainTitle;

    public static int contentId = R.id.mainContent;

    //public static final int BUTTON_BAR_BACKGROUND = Color.parseColor("#f97101");

    //public static final int HIGH_LIGHT_COLOR = Color.parseColor("#381a00");

    public static final int BUTTON_BAR_BACKGROUND = Color.parseColor("#000000");

    public static final int HIGH_LIGHT_COLOR = Color.parseColor("#000000");

    public static final int HIGH_LIGHT_TEXT_COLOR = Color.WHITE;

    //public static final int DEFAULT_COLOR = Color.TRANSPARENT;
    public static final int DEFAULT_COLOR = Color.BLACK;

    public static final int DEFAULT_TEXT_COLOR = Color.parseColor("#4c1a03");

    public static final int DEFAULT_BLUE_COLOR = Color.parseColor("#0000ff");

    /**
     * Tab 按鈕
     */
    private ImageButton tab1Btn = null;

    private ImageButton tab2Btn = null;

    private ImageButton tab3Btn = null;

    private ImageButton tab4Btn = null;

    /**
     * layoutResource
     */
    //protected int layoutResource = R.layout.main;
    protected int layoutResource = 0;

    private boolean hasListView = false;

    private boolean hasTitle = false;


    /**
     * mainLayout
     */
    protected ViewGroup mainLayout = null;

    protected CustomerBaseActivity(int layoutResourceId, boolean hasTitle, boolean hasListView) {
        super();
        this.layoutResource = layoutResourceId;
        this.hasListView = hasListView;
        this.hasTitle = hasTitle;
    }

    protected CustomerBaseActivity(int layoutResourceId, boolean hasTitle) {
        super();
        this.layoutResource = layoutResourceId;
        this.hasListView = false;
        this.hasTitle = hasTitle;
    }

    protected CustomerBaseActivity(int layoutResourceId) {
        super();
        this.layoutResource = layoutResourceId;
        this.hasListView = false;
        this.hasTitle = false;
    }

    protected CustomerBaseActivity() {
        this.hasListView = false;
        this.hasTitle = false;
    }


    /**
     * getDisplayWidth
     *
     * @return
     */
    protected int getDisplayWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    /**
     * getDisplayWidth
     *
     * @return
     */
    protected int getDisplayHeight() {
//        Rect rectangle= new Rect();
//        Window window= getWindow();
//        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
//        int statusBarHeight= rectangle.top;
//        int contentViewTop= window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
//        int titleBarHeight= contentViewTop - statusBarHeight;
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //Log.i("*** getDisplayHeight :: ", "StatusBar Height= " + statusBarHeight + " , TitleBar Height = " + titleBarHeight+"rectangle=["+rectangle.height()+"] metrics.heightPixels=["+metrics.heightPixels+"]");
        return metrics.heightPixels;
    }



    /**
     * 取得ScrollView 的高度
     *
     * @return
     */
    protected int getNoTitleHeight() {
        return getDisplayHeight() - getButtonBarHeight();
    }


    protected int getButtonBarWidth() {
        return getDisplayWidth() / 4;
    }

    /**
     * 用 16:9 計算
     * @return
     */
    protected int getButtonBarHeight() {
        return getButtonBarWidth()*9/16;
    }




    /**
     * Called when the activity is first created.
     * 首次建立
     */
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //0 不使用底層
        if (layoutResource != 0) {
            this.setContentView(layoutResource);
            mainLayout = (ViewGroup) findViewById(mainLayoutId);
            //設定表頭高度
            if (hasTitle) {
                ViewGroup view=((ViewGroup) findViewById(titleId));
               // ViewGroup mainContent= (ViewGroup) findViewById(contentId);
                if(view.getLayoutParams()instanceof RelativeLayout.LayoutParams){
                    view.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,this.getTitleBarHeight()));
                  //  mainContent.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,this.getNoTitleHeight()-this.getTitleBarHeight()));
                }else if(view.getLayoutParams()instanceof LinearLayout.LayoutParams){
                    view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, this.getTitleBarHeight()));
                  //  mainContent.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, this.getNoTitleHeight()-this.getTitleBarHeight()));
                }else if(view.getLayoutParams()instanceof FrameLayout.LayoutParams){
                    view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, this.getTitleBarHeight()));
                 //   mainContent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, this.getNoTitleHeight()-this.getTitleBarHeight()));
                }
            }else{
//                ViewGroup mainContent= (ViewGroup) findViewById(contentId);
//                if(mainContent.getLayoutParams()instanceof RelativeLayout.LayoutParams){
//                    mainContent.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,this.getNoTitleHeight()));
//                }else if(mainContent.getLayoutParams()instanceof LinearLayout.LayoutParams){
//                    mainContent.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, this.getNoTitleHeight()));
//                }else if(mainContent.getLayoutParams()instanceof FrameLayout.LayoutParams){
//                    mainContent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, this.getNoTitleHeight()));
//                }
            }
            LinearLayout buttonTopBar = new LinearLayout(this);
            buttonTopBar.setOrientation(LinearLayout.VERTICAL);
            //if (hasListView) {
            buttonTopBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            } else {
//                buttonTopBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
//            }

            //buttonTopBar.setBackgroundColor(Color.DKGRAY);
            buttonTopBar.setBackgroundColor(Color.TRANSPARENT);

            LinearLayout buttonBar = new LinearLayout(this);
            buttonBar.setOrientation(LinearLayout.HORIZONTAL);
            buttonBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            //android:background="#ffff9900"
            buttonBar.setBackgroundColor(BUTTON_BAR_BACKGROUND);
            buttonTopBar.addView(buttonBar);

            tab1Btn = new ImageButton(this);
            tab1Btn.setLayoutParams(new LinearLayout.LayoutParams(getButtonBarWidth(), getButtonBarHeight()));
            tab1Btn.setImageResource(R.drawable.tab1_icon);
            tab1Btn.setAdjustViewBounds(true);
            tab1Btn.setPadding(0, 0, 0, 0);
            tab1Btn.setBackgroundColor(DEFAULT_COLOR);
            buttonBar.addView(tab1Btn);

            tab2Btn = new ImageButton(this);
            tab2Btn.setLayoutParams(new LinearLayout.LayoutParams(getButtonBarWidth(), getButtonBarHeight()));
            tab2Btn.setImageResource(R.drawable.tab2_icon);
            tab2Btn.setAdjustViewBounds(true);
            tab2Btn.setPadding(0, 0, 0, 0);
            tab2Btn.setBackgroundColor(DEFAULT_COLOR);
            buttonBar.addView(tab2Btn);

            tab3Btn = new ImageButton(this);
            tab3Btn.setLayoutParams(new LinearLayout.LayoutParams(getButtonBarWidth(), getButtonBarHeight()));
            tab3Btn.setImageResource(R.drawable.tab3_icon);
            tab3Btn.setAdjustViewBounds(true);
            tab3Btn.setPadding(0, 0, 0, 0);
            tab3Btn.setBackgroundColor(DEFAULT_COLOR);
            buttonBar.addView(tab3Btn);

            tab4Btn = new ImageButton(this);
            tab4Btn.setLayoutParams(new LinearLayout.LayoutParams(getButtonBarWidth(), getButtonBarHeight()));
            tab4Btn.setImageResource(R.drawable.tab4_icon);
            tab4Btn.setAdjustViewBounds(true);
            tab4Btn.setPadding(0, 0, 0, 0);
            tab4Btn.setBackgroundColor(DEFAULT_COLOR);
            buttonBar.addView(tab4Btn);

            setListeners();
            decideTabAction();



            //QuMediaLogger.debug(TAG,"mainLayout.getChildCount()=["+mainLayout.getChildCount()+"]");
            //把按鈕列加進去 Main
            //mainLayout.addView(buttonTopBar,mainLayout.getChildCount());
            mainLayout.addView(buttonTopBar,0);
        }
    }

    /**
     * Called when the activity is first created.
     * 首次建立
     */
    protected void onResume() {
        super.onResume();
    }



    /**
     *
     *
     * @param message
     */
    public void showMessageDialog(String message) {
        cloasDialog();
        CustomerAndroidUtils.openAlertDialog(this,
                this.getString(R.string.business_common_message), message, false,0);
    }

    /**
     *
     *
     * @param message
     */
    public void showFinishMessageDialog(String message) {
        cloasDialog();
        CustomerAndroidUtils.openAlertDialog(this,
                this.getString(R.string.business_common_message), message, true,0);
    }


    /**
     *
     *
     * @param message
     */
    public void showFinishResultMessageDialog(String message, int resultCode) {
        cloasDialog();
        CustomerAndroidUtils.openAlertDialog(this,
                this.getString(R.string.business_common_message), message, true,resultCode);
    }


    /**
     * showErrorDialog
     *
     * @param message
     */
    public void showErrorDialog(String message) {
        cloasDialog();
        CustomerAndroidUtils.openAlertDialog(this,
                this.getString(R.string.business_common_error), message, false,0);
    }

    /**
     * showFinishErrorDialog
     *
     * @param message
     */
    public void showFinishErrorDialog(String message) {
        cloasDialog();
        CustomerAndroidUtils.openAlertDialog(this,
                this.getString(R.string.business_common_error), message, true,0);
    }

    @Override
    public void showProcessDialog() {
        if (mProgress == null) {
            mProgress = new ProgressDialog(this);
            mProgress.setTitle(AndroidContextUtils.getInstance().getBundleString(R.string.business_common_dataTitle));
            mProgress.setMessage(AndroidContextUtils.getInstance().getBundleString(R.string.business_common_dataDownLoad));
            mProgress.setIndeterminate(false);
            mProgress.setMax(100);
            mProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgress.setCancelable(false);
            mProgress.show();
        } else {
            mProgress.show();
        }
    }

    /**
     * 判斷選取哪一個 Tab
     */
    private void decideTabAction() {
        QuMediaLogger.debug(TAG, "doAction=" + doAction);

        if (CustomerConfigData.ACTION_TAB_1.equals(doAction)) {
            tab1Btn.setImageResource(R.drawable.tab1_icon_w);
            tab1Btn.setBackgroundColor(DEFAULT_COLOR);
        } else {
            tab1Btn.setImageResource(R.drawable.tab1_icon);
            tab1Btn.setBackgroundColor(DEFAULT_COLOR);
        }

        if (CustomerConfigData.ACTION_TAB_2.equals(doAction)) {
            tab2Btn.setImageResource(R.drawable.tab2_icon_w);
            tab2Btn.setBackgroundColor(DEFAULT_COLOR);
        } else {
            tab2Btn.setImageResource(R.drawable.tab2_icon);
            tab2Btn.setBackgroundColor(DEFAULT_COLOR);
        }

        if (CustomerConfigData.ACTION_TAB_3.equals(doAction)) {
            tab3Btn.setImageResource(R.drawable.tab3_icon_w);
            tab3Btn.setBackgroundColor(DEFAULT_COLOR);
        } else {
            tab3Btn.setImageResource(R.drawable.tab3_icon);
            tab3Btn.setBackgroundColor(DEFAULT_COLOR);
        }

        if (CustomerConfigData.ACTION_TAB_4.equals(doAction)) {
            tab4Btn.setImageResource(R.drawable.tab4_icon_w);
            tab4Btn.setBackgroundColor(DEFAULT_COLOR);
        } else {
            tab4Btn.setImageResource(R.drawable.tab4_icon);
            tab4Btn.setBackgroundColor(DEFAULT_COLOR);
        }

    }

    /**
     * setListeners
     */
    private void setListeners() {
        if (tab1Btn != null) {
            tab1Btn.setOnClickListener(onTab1Btn);
        }

        if (tab2Btn != null) {
            tab2Btn.setOnClickListener(onTab2Btn);
        }

        if (tab3Btn != null) {
            tab3Btn.setOnClickListener(onTab3Btn);
        }

        if (tab4Btn != null) {
            tab4Btn.setOnClickListener(onTab4Btn);
        }

    }


    /**
     *
     */
    private View.OnClickListener onTab1Btn = new View.OnClickListener() {
        public void onClick(View v) {
            CustomerAndroidUtils.startActivity(
                    CustomerBaseActivity.this,
                    ProductVerifyActivity.class,
                    null,
                    Intent.FLAG_ACTIVITY_CLEAR_TOP,
                    CustomerConfigData.ACTION_TAB_1);
        }
    };



    /**
     *
     */
    private View.OnClickListener onTab2Btn = new View.OnClickListener() {
        public void onClick(View v) {
                CustomerAndroidUtils.startActivity(
                        CustomerBaseActivity.this,
                        ProductInfoActivity.class,
                        null,
                        Intent.FLAG_ACTIVITY_CLEAR_TOP,
                        CustomerConfigData.ACTION_TAB_2);
        }
    };

    /**
     *
     */
    private View.OnClickListener onTab3Btn = new View.OnClickListener() {
        public void onClick(View v) {
            CustomerAndroidUtils.startActivity(
                    CustomerBaseActivity.this,
                    ProductUseActivity.class,
                    null,
                    Intent.FLAG_ACTIVITY_CLEAR_TOP,
                    CustomerConfigData.ACTION_TAB_3);
        }
    };


    /**
     * 行動首頁
     */
    private View.OnClickListener onTab4Btn = new View.OnClickListener() {
        public void onClick(View v) {
            HashMap<String, Serializable> parameterMap = new HashMap<String, Serializable>();
            parameterMap.put(WebActivity.REQUEST_URL_BUNDLE_KEY, CustomerConfigData.MOBILE_MAIN_URL);
            CustomerAndroidUtils.startActivity(
                    CustomerBaseActivity.this,
                    WebActivity.class,
                    parameterMap,
                    Intent.FLAG_ACTIVITY_CLEAR_TOP,
                    CustomerConfigData.ACTION_TAB_4);

        }
    };



}
