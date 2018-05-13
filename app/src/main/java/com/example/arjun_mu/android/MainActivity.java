package com.example.arjun_mu.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView globaltextview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
;
        globaltextview=(TextView)findViewById(R.id.apptextView);



    }

    @Override
    protected void onResume() {
        super.onResume();

        if(MySingleton.getInstance().isSave()){
            globaltextview.setText(MySingleton.getInstance().getName());

        }

    }

    public void changetext(View view) {
        globaltextview.setText(MySingleton.getInstance().getName());
            MySingleton.getInstance().setSave(true);
    }
}

