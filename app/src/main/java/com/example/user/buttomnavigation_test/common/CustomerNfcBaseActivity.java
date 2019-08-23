/*
 * ===========================================================================
 * QuMedia Confidential
 *
 * (C) Copyright QuMedia Corp. 2010
 * ===========================================================================
 */
package com.example.user.buttomnavigation_test.common;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.qumedia.android.core.common.QuMediaLogger;
import com.qumedia.android.core.util.AndroidContextUtils;


/**
 * <p>CustomerNfcBaseActivity
 * <p/>
 * </p>
 *
 * @author Brian Liao
 * @version 下午 10:07:07 2010/2/2
 * @see
 */
public abstract class CustomerNfcBaseActivity extends CustomerBaseActivity {

    private static final int mainLayoutId = R.id.mainLinearLayout;

    private static final String TAG = CustomerNfcBaseActivity.class.getName();

    protected CustomerNfcBaseActivity() {
        super(R.layout.qumedia_base_list,true,true);
    }

    protected CustomerNfcBaseActivity(int layoutResource) {
        super(layoutResource,true,true);
    }

    protected CustomerNfcBaseActivity(int layoutResourceId, boolean hasTitle, boolean hasListView) {
        super(layoutResourceId,hasTitle,hasListView);
    }


    protected CustomerNfcBaseActivity(int layoutResource, boolean title) {
        super(layoutResource,true,false);
    }

    /**
     * 支援前景讀取
     */
    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;


    protected ListView listView = null;

    /**
     * Called when the activity is first created.
     * 首次建立
     */
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);


        listView = (ListView) findViewById(R.id.commonList);

        mAdapter = NfcAdapter.getDefaultAdapter(this);

        if (!mAdapter.isEnabled())
        {
            AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
            aBuilder.setTitle(this.getString(R.string.business_common_message))
                    .setMessage(this.getString(R.string.business_nfc_disable))
                            //正向按鈕
                    .setPositiveButton(AndroidContextUtils.getInstance().getBundleString(R.string.common_button_confirm),
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialoginterface, int i) {
                                    startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                                }
                            })
                    .show();
        }

        // Create a generic PendingIntent that will be deliver to this activity. The NFC stack
        // will fill in the intent with the details of the discovered tag before delivering to
        // this activity.
        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        IntentFilter tag = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        try {
            ndef.addDataType("text/plain");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }

        // Setup an intent filter for all MIME based dispatches
        IntentFilter tech = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        mFilters = new IntentFilter[] {
                tag,ndef,tech
        };
        // Setup a tech list for all NfcF tags
        mTechLists = new String[][] { new String[] {IsoDep.class.getName(),
                NfcA.class.getName(),
                NfcB.class.getName(),
                NfcF.class.getName(),
                NfcV.class.getName(),
                Ndef.class.getName(),
                NdefFormatable.class.getName(),
                MifareClassic.class.getName(),
                MifareUltralight.class.getName()} };
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    protected void setListAdapter(ListAdapter listAdapter) {
        listView.setAdapter(listAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mAdapter!=null){
            QuMediaLogger.debug(TAG, ".......................onResume.enableForegroundDispatch.....................");
            mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters, mTechLists);
        }
    }

    @Override
    public void onPause() {
        if(mAdapter!=null){
            mAdapter.disableForegroundDispatch(this);
        }
        super.onPause();
    }

       /**
     * onLoginSubmit
     */
    protected View.OnClickListener onPrePage = new View.OnClickListener() {
        public void onClick(View v) {
            CustomerNfcBaseActivity.this.finish();
        }
    };


    protected abstract boolean resolveIntent(Intent intent);

    //當夜讀取的時候收到的在這一頁的時候
    protected void onNewIntent(Intent intent)
    {
        QuMediaLogger.debug(TAG,".......................onNewIntent..................");
        setIntent(intent);
        resolveIntent(intent);
    }
}
