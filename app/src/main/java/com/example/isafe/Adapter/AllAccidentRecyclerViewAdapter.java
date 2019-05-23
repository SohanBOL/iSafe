package com.example.isafe.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.isafe.Model.AccidentReportModel;
import com.example.isafe.R;
import com.example.isafe.ViewHolder.AllAccidentViewHolder;

import java.util.ArrayList;

public class AllAccidentRecyclerViewAdapter extends RecyclerView.Adapter<AllAccidentViewHolder> {

    private ArrayList<AccidentReportModel> accidentReportModels=new ArrayList<>();
    private Context context;

    public AllAccidentRecyclerViewAdapter(Context context,ArrayList<AccidentReportModel> accidentReportModels) {
        this.context = context;
        this.accidentReportModels = accidentReportModels;

    }

    @NonNull
    @Override
    public AllAccidentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.all_accident_row,viewGroup,false);
        return new AllAccidentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllAccidentViewHolder allAccidentViewHolder, int i) {
        allAccidentViewHolder.contactTextView.setText(accidentReportModels.get(i).getContactDetails().getName()
        +"    "+
                accidentReportModels.get(i).getContactDetails().getMobile());
        Glide.with(context).load(accidentReportModels.get(i).getImages().getImageUrls().get(0)).into(allAccidentViewHolder.imageView1);
        Glide.with(context).load(accidentReportModels.get(i).getImages().getImageUrls().get(1)).into(allAccidentViewHolder.imageView2);
        Glide.with(context).load(accidentReportModels.get(i).getImages().getImageUrls().get(2)).into(allAccidentViewHolder.imageView3);
        allAccidentViewHolder.locationTextView.setText(accidentReportModels.get(i).getAddressAndLatLong().getAddress());

    }

    @Override
    public int getItemCount() {
        return accidentReportModels.size();
    }
}
