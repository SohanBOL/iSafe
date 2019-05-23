package com.example.isafe.Utills;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CompressImage {

    private Uri fileUri;
    private Context context;

    public CompressImage(Context context,Uri fileUri) {
        this.fileUri = fileUri;
        this.context = context;
    }

    public byte[] compress() throws IOException {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), fileUri);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,25, byteArrayOutputStream);
        byte[] data = byteArrayOutputStream.toByteArray();
        //uploading the image
        return data;
    }
}