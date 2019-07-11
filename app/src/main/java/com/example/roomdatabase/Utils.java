package com.example.roomdatabase;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.arch.persistence.room.TypeConverter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Utils {
    public static void setDialogAlert(Context context,String title, String content){
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();

    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if (bitmap==null)
            return null;
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap

    @TypeConverter
    public static Bitmap getImage(byte[] image) {
        if (image==null)
            return null;
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public static boolean isRequestPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if ((Boolean) ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // Explain to the user why we need to read the contacts

                }
                Log.d("mano", "isRequestPermission: 1");

                ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                // app-defined int constant that should be quite unique

                return false;
            }else {
                Log.d("mano", "isRequestPermission: 2");
                return true;
            }
        }
        Log.d("mano", "isRequestPermission: 3");
        return false;
    }

    public static class CustomLinearLayoutManager extends LinearLayoutManager {


        public CustomLinearLayoutManager(Context context) {
            super(context);
        }


        @Override
        public boolean canScrollHorizontally() {
            return false;
        }
    }

}
