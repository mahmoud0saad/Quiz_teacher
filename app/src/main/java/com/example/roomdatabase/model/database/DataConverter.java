package com.example.roomdatabase.model.database;

import android.arch.persistence.room.TypeConverter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.roomdatabase.Utils;

import java.io.ByteArrayOutputStream;

public class DataConverter {
    @TypeConverter
    public static byte[] getBytes(Bitmap bitmap) {
       return Utils.getBytes(bitmap);
    }

    // convert from byte array to bitmap

    @TypeConverter
    public static Bitmap getImage(byte[] image) {
        return Utils.getImage(image);
    }
}
