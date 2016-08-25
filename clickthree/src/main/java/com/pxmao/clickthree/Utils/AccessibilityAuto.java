package com.pxmao.clickthree.Utils;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by psq on 2016/8/25
 */
public class AccessibilityAuto extends AccessibilityService{
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {


    }

    @Override
    public void onInterrupt() {

        AccessibilityServiceInfo info = getServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
        info.notificationTimeout = 100;
        setServiceInfo(info);
        info.packageNames = new String[]{"com.tencent.mm"};
        setServiceInfo(info);
    }
}
