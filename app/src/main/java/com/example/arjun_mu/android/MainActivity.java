package com.example.arjun_mu.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    public void movetoanother(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}
