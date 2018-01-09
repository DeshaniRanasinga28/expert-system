package com.example.ef.expert;

import android.app.Application;

/**
 * Created by EF on 10/27/2017.
 */

public class ExpertApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();

    }

    private void init() {
        MyVolley.init(this);
    }
}
