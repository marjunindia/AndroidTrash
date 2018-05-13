package com.example.arjun_mu.android;

import android.app.Application;

public class GlobalVariable extends Application {

    private String username="arjun";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
