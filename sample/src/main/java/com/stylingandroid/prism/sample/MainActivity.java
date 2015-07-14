package com.stylingandroid.prism.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.stylingandroid.prism.Prism;
import com.stylingandroid.prism.filter.Filter;
import com.stylingandroid.prism.filter.TintFilter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final float TINT_FACTOR_50_PERCENT = 0.5f;
    private static final int RED = Color.RED;
    private static final int GREEN = Color.GREEN;
    private static final int BLUE = Color.BLUE;

    private Prism prism;
    private int currentColour = RED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.text_view);
        AppBarLayout appBar = (AppBarLayout) findViewById(R.id.app_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        setSupportActionBar(toolbar);

        Filter tint = new TintFilter(TINT_FACTOR_50_PERCENT);
        prism = Prism.Builder.newInstance()
                .background(appBar)
                .background(getWindow())
                .text(textView)
                .background(fab, tint)
                .build();

        fab.setOnClickListener(this);
        setColour(currentColour);
    }

    @Override
    protected void onDestroy() {
        if (prism != null) {
            prism.destroy();
        }
        super.onDestroy();
    }


    private void setColour(int colour) {
        if (prism != null) {
            prism.setColour(colour);
        }
    }

    @Override
    public void onClick(View v) {
        switch (currentColour) {
            case RED:
                currentColour = GREEN;
                break;
            case GREEN:
                currentColour = BLUE;
                break;
            case BLUE:
            default:
                currentColour = RED;
                break;
        }
        setColour(currentColour);
    }
}
