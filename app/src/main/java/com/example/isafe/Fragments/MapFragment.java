package com.example.isafe.Fragments;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.isafe.Activities.DrawerActivity;
import com.example.isafe.Model.AccidentReport;
import com.example.isafe.Model.AddressAndLatLong;
import com.example.isafe.R;
import com.example.isafe.Utills.GPSTracker;
import com.example.isafe.Utills.MyApplication;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapFragment extends Fragment implements OnMapReadyCallback,View.OnClickListener {
    private Context context;
    private GoogleMap mMap;

    private Button attachLocationButton;
    private EditText addLocationEditText;
    private String address;
    private double latitude,longitude;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context=getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.maps_fragment,container,false);

        attachLocationButton=view.findViewById(R.id.attachLocationButton);
        attachLocationButton.setOnClickListener(this);
        addLocationEditText=view.findViewById(R.id.locationEditText);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        return view;
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        GPSTracker gps = new GPSTracker(context);
        latitude = gps.getLatitude();
        longitude = gps.getLongitude();
        LatLng myLocation=new LatLng(latitude,longitude);

        mMap.addMarker(new MarkerOptions().position(myLocation)
                .title("My Location")
                .icon(BitmapDescriptorFactory.defaultMarker()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        mMap.getUiSettings().setZoomControlsEnabled(true);

        setAddress(latitude,longitude);

    }

    private void setAddress(double latitude, double longitude) {

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            addLocationEditText.setText(knownName+" "
            +city+" "
            +state+" "
            +country+" "
            +postalCode);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {

        if(MyApplication.accidentReport!=null) {
            MyApplication.accidentReport.setAddressAndLatLong(new AddressAndLatLong(address, latitude, longitude));
        }
        else {
            MyApplication.accidentReport = AccidentReport.getAccidentReport();
            MyApplication.accidentReport.setAddressAndLatLong(new AddressAndLatLong(address, latitude, longitude));

        }
        Intent intent=new Intent(context, DrawerActivity.class);
        startActivity(intent);

        }

    }
