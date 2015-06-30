package com.stylingandroid.colouredtabs;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.stylingandroid.prism.ColourChangeTrigger;
import com.stylingandroid.prism.Prism;
import com.stylingandroid.prism.filter.ColourFilter;
import com.stylingandroid.prism.filter.TintFilter;
import com.stylingandroid.prism.viewpager.ViewPagerTrigger;

public class MainActivity extends AppCompatActivity {
    private static final float TINT_FACTOR_50_PERCENT = 0.5f;
    private DrawerLayout drawerLayout;
    private View navHeader;
    private AppBarLayout appBar;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navHeader = findViewById(R.id.nav_header);
        appBar = (AppBarLayout) findViewById(R.id.app_bar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        setupToolbar();
        setupViewPager();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.app_title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupViewPager() {
        RainbowPagerAdapter adapter = new RainbowPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        ColourFilter tint = new TintFilter(TINT_FACTOR_50_PERCENT);
        ColourChangeTrigger trigger = ViewPagerTrigger.newInstance(viewPager, adapter);
        Prism.newInstance(trigger)
                .background(appBar)
                .background(getWindow())
                .background(navHeader)
                .background(fab, tint)
                .colour(viewPager, tint)
                .build();
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }
}
