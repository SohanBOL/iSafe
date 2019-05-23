package com.example.isafe.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.isafe.R;
import com.example.isafe.Utills.MyApplication;

public class SplashScreen extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=LayoutInflater.from(getContext()).inflate(R.layout.splash_screen,container,false);
        MyApplication.accidentReport=null;
       return view;
    }
}
