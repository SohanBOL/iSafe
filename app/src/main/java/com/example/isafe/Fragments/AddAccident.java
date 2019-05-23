package com.example.isafe.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.isafe.Activities.DrawerActivity;
import com.example.isafe.Model.AccidentReport;
import com.example.isafe.Model.AddressAndLatLong;
import com.example.isafe.Model.ContactDetails;
import com.example.isafe.Model.Images;
import com.example.isafe.R;
import com.example.isafe.Utills.AllConstant;
import com.example.isafe.Utills.MyApplication;

import static com.example.isafe.R.id.displayContact;

public class AddAccident extends Fragment implements View.OnClickListener{
    private Context context;
    private ImageView imageView1,imageView2,imageView3;
    private TextView locationTextView,contactTextView;
    private View displayImageView,displayLocationView,displayContactDetailsView;
    private Button clickPhotoCardView,attachLocationCardView,contactPersonCardView,reportCardView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context=getContext();
        View view=LayoutInflater.from(context).inflate(R.layout.add_accident,container,false);
        displayContactDetailsView=view.findViewById(displayContact);
        displayLocationView=view.findViewById(R.id.displayLocation);
        displayImageView=view.findViewById(R.id.displayImages);
        imageView1=displayImageView.findViewById(R.id.imageView1);
        imageView2=displayImageView.findViewById(R.id.imageView2);
        imageView3=displayImageView.findViewById(R.id.imageView3);
        locationTextView=displayLocationView.findViewById(R.id.locationTextView);
        contactTextView=displayContactDetailsView.findViewById(R.id.displayMobileTextView);
        clickPhotoCardView=view.findViewById(R.id.clickPhotoCardView);
        attachLocationCardView=view.findViewById(R.id.attachLocation);
        contactPersonCardView=view.findViewById(R.id.contactPersoncardView);
        reportCardView=view.findViewById(R.id.reportcardView);
        clickPhotoCardView.setOnClickListener(this);
        attachLocationCardView.setOnClickListener(this);
        contactPersonCardView.setOnClickListener(this);
        reportCardView.setOnClickListener(this);

        checkAccident();

        return view;

    }

    private void checkAccident() {


        if(MyApplication.accidentReport==null){
            MyApplication.accidentReport= AccidentReport.getAccidentReport();
        }
        Images images=MyApplication.accidentReport.getImages();
        if(images==null){

            displayImageView.setVisibility(View.GONE);
            clickPhotoCardView.setVisibility(View.VISIBLE);
        }
        else{
            displayImageView.setVisibility(View.VISIBLE);
            imageView1.setImageURI(Uri.parse(images.getImageUrl1()));
            imageView2.setImageURI(Uri.parse(images.getImageUrl2()));
            imageView3.setImageURI(Uri.parse(images.getImageUrl3()));
            clickPhotoCardView.setVisibility(View.GONE);

        }
        AddressAndLatLong addressAndLatLong=MyApplication.accidentReport.getAddressAndLatLong();
        if(addressAndLatLong==null){

            displayLocationView.setVisibility(View.GONE);
            attachLocationCardView.setVisibility(View.VISIBLE);
        }
        else{
            displayLocationView.setVisibility(View.VISIBLE);
            attachLocationCardView.setVisibility(View.GONE);
            locationTextView.setText(addressAndLatLong.getAddress());
        }
        ContactDetails contactDetails=MyApplication.accidentReport.getContactDetails();
        if(contactDetails==null){
            displayContactDetailsView.setVisibility(View.GONE);
            contactPersonCardView.setVisibility(View.VISIBLE);
        }
        else{
            String mobile=contactDetails.getMobile();
            mobile=mobile.substring(0,5);
            mobile+="*****";
            displayContactDetailsView.setVisibility(View.VISIBLE);
            contactPersonCardView.setVisibility(View.GONE);
            contactTextView.setText(contactDetails.getName()+"    "+mobile);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.clickPhotoCardView:
                openFragment(AllConstant.CLICK_PHOTOS_FRAGMENT);
                break;
            case R.id.attachLocation:

                openFragment(AllConstant.MAP_FRAGMENT);
                break;
            case R.id.contactPersoncardView:
                openFragment(AllConstant.CONTACT_DETAILS);
                break;
            case R.id.reportcardView:
                uploadData();
                break;

        }
    }

    private void uploadData() {

        //TODO: upload data on firebase
    }

    private void openFragment(int fragmentNo) {

        Intent intent=new Intent(context,DrawerActivity.class);
        intent.putExtra("FRAGMENT",fragmentNo);
        startActivity(intent);
    }

    public static AddAccident newInstance() {

        Bundle args = new Bundle();

        AddAccident fragment = new AddAccident();
        fragment.setArguments(args);
        return fragment;
    }
}
