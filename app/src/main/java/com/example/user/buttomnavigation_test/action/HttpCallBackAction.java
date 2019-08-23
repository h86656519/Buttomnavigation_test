/*
 * ===========================================================================
 * QuMedia Confidential
 *
 * (C) Copyright QuMedia Corp. 2010
 * ===========================================================================
 */
package com.example.user.buttomnavigation_test.action;

import com.example.user.buttomnavigation_test.ViartrilMobileResponseVO;
import com.qumedia.android.core.common.BaseReponseVO;
import com.qumedia.android.core.common.HttpDataHandler;
import com.qumedia.android.core.util.XmlBeanUtils;
//import com.qumedia.android.viartril.customer.common.ViartrilMobileResponseVO;

import org.w3c.dom.Document;

import java.io.DataInputStream;

/**
 * <p>CouponDeleteAction
 * <p/>
 * </p>
 *
 * @author Brian Liao
 * @version 上午 10:52:32 2010/1/22
 * @see
 */
public class HttpCallBackAction implements HttpDataHandler {

    public HttpCallBackAction() {

    }

    public void setData(Object input, BaseReponseVO responseHeader) throws Exception {
        Document xmlDoc=XmlBeanUtils.parseXmlData((DataInputStream)input);
        ViartrilMobileResponseVO reponseVO = new ViartrilMobileResponseVO(xmlDoc);
        if (reponseVO.isSucess()) {
            //回覆成功 呼叫原頁的 CallBack Method
            responseHeader.getActiveActivity().doHttpCallBack(reponseVO.getStatusDesc());
        } else {
            responseHeader.getActiveActivity().showErrorDialog(reponseVO.getStatusDesc());
        }
    }
}
