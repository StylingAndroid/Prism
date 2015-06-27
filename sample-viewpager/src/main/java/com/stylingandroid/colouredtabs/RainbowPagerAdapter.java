package com.stylingandroid.colouredtabs;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.stylingandroid.prism.viewpager.ColourProvider;

public class RainbowPagerAdapter extends FragmentPagerAdapter implements ColourProvider {
    private static final Rainbow[] COLOURS = {
            Rainbow.Red, Rainbow.Orange, Rainbow.Yellow, Rainbow.Green,
            Rainbow.Blue, Rainbow.Indigo, Rainbow.Violet
    };

    private final Context context;

    public RainbowPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Rainbow colour = COLOURS[position];
        return ColourFragment.newInstance(context, getPageTitle(position), colour.getColour());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        FragmentManager manager = ((Fragment) object).getFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove((Fragment) object);
        trans.commit();
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return COLOURS.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return COLOURS[position].name();
    }

    @Override
    public int getColour(int position) {
        return COLOURS[position].getColour();
    }

    private enum Rainbow {
        Red(Color.rgb(0xFF, 0x00, 0x00)),
        Orange(Color.rgb(0xFF, 0x7F, 0x00)),
        Yellow(Color.rgb(0xCF, 0xCF, 0x00)),
        Green(Color.rgb(0x00, 0xAF, 0x00)),
        Blue(Color.rgb(0x00, 0x00, 0xFF)),
        Indigo(Color.rgb(0x4B, 0x00, 0x82)),
        Violet(Color.rgb(0x7F, 0x00, 0xFF));

        private final int colour;

        Rainbow(int colour) {
            this.colour = colour;
        }

        public int getColour() {
            return colour;
        }
    }
}
