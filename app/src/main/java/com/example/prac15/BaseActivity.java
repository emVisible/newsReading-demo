package com.example.prac15;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BaseActivity extends AppCompatActivity {
    private ForceOfflineReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.prac15.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class ForceOfflineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle("系统提示");
            dialog.setCancelable(false);
            dialog.setMessage("帐号状态已变更, 请重新登录");
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll();
                    Intent intent = new Intent(context, Login.class);
                    context.startActivity(intent);
                }
            });
            dialog.show();
        }
    }
}