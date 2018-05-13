package com.example.arjun_mu.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {


    private TextView globaltextview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
;
        globaltextview=(TextView)findViewById(R.id.apptextView);

        EventBus.getDefault().register(this);

    }

    @Override
    protected void onResume() {
        super.onResume();



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }


    @Subscribe(threadMode =ThreadMode.MAIN)
    public void onMessageEvent(SampleData sampleData){
        Toast.makeText(this, ""+sampleData.getName(), Toast.LENGTH_SHORT).show();

    }

    public void changetext(View view) {

        MyIntentService.startActionBaz(this,"13","1232");
    }
}

