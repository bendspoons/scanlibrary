package com.scanlibrary;

import android.content.Context;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Build;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by jhansi on 05/04/15.
 */
public class Utils {

    private Utils() {

    }

    public static Uri getUri(Context context, Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        private Uri collection;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
          collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        } else {
          collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"Title");
        values.put(MediaStore.Images.Media.DESCRIPTION,"From Camera");
        Uri path = context.getContentResolver().insert(collection, values);

        return path;

        //String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null);
        //return Uri.parse(path);
    }

    public static Bitmap getBitmap(Context context, Uri uri) throws IOException {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        return bitmap;
    }
}
