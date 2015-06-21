package com.stylingandroid.colouredtabs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.design.R;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.stylingandroid.prism.viewpager.ColourProvider;

public class ColouredTabLayout extends TabLayout {
    private int tabTextAppearance;

    public ColouredTabLayout(Context context) {
        super(context, null);
    }

    public ColouredTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColouredTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabLayout, defStyleAttr, R.style.Widget_Design_TabLayout);
        tabTextAppearance = typedArray.getResourceId(R.styleable.TabLayout_tabTextAppearance, R.style.TextAppearance_Design_Tab);
        typedArray.recycle();
        setClipChildren(false);
    }

    @Override
    public void setTabsFromPagerAdapter(PagerAdapter adapter) {
        this.removeAllTabs();
        int i = 0;
        ColourProvider colourProvider = adapter instanceof ColourProvider ? (ColourProvider) adapter : NULL_SAFE_COLOUR_PROVIDER;

        for (int count = adapter.getCount(); i < count; ++i) {
            Tab tab = this.newTab();
            TextView view = createCustomTabView();
            view.setText(adapter.getPageTitle(i));
            view.setBackgroundColor(colourProvider.getColour(i));
            tab.setCustomView(view);
            this.addTab(tab);
        }
    }

    private TextView createCustomTabView() {
        Context context = getContext();
        AppCompatTextView textView1 = new AppCompatTextView(context);
        textView1.setTextAppearance(context, tabTextAppearance);
        textView1.setMaxLines(2);
        textView1.setEllipsize(TextUtils.TruncateAt.END);
        textView1.setGravity(Gravity.CENTER);
        LayoutParams layoutParams = generateDefaultLayoutParams();
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.height = LayoutParams.MATCH_PARENT;
        textView1.setLayoutParams(layoutParams);
        if (getTabTextColors() != null) {
            textView1.setTextColor(getTabTextColors());
        }
        return textView1;
    }

    private static final ColourProvider NULL_SAFE_COLOUR_PROVIDER = new ColourProvider() {
        @Override
        public int getColour(int position) {
            return Color.TRANSPARENT;
        }
    };
}
