package com.stylingandroid.prism.sample.palette;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.stylingandroid.prism.Prism;
import com.stylingandroid.prism.Setter;
import com.stylingandroid.prism.palette.PaletteTrigger;

public class MainActivity extends AppCompatActivity implements Setter {
    private static final int SELECT_IMAGE = 1;
    private static final String IMAGE_MIME_TYPE = "image/*";

    private PaletteTrigger paletteTrigger;

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private ImageView imageView;
    private TextView titleText;
    private TextView bodyText;

    private Bitmap bitmap = null;

    private Prism prism = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        imageView = (ImageView) findViewById(R.id.image);

        View vibrant = findViewById(R.id.swatch_vibrant);
        View vibrantLight = findViewById(R.id.swatch_vibrant_light);
        View vibrantDark = findViewById(R.id.swatch_vibrant_dark);
        View muted = findViewById(R.id.swatch_muted);
        View mutedLight = findViewById(R.id.swatch_muted_light);
        View mutedDark = findViewById(R.id.swatch_muted_dark);

        titleText = (TextView) findViewById(R.id.title);
        bodyText = (TextView) findViewById(R.id.body);

        paletteTrigger = new PaletteTrigger();
        prism = Prism.Builder.newInstance()
                .add(paletteTrigger)
                .background(vibrant, paletteTrigger.getVibrantFilter(paletteTrigger.getColour()))
                .background(vibrantLight, paletteTrigger.getLightVibrantFilter(paletteTrigger.getColour()))
                .background(vibrantDark, paletteTrigger.getDarkVibrantFilter(paletteTrigger.getColour()))
                .background(muted, paletteTrigger.getMutedFilter(paletteTrigger.getColour()))
                .background(mutedLight, paletteTrigger.getLightMutedFilter(paletteTrigger.getColour()))
                .background(mutedDark, paletteTrigger.getDarkMutedFilter(paletteTrigger.getColour()))
                .background(titleText, paletteTrigger.getVibrantFilter(paletteTrigger.getColour()))
                .text(titleText, paletteTrigger.getVibrantFilter(paletteTrigger.getTitleTextColour()))
                .background(bodyText, paletteTrigger.getLightMutedFilter(paletteTrigger.getColour()))
                .text(bodyText, paletteTrigger.getLightMutedFilter(paletteTrigger.getBodyTextColour()))
                .add(this)
                .build();

        setupToolbar();
        setupFab();
    }

    @Override
    protected void onDestroy() {
        if (prism != null) {
            prism.destroy();
        }
        super.onDestroy();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setTitle(R.string.app_name);
        }
    }

    private void setupFab() {
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectImage();
                    }
                });
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType(IMAGE_MIME_TYPE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        getString(R.string.select_image)), SELECT_IMAGE);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == SELECT_IMAGE) {
            Uri selectedImageUri = data.getData();
            loadBitmap(selectedImageUri);
        }
    }

    private void loadBitmap(Uri selectedImageUri) {
        ImageLoadTask imageLoadTask = ImageLoadTask.newInstance(this);
        imageLoadTask.execute(selectedImageUri);
    }

    public void setBitmap(Bitmap newBitmap) {
        titleText.setVisibility(View.GONE);
        bodyText.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
        bitmap = newBitmap;
        if (bitmap != null) {
            paletteTrigger.setBitmap(bitmap);
        }
    }

    public void showError(int errorId) {
        String error = getString(errorId);
        Snackbar.make(imageView, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setColour(@ColorInt int colour) {
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            titleText.setVisibility(View.VISIBLE);
            bodyText.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setTransientColour(@ColorInt int colour) {
        //NO-OP
    }
}
