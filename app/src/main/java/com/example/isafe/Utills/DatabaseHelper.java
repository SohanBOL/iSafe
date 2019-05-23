package com.example.isafe.Utills;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.isafe.Activities.DrawerActivity;
import com.example.isafe.Model.AccidentReportModel;
import com.example.isafe.Model.Images;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class DatabaseHelper {

    private static DatabaseHelper instance;
    private Context context;
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private ArrayList<String> imageUrlArrayList;
    private int index=0;
    public DatabaseHelper(Context context) {
        this.context = context;
        this.database = FirebaseDatabase.getInstance();
        this.storage = FirebaseStorage.getInstance();
        imageUrlArrayList=new ArrayList<>();
    }

    public DatabaseHelper() {
    }


    public void uploadMultipleImages(final ArrayList<String> imageUrls) throws IOException {

        CompressImage compressImage=new CompressImage(context,Uri.parse(imageUrls.get(index)));
        byte[] image=compressImage.compress();
        String uuid=UUID.randomUUID().toString();
        storage.getReference("Photos").child(uuid).putBytes(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

                        imageUrlArrayList.add(task.getResult().toString());
                        index++;
                        if(index>=3){

                            MyApplication.accidentReport.setImages(new Images(imageUrlArrayList));
                            uploadDataOnFirebase();
                        }
                        else{
                            try {
                                uploadMultipleImages(imageUrls);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }
    public void uploadDataOnFirebase(){
        database.getReference("Accident").push().setValue(MyApplication.accidentReport).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(context, "Data uploaded!", Toast.LENGTH_SHORT).show();
                    //TODO: open congratulation fragment.
                    Intent intent=new Intent(context, DrawerActivity.class);
                    intent.putExtra("FRAGMENT",AllConstant.SPLASH_SCREEN);
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context, task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
