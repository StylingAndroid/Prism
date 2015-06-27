package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;

public class FloatingActionButton extends View {
    public static final int DEFAULT = 345;
    private Integer privateField = DEFAULT;
    private final Integer finalField = DEFAULT;

    public FloatingActionButton(Context context) {
        super(context);
    }

    public FloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBackgroundTintList(ColorStateList colorStateList) {

    }
}
