/*
 * ===========================================================================
 * QuMedia Confidential
 *
 * (C) Copyright QuMedia Corp. 2012
 * ===========================================================================
 */

package com.example.user.buttomnavigation_test;

import com.qumedia.android.core.common.BaseMobileResponseVO;
import com.qumedia.android.core.common.QuMediaLogger;
import com.qumedia.android.core.util.XmlBeanUtils;

import org.w3c.dom.Document;

/**
 * <p>ViartrilMobileResponseVO
 * <p/>
 * </p>
 *
 * @author brian
 * @version 2012/5/14下午 10:56
 * @see
 */
public class ViartrilMobileResponseVO extends BaseMobileResponseVO {

    public ViartrilMobileResponseVO(Document xmlDoc) {
        try {
            this.setStatusCode(XmlBeanUtils.getElement(xmlDoc,"return","statusCode"));
            this.setStatusDesc(XmlBeanUtils.getElement(xmlDoc,"return","statusDesc"));
            QuMediaLogger.debug("ViartrilMobileResponseVO", "statusCode=[" + this.getStatusCode() + "]");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
