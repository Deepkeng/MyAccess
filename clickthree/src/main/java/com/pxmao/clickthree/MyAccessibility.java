package com.pxmao.clickthree;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by C on 2016/8/11.
 */
public class MyAccessibility extends AccessibilityService {
    private static final String TAG = "MyAccessibility";
    private String msg;
    private String className;

    @SuppressLint("NewApi")
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // TODO Auto-generated method stub

        int eventType = event.getEventType();

        String eventText = "";
//        Log.i(TAG, "==============Start====================");
        switch (eventType) {

            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                eventText = "TYPE_VIEW_LONG_CLICKED";
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                eventText = "TYPE_WINDOW_STATE_CHANGED";
                className = event.getClassName().toString();
                Log.i("MyAccessibility", className);

            /*    if (className.equals("android.widget.FrameLayout")) {
                    performClickById("com.tencent.mm:id/aes", 1);
                    Log.i("MyAccessibility", "添加朋友");
                }

                if (className.equals("com.tencent.mm.plugin.subapp.ui.pluginapp.AddMoreFriendsUI")) {
                    SystemClock.sleep(2000);
                    performClickByIdByFather1("android:id/list", 4);
                    Log.i("MyAccessibility", "雷达添加朋友");
                }
*/
                break;

            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                eventText = "TYPE_NOTIFICATION_STATE_CHANGED";
                filtMsg(event);

                break;


            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                eventText = "TYPE_VIEW_CLICKED";

                AccessibilityNodeInfo rowNode = getRootInActiveWindow();
                if (rowNode == null) {
                    Log.i(TAG, "noteInfo is　null");
                    return;
                } else {
                    recycle(rowNode);
                }

                List<AccessibilityNodeInfo> list = getRootInActiveWindow().findAccessibilityNodeInfosByViewId("com.tencent.mm:id/ey");
                if (list != null && !list.isEmpty()) {
                    CharSequence text = list.get(0).getText();
                    Log.i(TAG,"list"+text.toString());
                }



                /*AccessibilityNodeInfo info = getRootInActiveWindow();

                for (int i = 0; i < info.getChildCount(); i++) {
                    // info.getChild(i);
                    Log.i("MyAccessibility", info.getChild(i).toString());

                }*/
                break;

           /* case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                eventText = "TYPE_WINDOW_CONTENT_CHANGED";*/

            /*    //通过id查找
                public static AccessibilityNodeInfo findNodeInfosById(AccessibilityNodeInfo nodeInfo, String resId) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId(resId);
                    if (list != null && !list.isEmpty()) {
                        return list.get(0);
                    }
                }
                return null;
            }*/





                //List<AccessibilityNodeInfo> list = rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/ey");





               // Log.i("MyAccessibility",.toString());

                //  AccessibilityNodeInfo info = findNodeInfosById(this.getRootInActiveWindow(), "com.tencent.mm:id/ey");



              //  break;
        }


//        Log.i(TAG, eventText);
//        Log.i(TAG, "=============END=====================");


    }


    public void recycle(AccessibilityNodeInfo info) {
        if (info.getChildCount() == 0) {
            //  Log.i(TAG, "child widget----------------------------" + info.getClassName());
            // Log.i(TAG, "showDialog:" + info.canOpenPopup());
            if(info.getText()!= null){
                Log.i(TAG, "Text：" + info.getText());//获取控件节点文本

            }
            //  Log.i(TAG, "windowId:" + info.getWindowId());
        } else {
            for (int i = 0; i < info.getChildCount(); i++) {
                if(info.getChild(i)!=null){
                    recycle(info.getChild(i));
                }
            }
        }
    }


  /*  *//**
     * 打印一个节点的结构
     * @param info
     *//*
    @SuppressLint("NewApi")
    public void recycle(AccessibilityNodeInfo info) {
        if (info.getChildCount() == 0) {
            if(info.getText() != null){
                if("领取红包".equals(info.getText().toString())){
                    //这里有一个问题需要注意，就是需要找到一个可以点击的View
                    Log.i("demo", "Click"+",isClick:"+info.isClickable());
                    info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    AccessibilityNodeInfo parent = info.getParent();
                    while(parent != null){
                        Log.i("demo", "parent isClick:"+parent.isClickable());
                        if(parent.isClickable()){
                            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            break;
                        }
                        parent = parent.getParent();
                    }

                }
            }

        } else {
            for (int i = 0; i < info.getChildCount(); i++) {
                if(info.getChild(i)!=null){
                    recycle(info.getChild(i));

                }
            }
        }
    }*/



    /**
     * 通过消息内容过滤
     *
     * @param event
     */
    private String filtMsg(AccessibilityEvent event) {
        String content = "";
        String liaotian = "";
        List<CharSequence> texts = event.getText();
        if (!texts.isEmpty()) {
            for (CharSequence text : texts) {
                content = text.toString();//拿到通知栏的消息
                Log.i("MyAccessibility", "text:" + content);

              /*  //分割内容
                String s = new String(content);
                String a[] = s.split(":");
                liaotian = a[1];
              */
            }


               /* if (content.contains("密码")) {
                    //模拟打开通知栏消息
                    if (event.getParcelableData() != null
                            &&
                            event.getParcelableData() instanceof Notification) {
                        Notification notification = (Notification) event.getParcelableData();
                        PendingIntent pendingIntent = notification.contentIntent;
                        try {
                            pendingIntent.send();
                        } catch (PendingIntent.CanceledException e) {
                            e.printStackTrace();
                        }
                    }

                }*/


        }

        return liaotian;
    }

    /**
     * 点击语音按钮
     *
     * @param event
     */
    private void sousuo(AccessibilityEvent event) {
        String className = event.getClassName().toString();
        Log.i("className", className);
        if (className.equals("com.tencent.mm.plugin.search.ui.FTSMainUI")) {
            Log.i("语音输入", "1111111");
            SystemClock.sleep(2000);
            performClickByText("语音输入");
        }
    }


    @Override
    protected boolean onKeyEvent(KeyEvent event) {

        Log.i("MyService", "按钮点击变化");

        //接收按键事件
        return super.onKeyEvent(event);
    }


    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
/*    //代码配置服务监听信息
        AccessibilityServiceInfo info = getServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
        info.notificationTimeout = 100;
        setServiceInfo(info);
        info.packageNames = new String[]{"com.tencent.mm"};
        setServiceInfo(info);*/
    }

    //通过文字执行点击
    private void performClickByText(String text) {

        Log.i("MyService", "通过文字执行点击");

        AccessibilityNodeInfo nodeInfo = this.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        //通过名字获取
        //targetNode = findNodeInfosByText(nodeInfo,"广告");
        targetNode = findNodeInfosByText(nodeInfo, text);
        if (targetNode.isClickable()) {
            targetNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);

        }
    }


    //通过资源ID执行点击
    private void performClickById(String resId) {
        Log.i("MyService", "通过ID执行点击");
        AccessibilityNodeInfo nodeInfo = this.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        //通过资源ID点击
        targetNode = findNodeInfosById(nodeInfo, resId);
        if (targetNode.isClickable()) {
            targetNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }
    }

    //通过资源ID和index执行点击
    private void performClickById(String resId, int index) {
        Log.i("MyService", "通过ID执行点击");
        AccessibilityNodeInfo nodeInfo = this.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        //通过资源ID点击
        targetNode = findNodeInfosById(nodeInfo, resId, index);
        if (targetNode.isClickable()) {
            targetNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }
    }


    //通过父ID和index执行点击
    private void performClickByIdByFather(String resId, int index) {
        Log.i("MyService", "通过父ID执行点击");
        AccessibilityNodeInfo nodeInfo = this.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        //通过资源ID点击
        targetNode = findNodeInfosByIdByFather(nodeInfo, resId, index);
        if (targetNode.isClickable()) {
            targetNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }
    }


    //通过父ID和index执行点击
    private void performClickByIdByFather1(String resId, int index) {
        Log.i("MyService", "通过父ID执行点击");
        AccessibilityNodeInfo nodeInfo = this.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        //通过资源ID点击
        targetNode = findNodeInfosByIdByFather1(nodeInfo, resId, index);
        if (targetNode.isClickable()) {
            targetNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }
    }


    //通过资源ID获取EditText焦点
    private void performFocusById(String resId) {
        Log.i("MyService", "通过ID获取IDEditText焦点");
        AccessibilityNodeInfo nodeInfo = this.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        //通过资源ID点击
        targetNode = findNodeInfosById(nodeInfo, resId);
        if (targetNode.isClickable()) {
            targetNode.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
        }
    }


    /*//通过资源ID给EditText输入文字  只支持API21以上
        private void performSetTextById(String resId){
            Log.i("MyService","通过ID给EditText输入文字");
            AccessibilityNodeInfo nodeInfo = this.getRootInActiveWindow();
            AccessibilityNodeInfo targetNode = null;
            //通过资源ID点击
            targetNode = findNodeInfosById(nodeInfo,resId);
            if (targetNode.isClickable()) {
                Bundle args = new Bundle();
                args.putCharSequence(AccessibilityNodeInfo
                        .ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, "android");

                targetNode.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT,args);
            }
        }*/


    //通过资源ID执行长按
    private void performLongClickById(String resId) {
        Log.i("MyService", "通过ID执行长按");
        AccessibilityNodeInfo nodeInfo = this.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        //通过资源ID点击
        targetNode = findNodeInfosById(nodeInfo, resId);
        if (targetNode.isClickable()) {
            targetNode.performAction(AccessibilityNodeInfo.ACTION_LONG_CLICK);
        }
    }


    //通过文字执行长按
    private void performLongClick(String text) {

        Log.i("MyService", "执行长按");

        AccessibilityNodeInfo nodeInfo = this.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        //通过名字获取
        targetNode = findNodeInfosByText(nodeInfo, text);
        if (targetNode.isClickable()) {
            targetNode.performAction(AccessibilityNodeInfo.ACTION_LONG_CLICK);


        }
    }


    //通过文本查找
    public static AccessibilityNodeInfo findNodeInfosByText(AccessibilityNodeInfo nodeInfo, String text) {
        List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(text);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }


    //通过id查找
    public static AccessibilityNodeInfo findNodeInfosById(AccessibilityNodeInfo nodeInfo, String resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId(resId);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }


    //通过id查找右上角+号
    public static AccessibilityNodeInfo findNodeInfosById(AccessibilityNodeInfo nodeInfo, String resId, int index) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId(resId);
            if (list != null && !list.isEmpty()) {
                return list.get(index);
            }
        }
        return null;
    }


    //通过id查找-右上角+号-添加朋友-X
    public static AccessibilityNodeInfo findNodeInfosByIdByFather1(AccessibilityNodeInfo nodeInfo, String resId, int index) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId(resId);
            if (list != null && !list.isEmpty()) {
                return list.get(0).getChild(index);
            }
        }
        return null;
    }

    //通过父id查找首页底部（微信，通讯录，发现，我）
    public static AccessibilityNodeInfo findNodeInfosByIdByFather(AccessibilityNodeInfo nodeInfo, String resId, int index) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId(resId);
            if (list != null && !list.isEmpty()) {
                return list.get(0).getChild(0).getChild(0).getChild(1).getChild(0).getChild(index);
            }
        }
        return null;
    }


}
