package com.pxmao.clickthree.Utils;
import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by psq on 2016/8/25
 */
public class WeiXinUtils {

    /**
     * 获取微信当前聊天对象的名字
     * @param rowNode 当前窗口跟节点getRootInActiveWindow()
     * @return  返回微信当前聊天对象名字
     */
    public static String getCurrentCommunicateName(AccessibilityNodeInfo rowNode) {
        CharSequence text;
        if(rowNode != null){
            List<AccessibilityNodeInfo> list = rowNode.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/ey");
            if (list != null && !list.isEmpty()) {
                text = list.get(0).getText();
                return text.toString();
            }
        }
        return null;

    }


    /**
     *通过父id查找首页底部（微信，通讯录，发现，我）
     * @param rowNode 当前窗口跟节点
     * @param index 1微信  2通讯录  3发现  4我
     * @return 目标节点
     */
    public static AccessibilityNodeInfo findBottomNodeByIndex(AccessibilityNodeInfo rowNode, int index) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            List<AccessibilityNodeInfo> list = rowNode.findAccessibilityNodeInfosByViewId("android:id/content");
            if (list != null && !list.isEmpty()) {
                return list.get(0).getChild(0).getChild(0).getChild(1).getChild(0).getChild(index);
            }
        }
        return null;
    }


}
