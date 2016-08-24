package com.example.c.myapplication;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

//    private TextView tv;
//    private Button bt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        bt = (Button) findViewById(R.id.bt);
//        tv = (TextView) findViewById(R.id.tv1);
        upgradeRootPermission(getPackageCodePath());

    }

    /**
     * 获取IMEI
     */
    public void getIMEI(View view){
        String imei = null;
        imei = ((TelephonyManager) this.getSystemService(TELEPHONY_SERVICE)).getDeviceId();
        System.out.println(imei);
        Toast.makeText(MainActivity.this,"IMEI:"+ imei, Toast.LENGTH_LONG).show();
    }

    /**
     * 获取Value值
     */
    public void getValue(View view){
        String value = null;
        value = getValue();
        Toast.makeText(MainActivity.this,"Value:"+ value, Toast.LENGTH_LONG).show();
    }

    /**
     * 获取控件
     */
    public void accessibility(){


    }
    /**
     * 获取su权限
     */
    public static boolean upgradeRootPermission(String pkgCodePath) {
        Process process = null;
        DataOutputStream os = null;
        try {
            String cmd="chmod 777 " + pkgCodePath;
            System.out.println(pkgCodePath);
            process = Runtime.getRuntime().exec("su"); //切换到root帐号
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
           // os.writeBytes("cd /data/data/com.tencent.mm/shared_prefs/");
            os.writeBytes("cat /data/data/com.tencent.mm/shared_prefs/system_config_prefs.xml >/sdcard/system_config_prefs.xml\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }
        return true;
    }

    /**
     * 解析XML,取到Value
     */
    public String getValue(){
        String  value = null;
        InputStream inputStream = null;
        try {
            // File file = new File("/sdcard/system_config_prefs.xml");
            File file = new File("/data/data/com.tencent.mm/shared_prefs/system_config_prefs.xml");

            inputStream = new FileInputStream(file);
            //获取工厂对象，以及通过DOM工厂对象获取DOMBuilder对象
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            //解析XML输入流，得到Document对象，表示一个XML文档
            Document document = builder.parse(inputStream);
            //获得文档中的次以及节点
            Element element = document.getDocumentElement();
            NodeList personNodes = element.getElementsByTagName("int");
            for (int i = 0; i < personNodes.getLength(); i++) {
                Element personElement = (Element) personNodes.item(i);
                String name = personElement.getAttribute("name");
                value = personElement.getAttribute("value");
                System.out.println(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (Exception e){

            }
        }
        return value;
    }



    //通过文本查找控件
    public static AccessibilityNodeInfo findNodeInfosByText(AccessibilityNodeInfo nodeInfo, String text) {
        List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(text);
        if(list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

}
