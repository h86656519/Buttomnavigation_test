/*
 *  ===========================================================================
 *  * QuMedia Confidential
 *  *
 *  * (C) Copyright QuMedia Corp. 2014.
 *  * ===========================================================================
 */
package com.example.user.buttomnavigation_test.action;

import com.example.user.buttomnavigation_test.ViartrilMobileResponseVO;
import com.qumedia.android.core.common.BaseReponseVO;
import com.qumedia.android.core.common.HttpDataHandler;
import com.qumedia.android.core.util.XmlBeanUtils;

import org.w3c.dom.Document;

import java.io.DataInputStream;

/**
 * <p>HttpCallBackProcessAction
 * <p/>
 * </p>
 *
 * @author Brian Liao
 * @version 上午 10:52:32 2010/1/22
 * @see
 */
public class HttpCallBackProcessAction implements HttpDataHandler {

    public HttpCallBackProcessAction() {

    }

    public void setData(Object input, BaseReponseVO responseHeader) throws Exception {
        Document xmlDoc=XmlBeanUtils.parseXmlData((DataInputStream)input);
        ViartrilMobileResponseVO reponseVO = new ViartrilMobileResponseVO(xmlDoc);
        responseHeader.getActiveActivity().doHttpCallBack(reponseVO);
    }
}
