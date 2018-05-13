package com.example.arjun_mu.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // EditText view for the website URI
    private EditText mWebsiteEditText;
    // EditText view for the location URI
    private EditText mLocationEditText;
    // EditText view for the share text
    private EditText mShareTextEditText;

    private TextView globaltextview;

    private GlobalVariable globalVariable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText = (EditText) findViewById(R.id.website_edittext);
        mLocationEditText = (EditText) findViewById(R.id.location_editext);
        mShareTextEditText = (EditText) findViewById(R.id.share_edittext);
        globaltextview=(TextView)findViewById(R.id.apptextView);
        globalVariable= (GlobalVariable) getApplicationContext();


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        globalVariable.setUsername(globalVariable.getUsername());

    }

    public void changetext(View view) {
        globaltextview.setText(globalVariable.getUsername());
    }
}

