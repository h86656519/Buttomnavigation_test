package com.example.user.buttomnavigation_test;

import com.qumedia.android.core.activity.QuMediaBaseActivity;

import java.io.Serializable;

public class BaseReponseVO implements Serializable {
    private QuMediaBaseActivity activeActivity;
    private int addFlags;
    private String doAction;

    public BaseReponseVO(QuMediaBaseActivity aQuMediaBaseActivity, int addFlags, String doAction) {
        this.setActiveActivity(aQuMediaBaseActivity);
        this.setAddFlags(addFlags);
        this.setDoAction(doAction);
    }

    public QuMediaBaseActivity getActiveActivity() {
        return this.activeActivity;
    }

    public void setActiveActivity(QuMediaBaseActivity activeActivity) {
        this.activeActivity = activeActivity;
    }

    public int getAddFlags() {
        return this.addFlags;
    }

    public void setAddFlags(int addFlags) {
        this.addFlags = addFlags;
    }

    public String getDoAction() {
        return this.doAction;
    }

    public void setDoAction(String doAction) {
        this.doAction = doAction;
    }
}

