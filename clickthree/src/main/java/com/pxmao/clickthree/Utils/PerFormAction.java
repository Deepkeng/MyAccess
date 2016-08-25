package com.pxmao.clickthree.Utils;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Created by psq on 2016/8/25
 */
public class PerFormAction {

    public PerFormAction(){}

    //通过父ID和index执行点击
    public void performClickByIdByFather(AccessibilityNodeInfo rowNode, int index){
        Log.i("MyService","通过父ID执行点击");
        AccessibilityNodeInfo targetNode = null;
        targetNode = FindNodeUtils.findBottomNodeByIndex(rowNode,index);
        if (targetNode.isClickable()) {
            targetNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }
    }
}
