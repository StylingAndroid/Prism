package com.stylingandroid.prism.sample.palette;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import java.lang.ref.WeakReference;

class ImageLoadTask extends AsyncTask<Uri, Void, Bitmap> {
    private final WeakReference<MainActivity> activityWeakReference;

    public static ImageLoadTask newInstance(MainActivity activity) {
        WeakReference<MainActivity> activityWeakReference = new WeakReference<MainActivity>(activity);
        return new ImageLoadTask(activityWeakReference);
    }

    ImageLoadTask(WeakReference<MainActivity> activityWeakReference) {
        this.activityWeakReference = activityWeakReference;
    }

    @Override
    protected Bitmap doInBackground(Uri... uris) {
        MainActivity activity = activityWeakReference.get();
        Bitmap bitmap = null;
        if (activity != null && uris.length > 0) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), uris[0]);
            } catch (Exception e) {
                return null;
            }
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        MainActivity activity = activityWeakReference.get();
        if (activity != null) {
            if (bitmap != null) {
                activity.setBitmap(bitmap);
            } else {
                activity.showError(R.string.load_error);
            }
        }
    }
}
