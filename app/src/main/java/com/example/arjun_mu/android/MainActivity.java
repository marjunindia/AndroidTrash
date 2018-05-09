package com.example.arjun_mu.android;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "message";
    private static final int REQUEST_CODE = 1;
    EditText etname;
    TextView headtextview;
    TextView replytextview;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etname = (EditText) findViewById(R.id.editText);
        headtextview = (TextView) findViewById(R.id.textView);
        replytextview = (TextView) findViewById(R.id.textView2);
        showmsg("onCreate");


        if (savedInstanceState != null) {

            boolean isVisible = savedInstanceState.getBoolean("reply_visible");
            if (isVisible) {
                headtextview.setVisibility(View.VISIBLE);
                replytextview.setVisibility(View.VISIBLE);
                replytextview.setText(savedInstanceState.getString("reply_text"));

            }
        }

    }


    // Save the activity instance state with onSaveInstanceState()
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (headtextview.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text", replytextview.getText().toString());

        }
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

    public void showmsg(String msg) {
        Log.d("lifecycle" + TAG, msg);
    }


    public void movetoanother(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        String message = etname.getText().toString();
        intent.putExtra(EXTRA_MSG, message);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Test for the right intent reply
        if (requestCode == REQUEST_CODE) {
            // Test to make sure the intent reply result was good.
            if (resultCode == RESULT_OK) {

                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                headtextview.setVisibility(View.VISIBLE);
                replytextview.setText(reply);
                replytextview.setVisibility(View.VISIBLE);
            }

        }

    }
}

