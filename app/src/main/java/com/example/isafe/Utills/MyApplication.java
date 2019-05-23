package com.example.isafe.Utills;

import android.app.Application;

import com.example.isafe.Model.AccidentReport;

public class MyApplication extends Application {
    public static AccidentReport accidentReport;
    @Override
    public void onCreate() {
        super.onCreate();
        accidentReport=AccidentReport.getAccidentReport();
    }
}
