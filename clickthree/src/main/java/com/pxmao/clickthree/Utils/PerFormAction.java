package com.pxmao.clickthree.Utils;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Created by psq on 2016/8/25
 */
public class PerFormAction {

    //执行点击
   public static void performClick(AccessibilityNodeInfo targetNode) {
        if (targetNode.isClickable()) {
            targetNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }
    }
}
