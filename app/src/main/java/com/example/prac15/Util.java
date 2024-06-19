package com.example.prac15;

import android.content.Intent;

public class Util extends BaseActivity{
    public void logout(){
        Intent intent = new Intent("com.example.prac15.FORCE_OFFLINE");
        sendBroadcast(intent);
    }

    public void route(){
        Intent intent = new Intent();
    }
}
