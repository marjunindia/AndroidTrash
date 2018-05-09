package com.example.arjun_mu.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        showmsg("onCreate");
    }


    @Override
    protected void onStart() {
        super.onStart();
        showmsg("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showmsg("onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        showmsg("onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        showmsg("onStop");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showmsg("onRestart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showmsg("onDestroy");
    }

    public void showmsg(String msg){
        Log.d("lifecycle"+TAG,msg);
    }
}
