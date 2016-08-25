package com.pxmao.clickthree.Utils;

import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by psq on 2016/8/25
 */
public class WeiXinUtils {

    /**
     * 获取微信当前聊天对象的名字
     *
     * @param rowNode 当前窗口跟节点getRootInActiveWindow()
     * @return 返回微信当前聊天对象名字
     */
    public static String getCurrentCommunicateName(AccessibilityNodeInfo rowNode) {
        CharSequence text;
        if (rowNode != null) {
            List<AccessibilityNodeInfo> list = rowNode.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/ey");
            if (list != null && !list.isEmpty()) {
                text = list.get(0).getText();
                return text.toString();
            }
        }
        return null;

    }




}
