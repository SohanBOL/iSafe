package com.example.isafe.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.isafe.Adapter.AllAccidentRecyclerViewAdapter;
import com.example.isafe.Model.AccidentReportModel;
import com.example.isafe.R;
import com.example.isafe.Utills.DatabaseHelper;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AllAccident extends Fragment {
    private Context context;
    private RecyclerView recyclerView;
    private AllAccidentRecyclerViewAdapter allAccidentRecyclerViewAdapter;
    private ArrayList<AccidentReportModel> accidentReportModelsArrayList;
    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context=getContext();
        View view=LayoutInflater.from(context).inflate(R.layout.all_accident,container,false);

        progressBar=view.findViewById(R.id.recyclerProgressBar);
        recyclerView=view.findViewById(R.id.allAccidentRecyclerView);
        accidentReportModelsArrayList=new ArrayList<>();
        allAccidentRecyclerViewAdapter=new AllAccidentRecyclerViewAdapter(context,accidentReportModelsArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(allAccidentRecyclerViewAdapter);
        fetchAllAccident();
        return view;
    }

    private void fetchAllAccident() {

        FirebaseDatabase.getInstance().getReference("Accident").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                AccidentReportModel accidentReportModel=dataSnapshot.getValue(AccidentReportModel.class);
                if(notAlreadyPresent(accidentReportModel)){
                    accidentReportModelsArrayList.add(accidentReportModel);
                    Log.d("demo",dataSnapshot.toString());

                    progressBar.setVisibility(View.GONE);
                    allAccidentRecyclerViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private boolean notAlreadyPresent(AccidentReportModel accidentReportModel) {

        for(int i=0;i<accidentReportModelsArrayList.size();i++){
            if(BothEquals(accidentReportModel,accidentReportModelsArrayList.get(i))){
                return false;
            }
        }
        return  true;
    }

    private boolean BothEquals(AccidentReportModel accidentReportModel, AccidentReportModel newAccidentReportModel) {

        return accidentReportModel.getContactDetails().getMobile().equals(newAccidentReportModel.getContactDetails().getMobile())&&
                accidentReportModel.getContactDetails().getName().equals(newAccidentReportModel.getContactDetails().getName())&&
                accidentReportModel.getAddressAndLatLong().getAddress().equals(newAccidentReportModel.getAddressAndLatLong().getAddress());
    }

}
