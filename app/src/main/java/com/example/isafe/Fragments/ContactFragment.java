package com.example.isafe.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.isafe.Activities.DrawerActivity;
import com.example.isafe.Model.AccidentReport;
import com.example.isafe.Model.ContactDetails;
import com.example.isafe.R;
import com.example.isafe.Utills.MyApplication;

public class ContactFragment extends Fragment {
    private Context context;
    private EditText nameEditText,moblieTextView;
    private Button addContactButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.name_contact, container, false);
        nameEditText=view.findViewById(R.id.nameTextView);
        moblieTextView=view.findViewById(R.id.phonNoEditText);
        addContactButton=view.findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment();
            }
        });
        return view;
    }

    private void openFragment() {

        if(checkEditText()) {
            ContactDetails contactDetails = new ContactDetails(nameEditText.getText().toString().trim(),moblieTextView.getText().toString().trim());
            if(MyApplication.accidentReport==null){
                MyApplication.accidentReport= AccidentReport.getAccidentReport();
            }
            MyApplication.accidentReport.setContactDetails(contactDetails);
            Intent intent = new Intent(context, DrawerActivity.class);
            startActivity(intent);
        }
    }

    private boolean checkEditText() {

        if(nameEditText.getText().toString().trim().equals("")){
            nameEditText.setError("Please enter your name!");
            nameEditText.requestFocus();
            return false;
        }
        if(moblieTextView.getText().toString().trim().equals("")){
            moblieTextView.setError("Please enter mobile no. first!");
            moblieTextView.requestFocus();
            return false;
        }
        return true;
    }
}
