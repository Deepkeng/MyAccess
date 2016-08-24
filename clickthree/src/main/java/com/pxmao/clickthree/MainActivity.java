package com.pxmao.clickthree;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Dialog mTipsDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService(View view){
        showOpenAccessibilityServiceDialog();
    }


    /** 显示未开启辅助服务的对话框*/
    private void showOpenAccessibilityServiceDialog() {
        if(mTipsDialog != null && mTipsDialog.isShowing()) {
            return;
        }
//        View view = getLayoutInflater().inflate(R.layout.dialog_tips_layout, null);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openAccessibilityServiceSettings();
//            }
//        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("需要开启辅助服务正常使用");
//        builder.setView(view);
        builder.setPositiveButton("打开辅助服务", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openAccessibilityServiceSettings();
            }
        });
        mTipsDialog = builder.show();
    }

    /** 打开辅助服务的设置*/
    private void openAccessibilityServiceSettings() {
        try {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
            Toast.makeText(this, "找[ClickThree],然后开启服务", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
