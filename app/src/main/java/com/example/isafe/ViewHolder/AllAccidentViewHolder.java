package com.example.isafe.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.isafe.R;

public class AllAccidentViewHolder extends RecyclerView.ViewHolder {

    public View displayImageView,displayLocationView,displayContactDetailsView;
    public ImageView imageView1,imageView2,imageView3;
    public TextView locationTextView,contactTextView;


    public AllAccidentViewHolder(@NonNull View itemView) {
        super(itemView);
        displayContactDetailsView=itemView.findViewById(R.id.displayContact);
        displayImageView=itemView.findViewById(R.id.displayImages);
        displayLocationView=itemView.findViewById(R.id.displayLocation);
        imageView1=displayImageView.findViewById(R.id.imageView1);
        imageView2=displayImageView.findViewById(R.id.imageView2);
        imageView3=displayImageView.findViewById(R.id.imageView3);
        locationTextView=displayLocationView.findViewById(R.id.locationTextView);
        contactTextView=displayContactDetailsView.findViewById(R.id.displayMobileTextView);

    }
}
