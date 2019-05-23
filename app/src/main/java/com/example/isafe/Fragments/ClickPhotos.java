package com.example.isafe.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.isafe.Activities.DrawerActivity;
import com.example.isafe.Model.AccidentReport;
import com.example.isafe.Model.Images;
import com.example.isafe.R;
import com.example.isafe.Utills.MyApplication;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClickPhotos extends Fragment implements View.OnClickListener{
    private Context context;
    static final int REQUEST_TAKE_PHOTO1 = 1,REQUEST_TAKE_PHOTO2 = 2,REQUEST_TAKE_PHOTO3 = 3;
    private Uri  photoURI1 ,photoURI2=null,photoURI3=null;//Since intent present in onActivityResult will be null.

    private  String currentPhotoPath;

    private ImageView imageView1,imageView2,imageView3;
    private Button attachImagesButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.add_three_photos, container, false);
        imageView1=view.findViewById(R.id.image1);
        imageView2=view.findViewById(R.id.image2);
        imageView3=view.findViewById(R.id.image3);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        attachImagesButton=view.findViewById(R.id.attachButton);
        attachImagesButton.setOnClickListener(this);

        return view;
    }

    private void dispatchTakePictureIntent(String imageNo) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (Exception ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                if (imageNo.equals("image1")) {

                    photoURI1 = FileProvider.getUriForFile(context,
                            "com.example.android.fileprovider",
                            photoFile);
                    Log.d("uri", photoURI1.toString());
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI1);
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO1);

                } else if (imageNo.equals("image2")) {

                    photoURI2 = FileProvider.getUriForFile(context,
                            "com.example.android.fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI2);
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO2);

                } else {

                    photoURI3 = FileProvider.getUriForFile(context,
                            "com.example.android.fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI3);//photoURI3 can't be changed
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO3);
                }
            }
        }
    }
File mPhotoFile;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        photoURI2=Uri.fromFile(image);
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override

    public  void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_TAKE_PHOTO1){
            setImage(imageView1,photoURI1);
//            setImage(Uri.parse(currentPhotoPath ));
        }
        else if(requestCode==REQUEST_TAKE_PHOTO2){

            setImage(imageView2,photoURI2);
        }
        else if(requestCode==REQUEST_TAKE_PHOTO3){
            setImage(imageView3,photoURI3);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.image1:
                dispatchTakePictureIntent("image1");
                break;
            case R.id.image2:
                dispatchTakePictureIntent("image2");
                break;
            case R.id.image3:
                dispatchTakePictureIntent("image3");
                break;
            case R.id.attachButton:
                goToPreviousPage();
                break;
        }
    }

    private void goToPreviousPage() {

        if(photoURI1!=null && photoURI3!=null && photoURI2!=null){
            // TODO: please complete this
            ArrayList<String> imagesArrayList=new ArrayList<>();
            imagesArrayList.add(photoURI1.toString());
            imagesArrayList.add(photoURI2.toString());
            imagesArrayList.add(photoURI3.toString());
            Images images=new Images(imagesArrayList);
           if(MyApplication.accidentReport!=null) {
               MyApplication.accidentReport.setImages(images);
           }
           else{
               MyApplication.accidentReport = AccidentReport.getAccidentReport();
               MyApplication.accidentReport.setImages(images);

           }
            Intent intent=new Intent(context, DrawerActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(context, "Please choose three photos first!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setImage(ImageView imageView,Uri photoURI) {

        imageView.setImageURI(photoURI);
//        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_camera));

    }
}